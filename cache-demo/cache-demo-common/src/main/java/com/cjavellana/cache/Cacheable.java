package com.cjavellana.cache;

/**
 * The interface that should be implemented by classes that are meant to be
 * cached in {@link Cache}
 * 
 * @author Christian
 * @since 1.0
 * @see Cache
 */
public interface Cacheable {

	/**
	 * <p>
	 * Sets the number of seconds an object should be kept in the cache.
	 * </p>
	 * <p>
	 * A value of 0 or less means an object will be stored in the cache for an
	 * indefinite amount of time until the application is brought down.
	 * </p>
	 * 
	 * @param seconds
	 */
	void setTimeToLive(long seconds);

	/**
	 * Returns the specified time to live in seconds
	 * 
	 * @return
	 */
	long getTimeToLive();
}
