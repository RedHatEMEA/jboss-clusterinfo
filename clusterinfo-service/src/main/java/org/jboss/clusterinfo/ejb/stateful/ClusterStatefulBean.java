package org.jboss.clusterinfo.ejb.stateful;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.util.Constants;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.ejb3.cache.Optimized;
import org.jboss.logging.Logger;

@Stateful
@RemoteBinding(jndiBinding = ClusterStateful.JNDI_NAME)
public class ClusterStatefulBean implements ClusterStateful, Serializable,
		Optimized {

	private static final long serialVersionUID = 5313622877709025523L;

	private static final Logger logPerformance = Logger
			.getLogger(ClusterInfoAPI.class.getName() + Constants.LOG_PERF_TAG);

	@PersistenceContext(unitName = "clusterinfo")
	private EntityManager entityManager;

	private ClusterInfo clusterInfo = null;

	private boolean isValid() {
		return clusterInfo != null && clusterInfo.getID() != null
				&& clusterInfo.getID().getSessionID() != null
				&& clusterInfo.getID().getSessionStateTarget() != null;
	}

	public void cacheClusterInfo(ClusterInfo clusterInfo) {
		if (logPerformance.isInfoEnabled())
			logPerformance.info("pre process cache");
		entityManager.clear();
		this.clusterInfo = clusterInfo == null ? null : clusterInfo.clone();
		if (logPerformance.isInfoEnabled())
			logPerformance.info("post process cache");
	}

	public void deleteClusterInfo() {
		if (logPerformance.isInfoEnabled())
			logPerformance.info("pre process delete");
		loadClusterInfo();
		if (isValid())
			entityManager.remove(clusterInfo);
		clusterInfo = null;
		if (logPerformance.isInfoEnabled())
			logPerformance.info("post process delete");
	}

	public void loadClusterInfo() {
		if (logPerformance.isInfoEnabled())
			logPerformance.info("pre process load");
		if (isValid())
			clusterInfo = (ClusterInfo) entityManager.find(ClusterInfo.class,
					clusterInfo.getID());
		if (logPerformance.isInfoEnabled())
			logPerformance.info("post process load");
	}

	public void saveClusterInfo() {
		if (logPerformance.isInfoEnabled())
			logPerformance.info("pre process save");
		if (isValid()) {
			clusterInfo = entityManager.merge(clusterInfo);
		}
		if (logPerformance.isInfoEnabled())
			logPerformance.info("post process save");

	}

	public ClusterInfo getClusterInfo() {
		return clusterInfo;
	}

	@Remove
	public void remove() {
		if (logPerformance.isInfoEnabled())
			logPerformance.info("pre process remove");
		entityManager.flush();
		entityManager.clear();
		this.clusterInfo = null;
		if (logPerformance.isInfoEnabled())
			logPerformance.info("post process remove");
	}

	public boolean isModified() {
		return true;
	}

}
