package org.jboss.clusterinfo.ejb.stateless;

import javax.ejb.Remote;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;

@Remote
public interface Cluster {

	public static final String JNDI_NAME = ClusterInfoAPI.KEY_CLUSTERINFO
			+ "/Cluster/Remote";

	public ClusterInfo getClusterInfo();

}
