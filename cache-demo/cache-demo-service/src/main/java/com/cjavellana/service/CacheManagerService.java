package com.cjavellana.service;

import com.cjavellana.cache.Cacheable;

/**
 * The service interface defining the operations for caching frequently used
 * objects
 * 
 * @author Christian
 * @since 1.0
 */
public interface CacheManagerService {

	/**
	 * Stores a {@link Cacheable} object into the cache
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, Cacheable value);

	/**
	 * Removes a cached object identified by the parameter <tt>key</tt>
	 * 
	 * @param key
	 */
	void remove(String key);

	/**
	 * Retrieves an object identified by <tt>key</tt> from the cache
	 * 
	 * @param key
	 *            The cached object's key
	 * @return The object referred to by <tt>key</tt>
	 */
	Cacheable get(String key);
}
