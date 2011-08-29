package org.jboss.clusterinfo.util;

public class DataStoreFactory {

	public static DataStore<String, Object> getDataStore() {
		return new DataStoreMapImpl<String, Object>();
	}

	public static DataStore<String, DataStore<String, Object>> getDataStore(
			String treeCacheMBeanServiceLocatorString, String leafLocator) {
		return new DataStoreTreeCacheImpl<String, DataStore<String, Object>>(
				treeCacheMBeanServiceLocatorString, leafLocator);
	}

}
