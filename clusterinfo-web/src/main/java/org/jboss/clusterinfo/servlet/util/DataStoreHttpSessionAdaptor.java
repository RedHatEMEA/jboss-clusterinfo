package org.jboss.clusterinfo.servlet.util;

import javax.servlet.http.HttpSession;

import org.jboss.clusterinfo.util.DataStore;

public class DataStoreHttpSessionAdaptor implements DataStore<String, Object> {

	private static final long serialVersionUID = -8451932266157778358L;

	private HttpSession session;

	public DataStoreHttpSessionAdaptor(HttpSession session) {
		this.session = session;
	}

	public Object get(String key) {
		return session.getAttribute(key);
	}

	public Object remove(String key) {
		Object value = get(key);
		session.removeAttribute(key);
		return value;
	}

	public void set(String key, Object value) {
		session.setAttribute(key, value);
	}

	public void clear() {
		session.invalidate();
	}

}
