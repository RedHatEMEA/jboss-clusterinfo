package org.jboss.clusterinfo.ws;

import java.util.Collection;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.ejb.entity.ClusterInfoID;

public interface ClusterInfoWS {

	public Collection<ClusterInfo> action(ClusterInfoAPI.Target target,
			ClusterInfoAPI.Mode mode, Collection<ClusterInfoID> requestIds);

}