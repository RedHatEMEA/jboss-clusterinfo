package org.jboss.clusterinfo.api;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.management.MalformedObjectNameException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.NotContextException;

import org.jboss.cache.CacheException;
import org.jboss.cache.jmx.CacheJmxWrapperMBean;
import org.jboss.cache.pojo.jmx.PojoCacheJmxWrapperMBean;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.ejb.entity.ClusterInfoID;
import org.jboss.clusterinfo.ejb.message.ClusterMessage;
import org.jboss.clusterinfo.ejb.stateful.ClusterStateful;
import org.jboss.clusterinfo.ejb.stateless.Cluster;
import org.jboss.clusterinfo.util.Constants;
import org.jboss.clusterinfo.util.DataStore;
import org.jboss.clusterinfo.util.DataStoreFactory;
import org.jboss.logging.Logger;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.mx.util.MBeanServerLocator;

public class ClusterInfoAPI {

	public static final String KEY_CLUSTERINFO = "clusterinfo";

	public static final String KEY_SESSION_ID = "sessionID";
	public static final String KEY_SESSION_HOME = "sessionHome";

	public static final String KEY_SYNC_ON_BEHALF_OF_ASYNC = "KEY_SYNC_ON_BEHALF_OF_ASYNC";

	private static final Logger log = Logger.getLogger(ClusterInfoAPI.class
			.getName());
	private static final Logger logPerformance = Logger
			.getLogger(ClusterInfoAPI.class.getName() + Constants.LOG_PERF_TAG);

	public enum Mode {
		Refresh, Update, Reset
	};

	public enum Target {
		AllClusterState, Request, Session, JNDILocal, JNDIHighAvailability, TreeCache, POJOCache, StatefulSessionEJB, EntityEJBSync, EntityEJBAsyncJMS
	}

	public static InitialContext getLocalJNDI() throws NamingException {

		return new InitialContext();
	}

	public static InitialContext getHAJNDI() throws NamingException {

		InitialContext localJNDI = ClusterInfoAPI.getLocalJNDI();
		ClusterInfo clusterInfo = ((Cluster) localJNDI
				.lookup(Cluster.JNDI_NAME)).getClusterInfo();

		if (clusterInfo.isServerIsStandalone()) {
			if (log.isInfoEnabled())
				log.info("retrieving local JNDI");
			return localJNDI;
		}

		Properties HAJNDIProps = new Properties();
		HAJNDIProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		HAJNDIProps.put(Context.URL_PKG_PREFIXES,
				"jboss.naming:org.jnp.interfaces");
		HAJNDIProps.put("jnp.partitionName", clusterInfo
				.getServerPartitionName());

		if (log.isInfoEnabled())
			log.info("retrieving HA JNDI");

		return new InitialContext(HAJNDIProps);

	}

	public static String getName(Object... suffixes) {
		StringBuffer name = new StringBuffer();
		name.append(ClusterInfoAPI.KEY_CLUSTERINFO);
		for (Object section : suffixes) {
			name.append('/');
			name.append(section);
		}
		return name.toString();
	}

	public static void jndiRecursiveCreateSubcontext(InitialContext context,
			Object... dirs) throws NamingException {
		for (int i = -1; i < dirs.length; i++) {
			Object[] dir = new Object[i == 0 ? 1 : i + 1];
			for (int j = 0; j <= i; j++)
				dir[j] = dirs[j];
			try {
				context.createSubcontext(getName(dir));
			} catch (NameAlreadyBoundException e) {
			}
		}
	}

	public static String getGUID() {
		return UUID.randomUUID().toString();
	}

