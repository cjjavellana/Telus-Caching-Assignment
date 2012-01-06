package com.cjavellana.cache;

/**
 * <p>
 * This interface defines the {@link Cache} operations
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
public interface Cache {

	/**
	 * Stores a {@link Cacheable} object into the cache
	 * 
	 * @param key
	 *            the <tt>object's</tt> key
	 * @param value
	 *            the object to store
	 */
	void put(String key, Cacheable value);

	/**
	 * Removes an object identified by <tt>key</tt> from the cache
	 * 
	 * @param key
	 */
	void remove(String key);

	/**
	 * <p>
	 * Retrieves a previously stored object from the cache.
	 * </p>
	 * <p>
	 * May return <tt>null</tt> if no object has been mapped to the given
	 * <tt>key</tt>
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	Cacheable get(String key);

	/**
	 * Clears all the contents of the cache
	 */
	void invalidate();
}
