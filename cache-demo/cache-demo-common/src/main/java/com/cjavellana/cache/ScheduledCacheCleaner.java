package com.cjavellana.cache;

import java.util.Collections;
import java.util.Map;
import java.util.TimerTask;

/**
 * <p>
 * The {@link ScheduledCacheCleaner} is used to remove expired {@link Cacheable}
 * objects from the <tt>cacheStore</tt>
 * </p>
 * <p>
 * This class is package private to as to prevent any usage from the outside of
 * this package
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
class ScheduledCacheCleaner extends TimerTask {

	private String key;

	/**
	 * The synchronized map instance. The returned instance of
	 * {@link Collections#synchronizedMap(Map)} here because the {@link #run()}
	 * method of this class does not <tt>synchronize</tt> the
	 * <tt>cacheStore</tt> before calling its remove method
	 */
	private Map<String, Cacheable> cacheStore;

	/**
	 * Creates a new {@link ScheduledCacheCleaner}
	 * 
	 * @param key
	 *            The key of the object to be removed from the cache
	 * @param cacheStore
	 *            The cache's instance
	 */
	public ScheduledCacheCleaner(String key, Map<String, Cacheable> cacheStore) {
		this.key = key;
		this.cacheStore = cacheStore;
	}

	/**
	 * Removes an object from the cache
	 */
	@Override
	public void run() {
		cacheStore.remove(key);
	}
}
