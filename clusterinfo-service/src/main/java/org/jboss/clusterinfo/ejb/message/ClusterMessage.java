package org.jboss.clusterinfo.ejb.message;

import javax.annotation.security.RunAs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;
import org.jboss.clusterinfo.ejb.entity.ClusterInfo;
import org.jboss.clusterinfo.util.DataStore;
import org.jboss.clusterinfo.util.DataStoreFactory;
import org.jboss.ejb3.annotation.Depends;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ClusterMessage.JNDI_DESTINATION),
		@ActivationConfigProperty(propertyName = "DLQMaxResent", propertyValue = "10") })
@RunAs("ClusterInfoAdmin")
@Depends("jboss.messaging.destination:service=Queue,name=clusterinfo")
public class ClusterMessage implements MessageListener {

	public static final String JNDI_CONNECTION_FACTORY = "ConnectionFactory";
	public static final String JNDI_DESTINATION = "queue/"
			+ ClusterInfoAPI.KEY_CLUSTERINFO;

	public void onMessage(Message message) {

		try {

			ClusterInfo clusterInfo = (ClusterInfo) ((ObjectMessage) message)
					.getObject();

			DataStore<String, Object> request = DataStoreFactory.getDataStore();
			request.set(ClusterInfoAPI.KEY_SYNC_ON_BEHALF_OF_ASYNC,
					Boolean.TRUE);
			clusterInfo.getID().setSessionStateTarget(Target.EntityEJBSync);
			ClusterInfoAPI.processRequest(clusterInfo, request,
					DataStoreFactory.getDataStore());

		} catch (Exception e) {
			throw new RuntimeException(
					"unexpected error during message processing", e);
		}

	}

}
