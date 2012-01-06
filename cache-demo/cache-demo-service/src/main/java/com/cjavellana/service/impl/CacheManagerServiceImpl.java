package com.cjavellana.service.impl;

import org.springframework.stereotype.Service;

import com.cjavellana.cache.Cache;
import com.cjavellana.cache.CacheImpl;
import com.cjavellana.cache.Cacheable;
import com.cjavellana.service.CacheManagerService;

/**
 * A service layer class implementing the {@link CacheManagerService}
 * 
 * @author Christian
 * @since 1.0
 */
@Service("cacheManagerService")
public class CacheManagerServiceImpl implements CacheManagerService {

	/**
	 * This instance variable will be a shared resource since we are letting
	 * spring manage the scope of this service class in a singleton scope.
	 */
	private Cache cache = new CacheImpl();

	public CacheManagerServiceImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(String key, Cacheable value) {
		cache.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cacheable get(String key) {
		return cache.get(key);
	}

}
