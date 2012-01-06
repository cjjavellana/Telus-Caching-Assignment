package com.cjavellana.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cjavellana.dao.UserInfoDao;
import com.cjavellana.mock.db.MockDb;
import com.cjavellana.model.UserInfo;

/**
 * <p>
 * The hibernate based implementation of {@link UserInfoDao} interface
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	// @Autowired commented out as we don't have an underlying database
	// for this demo

	// @Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUser(UserInfo userInfo) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(userInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUser(int userInfoId) {
		Session session = sessionFactory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
		session.delete(userInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserInfo getUser(int userInfoId) {
		Session session = sessionFactory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
		return userInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(UserInfo userInfo) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(userInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserInfo getUserInfo(String username, Date birthdate) {
		return MockDb.getUser(username, birthdate);
	}

}
