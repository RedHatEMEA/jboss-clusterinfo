package org.jboss.clusterinfo.ejb.stateful;

import javax.ejb.Remote;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;

@Remote
public interface ClusterStateful {

	public static final String JNDI_NAME = ClusterInfoAPI.KEY_CLUSTERINFO
			+ "/ClusterStateful/Remote";

	public void cacheClusterInfo(ClusterInfo clusterInfo);

	public void saveClusterInfo();

	public void loadClusterInfo();

	public void deleteClusterInfo();

	public ClusterInfo getClusterInfo();

	public void remove();
}
