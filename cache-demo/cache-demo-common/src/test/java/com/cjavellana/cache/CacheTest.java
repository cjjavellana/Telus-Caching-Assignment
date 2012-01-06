package com.cjavellana.cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.cjavellana.model.UserInfo;

public class CacheTest {

	private static final Log logger = LogFactory.getLog(CacheTest.class);
	private static final String SPACE = " ";
	private static final String UNDERSCORE = "_";

	/**
	 * Tests a cache miss
	 */
	@Test
	public void testCacheMiss() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Cache cache = new CacheImpl();

			UserInfo john = new UserInfo();
			john.setUserInfoId(1);
			john.setLoginId("john");
			john.setName("John Doe");
			john.setEmail("john@demo.com");
			john.setBirthdate(sdf.parse("01/01/1980"));

			String cacheKey = john.getName().replace(SPACE, UNDERSCORE) + "-"
					+ john.getBirthdate().getTime();
			cache.put(cacheKey, john);

			Assert.assertNull(cache.get("invalid-key"));

		} catch (ParseException pe) {
			logger.fatal("Unable to run test", pe);
		}
	}

	/**
	 * Tests a cache hit
	 */
	@Test
	public void testCacheHit() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Cache cache = new CacheImpl();

			UserInfo john = new UserInfo();
			john.setUserInfoId(1);
			john.setLoginId("john");
			john.setName("John Doe");
			john.setEmail("john@demo.com");
			john.setBirthdate(sdf.parse("01/01/1980"));

			String cacheKey = john.getName().replace(SPACE, UNDERSCORE) + "-"
					+ john.getBirthdate().getTime();
			cache.put(cacheKey, john);

			Assert.assertNotNull(cache.get(cacheKey));

		} catch (ParseException pe) {
			logger.fatal("Unable to run test", pe);
		}
	}
}
