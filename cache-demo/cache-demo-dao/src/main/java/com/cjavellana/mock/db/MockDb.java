package com.cjavellana.mock.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cjavellana.model.UserInfo;

/**
 * The so-called underlying 'database'
 * 
 * @author Christian
 * 
 */
public class MockDb {

	private static final Log logger = LogFactory.getLog(MockDb.class);

	private static List<UserInfo> userList = new ArrayList<UserInfo>();

	static {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			UserInfo john = new UserInfo();
			john.setUserInfoId(1);
			john.setLoginId("john");
			john.setName("John Doe");
			john.setEmail("john@demo.com");
			john.setBirthdate(sdf.parse("01/01/1980"));

			userList.add(john);

			UserInfo peter = new UserInfo();
			peter.setUserInfoId(2);
			peter.setLoginId("peter");
			peter.setName("Peter Doe");
			peter.setEmail("peter@demo.com");
			peter.setBirthdate(sdf.parse("03/05/1985"));

			userList.add(peter);

			UserInfo dasher = new UserInfo();
			dasher.setUserInfoId(3);
			dasher.setLoginId("dasher");
			dasher.setName("Dasher");
			dasher.setEmail("dasher@demo.com");
			dasher.setBirthdate(sdf.parse("15/06/1987"));

			userList.add(dasher);

			UserInfo dancer = new UserInfo();
			dancer.setUserInfoId(3);
			dancer.setLoginId("dancer");
			dancer.setName("Dancer");
			dancer.setEmail("dancer@demo.com");
			dancer.setBirthdate(sdf.parse("18/08/1989"));

			userList.add(dancer);

		} catch (ParseException pe) {
			logger.fatal("Unable to initialize mock database", pe);
		}
	}

	/**
	 * An expensive operation for retrieving a user from the 'database'
	 * 
	 * @param username
	 *            The user's name
	 * @param birthdate
	 *            The user's birthdate
	 * @return The {@link UserInfo} who's username and birthdate matches the
	 *         given parameters <tt>username</tt> and <tt>birthdate</tt>
	 */
	public static UserInfo getUser(String username, Date birthdate) {
		for (UserInfo userInfo : userList) {
			if (userInfo.getName().equalsIgnoreCase(username)
					&& userInfo.getBirthdate().equals(birthdate)) {
				return userInfo;
			}
		}

		return null;
	}
}
