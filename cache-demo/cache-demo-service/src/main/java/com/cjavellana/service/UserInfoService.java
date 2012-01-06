package com.cjavellana.service;

import java.util.Date;

import com.cjavellana.model.UserInfo;

/**
 * The {@link UserInfoService} interface defines the business specific
 * operations for interacting with user's information
 * 
 * @author Christian
 * @since 1.0
 */
public interface UserInfoService {

	/**
	 * Creates a new {@link UserInfo} record
	 * 
	 * @param userInfo
	 */
	void addUser(UserInfo userInfo);

	/**
	 * Retrieves a {@link UserInfo} record identified by the parameter
	 * <tt>userInfoId</tt>
	 * 
	 * @param userInfoId
	 * @return
	 */
	UserInfo getUserInfo(int userInfoId);

	/**
	 * Retrieves a {@link UserInfo} record identified by the user's name and
	 * date of birth
	 * 
	 * @param username
	 *            The user's name
	 * @param birthdate
	 *            The user's birhtdate
	 * @return
	 */
	UserInfo getUserInfo(String username, Date birthdate);

	/**
	 * Removes a {@link UserInfo} from the underlying database identified by the
	 * parameter <tt>userInfoId</tt>
	 * 
	 * @param userInfoId
	 */
	void removeUser(int userInfoId);

	/**
	 * Updates a {@link UserInfo}
	 * 
	 * @param userInfo
	 */
	void updateUser(UserInfo userInfo);

}
