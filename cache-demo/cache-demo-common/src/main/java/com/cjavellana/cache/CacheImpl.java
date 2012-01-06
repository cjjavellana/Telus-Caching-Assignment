package com.cjavellana.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 * <strong>Assumption:</strong>This assumes that the application is not deployed
 * in cluster. Otherwise, replication should be used to sync the cache's state
 * across multiple nodes.
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
public class CacheImpl implements Cache, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5827243146125139720L;

	/**
	 * The cache's internal storage
	 */
	private Map<String, Cacheable> store = Collections
			.synchronizedMap(new HashMap<String, Cacheable>());

	/**
	 * Creates a new instance
	 */
	public CacheImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(String key, Cacheable value) {
		// get the time to live, in seconds
		long timeToLive = value.getTimeToLive();
		if (timeToLive > 0) {
			TimerTask cacheCleaner = new ScheduledCacheCleaner(key, store);
			new Timer().schedule(cacheCleaner, timeToLive * 1000);
		}
		store.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cacheable get(String key) {
		return store.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidate() {
		store = Collections.synchronizedMap(new HashMap<String, Cacheable>());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(String key) {
		store.remove(key);
	}

}
