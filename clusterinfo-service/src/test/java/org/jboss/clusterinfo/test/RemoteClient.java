package org.jboss.clusterinfo.test;

import javax.naming.InitialContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jboss.clusterinfo.ejb.stateless.Cluster;

public class RemoteClient {

	private static Logger log = Logger.getLogger(RemoteClient.class);

	public static void main(String[] args) {

		try {
			Cluster cluster = (Cluster) new InitialContext()
					.lookup(Cluster.JNDI_NAME);

			if (log.isEnabledFor(Level.INFO)) {
				log.info("Request serviced by: " + cluster.getClusterInfo());
			}
		} catch (Exception e) {
			throw new RuntimeException("failed to get remote cluster info", e);
		}

	}

}
