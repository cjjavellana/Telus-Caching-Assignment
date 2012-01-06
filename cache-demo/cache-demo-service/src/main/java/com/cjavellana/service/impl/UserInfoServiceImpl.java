package com.cjavellana.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cjavellana.dao.UserInfoDao;
import com.cjavellana.model.UserInfo;
import com.cjavellana.service.CacheManagerService;
import com.cjavellana.service.UserInfoService;

/**
 * The service layer implementation of the {@link UserInfoService} interface
 * 
 * @author Christian
 * @since 1.0
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	// ~ Field constant definitions ============================================
	private static final Log logger = LogFactory
			.getLog(UserInfoServiceImpl.class);
	private static final String SPACE = " ";
	private static final String UNDERSCORE = "_";
	private static final long FIFTEEN_MINUTES = 60 * 15;

	// ~ Dao and service classes definitions ===================================

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private CacheManagerService cacheManagerService;

	// ~ Method definitions ===================================================

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	// Transactions are disabled as we don't have an underlying
	// transactionManager
	// @Transactional(propagation = Propagation.REQUIRED)
	public void addUser(UserInfo userInfo) {
		userInfoDao.addUser(userInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// Transactions are disabled as we don't have an underlying
	// transactionManager
	// @Transactional(readOnly = true)
	public UserInfo getUserInfo(int userInfoId) {
		return userInfoDao.getUser(userInfoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// Transactions are disabled as we don't have an underlying
	// transactionManager
	// @Transactional(readOnly = true)
	public UserInfo getUserInfo(String username, Date birthdate) {
		Assert.hasText(username, "username must not be null or empty string");
		Assert.notNull(birthdate, "birthdate must not be null");

		boolean debug = logger.isDebugEnabled();

		// check if we have an object available from the cache
		String cacheKey = username.replace(SPACE, UNDERSCORE) + "-"
				+ birthdate.getTime();
		UserInfo userInfo = (UserInfo) cacheManagerService.get(cacheKey);

		if (debug) {
			logger.debug(String.format("Cache hit? %s; Cache key: %s",
					(userInfo != null), cacheKey));
		}

		// user does not exist in the cache, retrieve it from the underlying
		// database
		if (userInfo == null) {
			userInfo = userInfoDao.getUserInfo(username, birthdate);
			if (userInfo != null) {
				// set object's ttl to fifteen minutes
				userInfo.setTimeToLive(FIFTEEN_MINUTES);
				cacheManagerService.put(cacheKey, userInfo);
			}
		}
		return userInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// Transactions are disabled as we don't have an underlying
	// transactionManager
	// @Transactional(propagation = Propagation.REQUIRED)
	public void removeUser(int userInfoId) {
		Assert.isTrue(userInfoId > 0, "0 is not a valid userInfoId");

		// get user information first so that we can construct the cache key and
		// subsequently remove it from the cache
		UserInfo userInfo = getUserInfo(userInfoId);
		if (userInfo != null) {
			String cacheKey = userInfo.getName().replace(SPACE, UNDERSCORE)
					+ "-" + userInfo.getBirthdate().getTime();
			cacheManagerService.remove(cacheKey);
			userInfoDao.removeUser(userInfoId);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// Transactions are disabled as we don't have an underlying
	// transactionManager
	// @Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(UserInfo userInfo) {
		Assert.notNull(userInfo, "userInfo must not be null");
		boolean validId = userInfo.getUserInfoId() != null
				&& userInfo.getUserInfoId() > 0;
		Assert.isTrue(validId,
				"userInfo.getUserInfoId() must null be null or 0");

		// get user information first so that we can construct the cache key and
		// subsequently remove it from the cache
		UserInfo managedEntity = getUserInfo(userInfo.getUserInfoId());
		if (managedEntity != null) {
			// it might be possible the the key we are using (name, birthdate)
			// in this instance has been updated. If that is the case, we must
			// remove the previously cached object first, before updating the
			// underlying database and finally restoring the object back to the
			// cache

			// remove the object that will be updated from the cache
			String cacheKey = managedEntity.getName()
					.replace(SPACE, UNDERSCORE)
					+ "-"
					+ managedEntity.getBirthdate().getTime();
			cacheManagerService.remove(cacheKey);

			// update the properties
			managedEntity.setLoginId(userInfo.getLoginId());
			managedEntity.setName(userInfo.getName());
			managedEntity.setEmail(userInfo.getEmail());
			managedEntity.setBirthdate(userInfo.getBirthdate());

			// merge with database
			userInfoDao.updateUser(managedEntity);

			// restore the object back into the cache
			cacheManagerService.put(cacheKey, managedEntity);
		}
	}
}
