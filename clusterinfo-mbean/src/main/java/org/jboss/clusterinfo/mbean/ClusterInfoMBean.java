package org.jboss.clusterinfo.mbean;

import org.jboss.system.ServiceMBean;

public interface ClusterInfoMBean extends ServiceMBean {

	public String getTreeCacheMBeanServiceLocator();

	public void setTreeCacheMBeanServiceLocator(
			String treeCacheMBeanServiceLocator);

	public String action(String target, String mode, String requestIds);

}
