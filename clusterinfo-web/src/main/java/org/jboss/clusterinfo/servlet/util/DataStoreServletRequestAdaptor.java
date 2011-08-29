package org.jboss.clusterinfo.servlet.util;

import javax.servlet.ServletRequest;

import org.jboss.clusterinfo.util.DataStore;

public class DataStoreServletRequestAdaptor implements
		DataStore<String, Object> {

	private static final long serialVersionUID = -597455543545168643L;

	private ServletRequest request;

	public DataStoreServletRequestAdaptor(ServletRequest request) {
		this.request = request;
	}

	public Object get(String key) {
		return request.getAttribute(key);
	}

	public Object remove(String key) {
		Object value = get(key);
		request.removeAttribute(key);
		return value;
	}

	public void set(String key, Object value) {
		request.setAttribute(key, value);
	}

	public void clear() {
	}

}