	public static DataStore<String, Object> preprocessSession(
			Collection<ClusterInfoID> clusterInfoclusterInfoRequestIds,
			DataStore<String, Object> request,
			DataStore<String, Object> session,
			DataStore<String, DataStore<String, Object>> sessionStore) {

		for (ClusterInfoID clusterInfoRequestId : clusterInfoclusterInfoRequestIds)
			if (clusterInfoRequestId != null
					&& clusterInfoRequestId.getSessionID() != null
					&& clusterInfoRequestId.getSessionStateTarget() != null
					&& clusterInfoRequestId.getSessionStateTarget().equals(
							ClusterInfoAPI.Target.Session)
					&& sessionStore.get(clusterInfoRequestId.getSessionID()) != null)
				session = (DataStore<String, Object>) sessionStore
						.get(clusterInfoRequestId.getSessionID());

		if (session == null) {
			session = DataStoreFactory.getDataStore();
		}

		for (ClusterInfoID clusterInfoRequestId : clusterInfoclusterInfoRequestIds)
			if (clusterInfoRequestId != null
					&& clusterInfoRequestId.getSessionID() != null
					&& clusterInfoRequestId.getSessionStateTarget() != null)
				session.set(ClusterInfoAPI
						.getName(clusterInfoRequestId.getSessionStateTarget(),
								ClusterInfoAPI.KEY_SESSION_ID),
						clusterInfoRequestId.getSessionID());
		if (session.get(ClusterInfoAPI.getName(ClusterInfoAPI.Target.Session,
				ClusterInfoAPI.KEY_SESSION_ID)) == null)
			session.set(ClusterInfoAPI.getName(ClusterInfoAPI.Target.Session,
					ClusterInfoAPI.KEY_SESSION_ID), ClusterInfoAPI.getGUID());

		return session;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, ClusterInfo> postprocessSession(
			DataStore<String, Object> request,
			DataStore<String, Object> session,
			DataStore<String, DataStore<String, Object>> sessionStore) {

		Map<String, ClusterInfo> clusterInfosResult = (Map<String, ClusterInfo>) request
				.get(ClusterInfoAPI.KEY_CLUSTERINFO);

		if (clusterInfosResult.get(ClusterInfoAPI.Target.Session.toString()) == null)
			clusterInfosResult.put(ClusterInfoAPI.Target.Session.toString(),
					new ClusterInfo(session.get(
							ClusterInfoAPI.getName(
									ClusterInfoAPI.Target.Session,
									ClusterInfoAPI.KEY_SESSION_ID)).toString(),
							ClusterInfoAPI.Mode.Refresh,
							ClusterInfoAPI.Target.Session, null, null, null,
							null, null, false, null));

		sessionStore.set(session.get(
				ClusterInfoAPI.getName(ClusterInfoAPI.Target.Session,
						ClusterInfoAPI.KEY_SESSION_ID)).toString(), session);

		return clusterInfosResult;
	}

	public static void processRequest(Mode mode, Target target,
			DataStore<String, Object> request, DataStore<String, Object> session) {

		try {
			ClusterInfo clusterInfo = ((Cluster) getLocalJNDI().lookup(
					Cluster.JNDI_NAME)).getClusterInfo();
			clusterInfo.setID(new ClusterInfoID(null, target));
			clusterInfo.setRequestMode(mode);

			if (logPerformance.isInfoEnabled())
				logPerformance.info("pre process request mode [" + mode
						+ "] target [" + target + "]");

			processRequest(clusterInfo, request, session);

			if (logPerformance.isInfoEnabled())
				logPerformance.info("post process request mode [" + mode
						+ "] target [" + target + "]");

		} catch (Exception e) {
			throw new RuntimeException("could not process request [" + mode
					+ "] on target [" + target + "]", e);
		}
	}

	public static void processRequest(ClusterInfo clusterInfo,
			DataStore<String, Object> request, DataStore<String, Object> session) {

		Mode mode = clusterInfo.getRequestMode();
		Target target = clusterInfo.getID().getSessionStateTarget();

		InitialContext localJNDI = null;
		InitialContext HAJNDI = null;

		try {

			localJNDI = getLocalJNDI();
			HAJNDI = getHAJNDI();

			if (target.equals(Target.AllClusterState)) {
				for (Target targets : Target.values()) {
					if (!targets.equals(Target.AllClusterState)) {
						clusterInfo.getID().setSessionStateTarget(targets);
						processRequest(clusterInfo.clone(), request, session);
					}
				}
			} else {

				if (clusterInfo.getID().getSessionID() == null) {
					String sessionID = (String) session.get(ClusterInfoAPI
							.getName(target, ClusterInfoAPI.KEY_SESSION_ID));
					clusterInfo.getID().setSessionID(
							sessionID == null ? getGUID() : sessionID);
				}

				switch (mode) {
				case Reset:
					session.remove(getName(target, KEY_SESSION_ID));
					processRequestReset(clusterInfo, request, session, target,
							localJNDI, HAJNDI);
					break;
				case Update:
					session.set(getName(target, KEY_SESSION_ID), clusterInfo
							.getID().getSessionID());
					processRequestUpdate(clusterInfo, request, session, target,
							localJNDI, HAJNDI);
					break;
				case Refresh:
					if (!target.equals(Target.Request)
							&& session.get(getName(target, KEY_SESSION_ID)) != null) {
						clusterInfo.getID().setSessionID(
								(String) session.get(getName(target,
										KEY_SESSION_ID)));
					}
					processRequestRefresh(clusterInfo, request, session,
							target, localJNDI, HAJNDI);
					break;
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("could not process request [" + mode
					+ "] on target [" + target + "]", e);
		}
	}

	@SuppressWarnings("unchecked")
	private static void processRequestReset(ClusterInfo clusterInfo,
			DataStore<String, Object> request,
			DataStore<String, Object> session, Target target,
			InitialContext localJNDI, InitialContext HAJNDI)
			throws NamingException, MalformedObjectNameException,
			CacheException {

		switch (target) {
		case Session: {
			session.remove(getName(Target.Session, KEY_SESSION_HOME));
			break;
		}
		case StatefulSessionEJB: {
			ClusterStateful clusterStateful = (ClusterStateful) session
					.get(getName(Target.StatefulSessionEJB, KEY_SESSION_HOME));
			if (clusterStateful != null) {
				clusterStateful.remove();
				session.remove(getName(Target.StatefulSessionEJB,
						KEY_SESSION_HOME));
			}
			break;
		}
		case JNDILocal: {
			try {
				localJNDI.unbind(getName(Target.JNDILocal, KEY_SESSION_ID,
						clusterInfo.getID().getSessionID()));
			} catch (NameNotFoundException e) {
			}
			break;
		}
		case JNDIHighAvailability: {
			try {
				HAJNDI.unbind(getName(Target.JNDIHighAvailability,
						KEY_SESSION_ID, clusterInfo.getID().getSessionID()));
			} catch (NameNotFoundException e) {
			} catch (NotContextException e) {
			}
			break;
		}
		case TreeCache: {
			((CacheJmxWrapperMBean<String, ClusterInfo>) MBeanProxyExt.create(
					CacheJmxWrapperMBean.class, "jboss.cache:service="
							+ KEY_CLUSTERINFO + Target.TreeCache,
					MBeanServerLocator.locateJBoss())).getCache().removeNode(
					getName(Target.TreeCache, KEY_SESSION_ID, clusterInfo
							.getID().getSessionID()));
			break;
		}
		case POJOCache: {
			((PojoCacheJmxWrapperMBean) MBeanProxyExt.create(
					PojoCacheJmxWrapperMBean.class, "jboss.cache:service="
							+ KEY_CLUSTERINFO + Target.POJOCache,
					MBeanServerLocator.locateJBoss())).getPojoCache().detach(
					getName(Target.POJOCache, KEY_SESSION_ID, clusterInfo
							.getID().getSessionID()));
			break;
		}
		case EntityEJBSync: {
			if (request.get(ClusterInfoAPI.KEY_SYNC_ON_BEHALF_OF_ASYNC) != null)
				clusterInfo.getID().setSessionStateTarget(
						Target.EntityEJBAsyncJMS);
			ClusterStateful clusterStateful = (ClusterStateful) localJNDI
					.lookup(ClusterStateful.JNDI_NAME);
			clusterStateful.cacheClusterInfo(clusterInfo);
			clusterStateful.deleteClusterInfo();
			clusterStateful.remove();
			break;
		}
		case EntityEJBAsyncJMS: {
			Connection jmsConnection = null;
			Session jmsSession = null;
			MessageProducer jmsProducer = null;
			try {
				jmsConnection = ((ConnectionFactory) localJNDI
						.lookup(ClusterMessage.JNDI_CONNECTION_FACTORY))
						.createConnection();
				jmsSession = jmsConnection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				jmsProducer = jmsSession.createProducer((Destination) localJNDI
						.lookup(ClusterMessage.JNDI_DESTINATION));
				jmsProducer.send(jmsSession.createObjectMessage(clusterInfo));
			} catch (Exception e) {
				throw new RuntimeException(
						"unexpected issue sending JMS message", e);
			} finally {
				try {
					if (jmsProducer != null)
						jmsProducer.close();
					if (jmsSession != null)
						jmsSession.close();
					if (jmsConnection != null)
						jmsConnection.close();
				} catch (Exception e) {
				}
			}
			break;
		}
		}
	}

	@SuppressWarnings("unchecked")
	private static void processRequestUpdate(ClusterInfo clusterInfo,
			DataStore<String, Object> request,
			DataStore<String, Object> session, Target target,
			InitialContext localJNDI, InitialContext HAJNDI)
			throws NamingException, MalformedObjectNameException,
			CacheException {

		switch (target) {
		case Session: {
			session.set(getName(Target.Session, KEY_SESSION_HOME), clusterInfo);
			break;
		}
		case StatefulSessionEJB: {
			ClusterStateful clusterStateful = (ClusterStateful) session
					.get(getName(Target.StatefulSessionEJB, KEY_SESSION_HOME));
			if (clusterStateful == null) {
				clusterStateful = (ClusterStateful) HAJNDI
						.lookup(ClusterStateful.JNDI_NAME);
				session.set(
						getName(Target.StatefulSessionEJB, KEY_SESSION_HOME),
						clusterStateful);
			}
			clusterStateful.cacheClusterInfo(clusterInfo);
			break;
		}
		case JNDILocal: {
			jndiRecursiveCreateSubcontext(localJNDI, Target.JNDILocal,
					KEY_SESSION_ID);
			localJNDI.rebind(getName(Target.JNDILocal, KEY_SESSION_ID,
					clusterInfo.getID().getSessionID()), clusterInfo);
			break;
		}
		case JNDIHighAvailability: {
			jndiRecursiveCreateSubcontext(HAJNDI, Target.JNDIHighAvailability,
					KEY_SESSION_ID);
			HAJNDI.rebind(getName(Target.JNDIHighAvailability, KEY_SESSION_ID,
					clusterInfo.getID().getSessionID()), clusterInfo);
			break;
		}
		case TreeCache: {
			((CacheJmxWrapperMBean<String, ClusterInfo>) MBeanProxyExt.create(
					CacheJmxWrapperMBean.class, "jboss.cache:service="
							+ KEY_CLUSTERINFO + Target.TreeCache,
					MBeanServerLocator.locateJBoss())).getCache().put(
					getName(Target.TreeCache, KEY_SESSION_ID, clusterInfo
							.getID().getSessionID()),
					clusterInfo.getID().getSessionID(), clusterInfo);
			break;
		}
		case POJOCache: {
			((PojoCacheJmxWrapperMBean) MBeanProxyExt.create(
					PojoCacheJmxWrapperMBean.class, "jboss.cache:service="
							+ KEY_CLUSTERINFO + Target.POJOCache,
					MBeanServerLocator.locateJBoss())).getPojoCache().attach(
					getName(Target.POJOCache, KEY_SESSION_ID, clusterInfo
							.getID().getSessionID()), clusterInfo);
			break;
		}
		case EntityEJBSync: {
			if (request.get(ClusterInfoAPI.KEY_SYNC_ON_BEHALF_OF_ASYNC) != null)
				clusterInfo.getID().setSessionStateTarget(
						Target.EntityEJBAsyncJMS);
			ClusterStateful clusterStateful = (ClusterStateful) localJNDI
					.lookup(ClusterStateful.JNDI_NAME);
			clusterStateful.cacheClusterInfo(clusterInfo);
			clusterStateful.saveClusterInfo();
			clusterStateful.remove();
			break;
		}
		case EntityEJBAsyncJMS: {
			Connection jmsConnection = null;
			Session jmsSession = null;
			MessageProducer jmsProducer = null;
			try {
				jmsConnection = ((ConnectionFactory) localJNDI
						.lookup(ClusterMessage.JNDI_CONNECTION_FACTORY))
						.createConnection();
				jmsSession = jmsConnection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				jmsProducer = jmsSession.createProducer((Destination) localJNDI
						.lookup(ClusterMessage.JNDI_DESTINATION));
				jmsProducer.send(jmsSession.createObjectMessage(clusterInfo));
			} catch (Exception e) {
				throw new RuntimeException(
						"unexpected issue sending JMS message", e);
			} finally {
				try {
					if (jmsProducer != null)
						jmsProducer.close();
					if (jmsSession != null)
						jmsSession.close();
					if (jmsConnection != null)
						jmsConnection.close();
				} catch (Exception e) {
				}
			}
			break;
		}
		}
	}

	@SuppressWarnings("unchecked")
	private static void processRequestRefresh(ClusterInfo clusterInfo,
			DataStore<String, Object> request,
			DataStore<String, Object> session, Target target,
			InitialContext localJNDI, InitialContext HAJNDI)
			throws NamingException, MalformedObjectNameException,
			CacheException {

		Map<String, ClusterInfo> clusterInfos = (Map<String, ClusterInfo>) request
				.get(ClusterInfoAPI.KEY_CLUSTERINFO);

		if (clusterInfos == null) {
			clusterInfos = new LinkedHashMap<String, ClusterInfo>();
			for (Target targets : Target.values())
				clusterInfos.put(targets.toString(), null);
			request.set(ClusterInfoAPI.KEY_CLUSTERINFO, clusterInfos);
		}

		switch (target) {
		case Request: {
			if (clusterInfo != null)
				clusterInfos.put(Target.Request.toString(), clusterInfo);
			break;
		}
		case Session: {
			if (session.get(getName(Target.Session, KEY_SESSION_HOME)) != null)
				clusterInfos.put(Target.Session.toString(),
						((ClusterInfo) session.get(getName(Target.Session,
								KEY_SESSION_HOME))));
			break;
		}
		case StatefulSessionEJB: {
			if (session
					.get(getName(Target.StatefulSessionEJB, KEY_SESSION_HOME)) != null)
				clusterInfos.put(Target.StatefulSessionEJB.toString(),
						((ClusterStateful) session.get(getName(
								Target.StatefulSessionEJB, KEY_SESSION_HOME)))
								.getClusterInfo());
			break;
		}
		case JNDILocal: {
			try {
				clusterInfos.put(Target.JNDILocal.toString(),
						((ClusterInfo) localJNDI.lookup(getName(
								Target.JNDILocal, KEY_SESSION_ID, clusterInfo
										.getID().getSessionID()))));
			} catch (NameNotFoundException e) {
			} catch (ClassCastException e) {
			}
			break;
		}
		case JNDIHighAvailability: {
			try {
				clusterInfos.put(Target.JNDIHighAvailability.toString(),
						((ClusterInfo) HAJNDI.lookup(getName(
								Target.JNDIHighAvailability, KEY_SESSION_ID,
								clusterInfo.getID().getSessionID()))));
			} catch (NameNotFoundException e) {
			} catch (ClassCastException e) {
			}
			break;
		}
		case TreeCache: {
			clusterInfos
					.put(
							Target.TreeCache.toString(),
							(ClusterInfo) ((CacheJmxWrapperMBean<String, ClusterInfo>) MBeanProxyExt
									.create(CacheJmxWrapperMBean.class,
											"jboss.cache:service="
													+ KEY_CLUSTERINFO
													+ Target.TreeCache,
											MBeanServerLocator.locateJBoss()))
									.getCache().get(
											getName(Target.TreeCache,
													KEY_SESSION_ID, clusterInfo
															.getID()
															.getSessionID()),
											clusterInfo.getID().getSessionID()));
			break;
		}
		case POJOCache: {
			clusterInfos
					.put(
							Target.POJOCache.toString(),
							(ClusterInfo) ((PojoCacheJmxWrapperMBean) MBeanProxyExt
									.create(PojoCacheJmxWrapperMBean.class,
											"jboss.cache:service="
													+ KEY_CLUSTERINFO
													+ Target.POJOCache,
											MBeanServerLocator.locateJBoss()))
									.getPojoCache().find(
											getName(Target.POJOCache,
													KEY_SESSION_ID, clusterInfo
															.getID()
															.getSessionID())));
			break;
		}
		case EntityEJBSync: {
			ClusterStateful clusterStateful = (ClusterStateful) localJNDI
					.lookup(ClusterStateful.JNDI_NAME);
			clusterStateful.cacheClusterInfo(clusterInfo);
			clusterStateful.loadClusterInfo();
			if (clusterStateful.getClusterInfo() != null)
				clusterInfos.put(Target.EntityEJBSync.toString(),
						clusterStateful.getClusterInfo());
			clusterStateful.remove();
			break;
		}
		case EntityEJBAsyncJMS: {
			ClusterStateful clusterStateful = (ClusterStateful) localJNDI
					.lookup(ClusterStateful.JNDI_NAME);
			clusterStateful.cacheClusterInfo(clusterInfo);
			clusterStateful.loadClusterInfo();
			if (clusterStateful.getClusterInfo() != null)
				clusterInfos.put(Target.EntityEJBAsyncJMS.toString(),
						clusterStateful.getClusterInfo());
			clusterStateful.remove();
			break;
		}
		}
	}

}
