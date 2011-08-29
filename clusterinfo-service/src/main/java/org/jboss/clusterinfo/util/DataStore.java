package org.jboss.clusterinfo.util;

import java.io.Serializable;

public interface DataStore<Key, Value> extends Serializable {

	public void set(Key key, Value value);

	public Value remove(Key key);

	public Value get(Key key);

	public void clear();

}
