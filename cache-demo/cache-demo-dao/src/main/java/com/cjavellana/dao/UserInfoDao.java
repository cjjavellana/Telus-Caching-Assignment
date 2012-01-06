package com.cjavellana.dao;

import java.util.Date;

import com.cjavellana.model.UserInfo;

/**
 * <p>
 * This interface defines the operations managing the user's information
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
public interface UserInfoDao {

	/**
	 * Creates a new {@link UserInfo} record
	 * 
	 * @param userInfo
	 *            the record to be created
	 */
	void addUser(UserInfo userInfo);

	/**
	 * Removes a {@link UserInfo} from the system
	 * 
	 * @param userInfoId
	 */
	void removeUser(int userInfoId);

	/**
	 * Retrieves a {@link UserInfo} identified by the parameter
	 * <tt>userInfoId</tt>
	 * 
	 * @param userInfoId
	 * @return
	 */
	UserInfo getUser(int userInfoId);

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
	 * Updates a {@link UserInfo}
	 * 
	 * @param userInfo
	 *            the record to be updated
	 */
	void updateUser(UserInfo userInfo);
}
