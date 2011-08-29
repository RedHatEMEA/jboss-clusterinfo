package org.jboss.clusterinfo.mbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Mode;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;
import org.jboss.clusterinfo.ejb.entity.ClusterInfoID;
import org.jboss.clusterinfo.util.DataStore;
import org.jboss.clusterinfo.util.DataStoreFactory;
import org.jboss.system.ServiceMBeanSupport;

public class ClusterInfo extends ServiceMBeanSupport implements
		ClusterInfoMBean {

	private static final String DELIM = ",";

	private String treeCacheMBeanServiceLocator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jboss.system.ServiceMBeanSupport#getName()
	 */
	@Override
	public String getName() {
		return "ClusterInfo";
	}

	public String getTreeCacheMBeanServiceLocator() {
		return treeCacheMBeanServiceLocator;
	}

	public void setTreeCacheMBeanServiceLocator(
			String treeCacheMBeanServiceLocator) {
		this.treeCacheMBeanServiceLocator = treeCacheMBeanServiceLocator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.clusterinfo.mbean.ClusterInfoMBean#action(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String action(String target, String mode, String requestIds) {

		ClusterInfoAPI.Target targetEnum = null;
		ClusterInfoAPI.Mode modeEnum = null;
		try {
			targetEnum = ClusterInfoAPI.Target.valueOf(
					ClusterInfoAPI.Target.class, target);
			modeEnum = ClusterInfoAPI.Mode.valueOf(ClusterInfoAPI.Mode.class,
					mode);
		} catch (Exception e) {
			return "Required parameter "
					+ (targetEnum == null ? "[target] " : "")
					+ (modeEnum == null ? "[mode] " : "")
					+ "is null or invalid";
		}

		Collection<ClusterInfoID> clusterInfoclusterInfoRequestIds = parseClusterInfoIDString(requestIds);

		DataStore<String, Object> request = DataStoreFactory.getDataStore();
		DataStore<String, Object> session = null;

		DataStore<String, DataStore<String, Object>> sessionStore = DataStoreFactory
				.getDataStore(treeCacheMBeanServiceLocator,
						ClusterInfoAPI.Target.Session.toString());

		// lookup/create session, transfer request IDs to session
		session = ClusterInfoAPI.preprocessSession(
				clusterInfoclusterInfoRequestIds, request, session,
				sessionStore);

		// process the target for a given mode
		ClusterInfoAPI.processRequest(modeEnum, targetEnum, request, session);

		// marshal the state into the request
		ClusterInfoAPI.processRequest(Mode.Refresh, Target.AllClusterState,
				request, session);

		return toStringClusterInfo(ClusterInfoAPI.postprocessSession(request,
				session, sessionStore));
	}

	private static Collection<ClusterInfoID> parseClusterInfoIDString(
			String list) {

		Collection<ClusterInfoID> clusterInfoIDs = new ArrayList<ClusterInfoID>();

		StringTokenizer listTokens = new StringTokenizer(list == null ? ""
				: list, DELIM);
		while (listTokens.hasMoreTokens()) {
			ClusterInfoID clusterInfoID = ClusterInfoID.valueOf(listTokens
					.nextToken());
			if (clusterInfoID != null)
				clusterInfoIDs.add(clusterInfoID);
		}

		return clusterInfoIDs;
	}

	private static String toStringClusterInfo(
			Map<String, org.jboss.clusterinfo.ejb.entity.ClusterInfo> results) {

		StringBuffer string = new StringBuffer();

		string.append("Session IDs:\n");
		for (String key : results.keySet()) {
			if (results.get(key) != null) {
				string.append(results.get(key).getID());
				string.append(DELIM);
			}
		}
		if (string.length() > 0)
			string.deleteCharAt(string.length() - 1);
		string.append("\n\n");

		for (String key : results.keySet()) {
			if (results.get(key) != null) {
				string.append(key);
				string.append(":\n");
				string.append(results.get(key));
				string.append("\n\n");
			}
		}

		return string.toString();

	}
}
