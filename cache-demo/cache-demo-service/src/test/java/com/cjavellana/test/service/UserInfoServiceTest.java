package com.cjavellana.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cjavellana.model.UserInfo;
import com.cjavellana.service.UserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "/applicationContext-cacheDemo-service.xml",
		"classpath:/applicationContext-cacheDemo-dao.xml" })
public class UserInfoServiceTest {

	private static final Log logger = LogFactory
			.getLog(UserInfoServiceTest.class);

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void getUserInfoTest() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// first access, causes the object to be cached
			UserInfo userInfo = userInfoService.getUserInfo("John Doe",
					sdf.parse("01/01/1980"));

			Assert.assertNotNull(userInfo);

			// subsequent access will retrieve from the cache
			userInfo = userInfoService.getUserInfo("John Doe",
					sdf.parse("01/01/1980"));

			Assert.assertNotNull(userInfo);

		} catch (ParseException pe) {
			logger.fatal("Unable to execute getUserInfoTest", pe);
		}
	}
}
