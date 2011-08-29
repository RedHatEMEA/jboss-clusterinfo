package org.jboss.clusterinfo.servlet.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.clusterinfo.util.DataStore;

public class DataStoreFactory extends
		org.jboss.clusterinfo.util.DataStoreFactory {

	public static DataStore<String, Object> getDataStore(ServletRequest request) {
		return new DataStoreServletRequestAdaptor(request);
	}

	public static DataStore<String, Object> getDataStore(HttpSession session) {
		return new DataStoreHttpSessionAdaptor(session);
	}

}
