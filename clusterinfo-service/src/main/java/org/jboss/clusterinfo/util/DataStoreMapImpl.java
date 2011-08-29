package org.jboss.clusterinfo.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataStoreMapImpl<Key, Value> implements DataStore<Key, Value>,
		Serializable {

	private static final long serialVersionUID = -2507890716531593732L;

	private Map<Key, Value> dataStore = new HashMap<Key, Value>();

	public Value get(Key key) {
		return dataStore.get(key);
	}

	public Value remove(Key key) {
		return dataStore.remove(key);
	}

	public void set(Key key, Value value) {
		dataStore.put(key, value);
	}

	public void clear() {
		dataStore.clear();
	}

}
