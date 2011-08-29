package org.jboss.clusterinfo.ws;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Mode;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.ejb.entity.ClusterInfoID;
import org.jboss.clusterinfo.util.DataStore;
import org.jboss.clusterinfo.util.DataStoreFactory;

@WebService
public class ClusterInfoWSBean implements ClusterInfoWS {

	@Resource
	WebServiceContext webServiceContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.clusterinfo.ws.ClusterInfoWS#action(org.jboss.clusterinfo.api
	 * .ClusterInfoAPI.Target, org.jboss.clusterinfo.api.ClusterInfoAPI.Mode)
	 */
	@SuppressWarnings("unchecked")
	@WebMethod
	public Collection<ClusterInfo> action(
			@WebParam(name = "target") Target target,
			@WebParam(name = "mode") Mode mode,
			@WebParam(name = "clusterinfoIds") Collection<ClusterInfoID> clusterInfoclusterInfoRequestIds) {

		if (target == null || mode == null)
			throw new RuntimeException("Required parameter "
					+ (target == null ? "[target] " : "")
					+ (mode == null ? "[mode] " : "") + "is null or invalid");

		Map<String, ClusterInfo> clusterInfosResult = null;
		try {

			clusterInfoclusterInfoRequestIds = clusterInfoclusterInfoRequestIds == null ? Collections.EMPTY_LIST
					: clusterInfoclusterInfoRequestIds;

			DataStore<String, Object> request = DataStoreFactory.getDataStore();
			DataStore<String, Object> session = null;

			DataStore<String, DataStore<String, Object>> sessionStore = DataStoreFactory
					.getDataStore(
							"jboss.cache:service=clusterinfoWSSessionCache",
							ClusterInfoAPI.Target.Session.toString());

			// lookup/create session, transfer request IDs to session
			session = ClusterInfoAPI.preprocessSession(
					clusterInfoclusterInfoRequestIds, request, session,
					sessionStore);

			// process the target for a given mode
			ClusterInfoAPI.processRequest(mode, target, request, session);

			// marshal the state into the request
			ClusterInfoAPI.processRequest(Mode.Refresh, Target.AllClusterState,
					request, session);

			// pass session into response
			clusterInfosResult = ClusterInfoAPI.postprocessSession(request,
					session, sessionStore);

		} catch (Throwable t) {
			throw new RuntimeException("unexpected exception caught"
					+ (t.getMessage() != null ? " with message ["
							+ t.getMessage() + "]" : ""), t);
		}

		return clusterInfosResult == null ? Collections.EMPTY_LIST
				: clusterInfosResult.values();
	}
}