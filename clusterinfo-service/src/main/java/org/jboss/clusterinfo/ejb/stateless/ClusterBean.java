package org.jboss.clusterinfo.ejb.stateless;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.management.InstanceNotFoundException;
import javax.management.ObjectName;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.util.Constants;
import org.jboss.ejb3.annotation.RemoteBinding;
import org.jboss.logging.Logger;
import org.jboss.mx.util.MBeanServerLocator;

@Stateless
@RemoteBinding(jndiBinding = Cluster.JNDI_NAME)
public class ClusterBean implements Cluster, Serializable {

	private static final long serialVersionUID = -4083180821897427600L;

	private static final Logger logPerformance = Logger
			.getLogger(ClusterInfoAPI.class.getName() + Constants.LOG_PERF_TAG);

	public ClusterInfo getClusterInfo() {

		ClusterInfo clusterInfo = new ClusterInfo();
		try {

			if (logPerformance.isInfoEnabled())
				logPerformance.info("pre process get");
			
			Hashtable<String, String> props = new Hashtable<String, String>();
			props.put("type", "ServerConfig");
			clusterInfo.setServerName((String) MBeanServerLocator.locateJBoss()
					.getAttribute(new ObjectName("jboss.system", props),
							"ServerName"));

			props.clear();
			props.put("service", "Naming");
			clusterInfo.setServerBindAddress((String) MBeanServerLocator
					.locateJBoss().getAttribute(new ObjectName("jboss", props),
							"BindAddress"));

			props.clear();
			props.put("type", "Server");
			clusterInfo.setServerVersion((String) MBeanServerLocator
					.locateJBoss().getAttribute(
							new ObjectName("jboss.system", props),
							"VersionNumber"));

			props.clear();
			props.put("type", "ServerInfo");
			clusterInfo.setServerHost((String) MBeanServerLocator.locateJBoss()
					.getAttribute(new ObjectName("jboss.system", props),
							"HostName"));

			props.clear();
			props.put("service", "HAJNDI");
			try {
				clusterInfo.setServerPartitionName((String) MBeanServerLocator
						.locateJBoss()
						.getAttribute(new ObjectName("jboss", props),
								"PartitionName"));
			} catch (InstanceNotFoundException e) {
				clusterInfo.setServerIsStandalone(true);
			}

			clusterInfo.setTimestamp(new Date());
			

		} catch (Exception e) {
			throw new RuntimeException("Could not retrieve cluster info", e);
		}
		finally {
			
			if (logPerformance.isInfoEnabled())
				logPerformance.info("post process get");
			
		}

		return clusterInfo;
	}

}
