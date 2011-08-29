package org.jboss.clusterinfo.ejb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.cache.pojo.annotation.Replicable;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Mode;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;

@Entity
@Table(name = "CLUSTER_INFO")
@Replicable
public class ClusterInfo implements Serializable {

	private static final long serialVersionUID = -8737812712704346747L;

	private ClusterInfoID ID;
	private Mode requestMode;
	private String serverName;
	private String serverHost;
	private String serverVersion;
	private String serverBindAddress;
	private String serverPartitionName;
	private boolean serverIsStandalone;
	private Date timestamp;

	public ClusterInfo() {
		super();
		ID = new ClusterInfoID();
		timestamp = new Date();
	}

	public ClusterInfo(String sessionID, Mode requestMode,
			Target sessionStateTarget, String serverName, String serverHost,
			String serverVersion, String serverBindAddress,
			String serverPartitionName, boolean serverIsStandalone,
			Date timestamp) {
		this();
		this.ID = new ClusterInfoID(sessionID, sessionStateTarget);
		this.requestMode = requestMode;
		this.serverName = serverName;
		this.serverHost = serverHost;
		this.serverVersion = serverVersion;
		this.serverBindAddress = serverBindAddress;
		this.serverPartitionName = serverPartitionName;
		this.serverIsStandalone = serverIsStandalone;
		this.timestamp = timestamp;
	}

	@Override
	public ClusterInfo clone() {
		return new ClusterInfo(ID.getSessionID(), requestMode, ID
				.getSessionStateTarget(), serverName, serverHost,
				serverVersion, serverBindAddress, serverPartitionName,
				serverIsStandalone, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof ClusterInfo
				&& ((ClusterInfo) obj).toString().equals(toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		try {
			return BeanUtils.describe(this).toString();
		} catch (Exception e) {
			throw new RuntimeException("Could not describe bean", e);
		}
	}

	@EmbeddedId
	public ClusterInfoID getID() {
		return ID;
	}

	public void setID(ClusterInfoID ID) {
		this.ID = ID;
	}

	@Transient
	public String getSessionID() {
		return ID.getSessionID();
	}

	@Transient
	public Target getSessionStateTarget() {
		return ID.getSessionStateTarget();
	}

	public Mode getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(Mode requestMode) {
		this.requestMode = requestMode;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getServerBindAddress() {
		return serverBindAddress;
	}

	public void setServerBindAddress(String serverBindAddress) {
		this.serverBindAddress = serverBindAddress;
	}

	public String getServerPartitionName() {
		return serverPartitionName;
	}

	public void setServerPartitionName(String serverPartitionName) {
		this.serverPartitionName = serverPartitionName;
	}

	public boolean isServerIsStandalone() {
		return serverIsStandalone;
	}

	public void setServerIsStandalone(boolean serverIsStandalone) {
		this.serverIsStandalone = serverIsStandalone;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
