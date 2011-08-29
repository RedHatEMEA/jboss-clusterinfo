package org.jboss.clusterinfo.util;

import java.io.Serializable;

import javax.management.MalformedObjectNameException;

import org.jboss.cache.Cache;
import org.jboss.cache.CacheException;
import org.jboss.cache.jmx.CacheJmxWrapperMBean;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.mx.util.MBeanServerLocator;

public class DataStoreTreeCacheImpl<Key, Value> implements
		DataStore<Key, Value>, Serializable {

	private static final long serialVersionUID = -2507890716531593732L;

	private Cache<String, Value> dataStore;
	private String leafLocator;

	@SuppressWarnings("unchecked")
	public DataStoreTreeCacheImpl(String treeCacheMBeanServiceLocatorString,
			String leafLocator) {

		try {
			dataStore = ((CacheJmxWrapperMBean<String, Value>) MBeanProxyExt.create(
					CacheJmxWrapperMBean.class, treeCacheMBeanServiceLocatorString,
					MBeanServerLocator.locateJBoss())).getCache();
		} catch (MalformedObjectNameException e) {
		}

		if (dataStore == null)
			throw new RuntimeException("could not resolve tree cache mbean ["
					+ treeCacheMBeanServiceLocatorString + "]");

		if (leafLocator == null || leafLocator.equals(""))
			throw new RuntimeException("invalie leaf locator [" + leafLocator
					+ "]");

		this.leafLocator = leafLocator;
	}

	public Value get(Key key) {
		try {
			return (Value) dataStore.get(key.toString(), leafLocator);
		} catch (CacheException e) {
			return null;
		}
	}

	public Value remove(Key key) {
		try {
			return (Value) dataStore.remove(key.toString(), leafLocator);
		} catch (CacheException e) {
			return null;
		}
	}

	public void set(Key key, Value value) {
		try {
			dataStore.put(key.toString(), leafLocator, value);
		} catch (CacheException e) {
			;
		}
	}

	public void clear() {
		try {
			dataStore.removeNode("/");
		} catch (CacheException e) {
			;
		}
	}

}
