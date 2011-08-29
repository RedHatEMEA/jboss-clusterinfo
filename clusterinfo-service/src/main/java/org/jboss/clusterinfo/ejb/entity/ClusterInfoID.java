package org.jboss.clusterinfo.ejb.entity;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.jboss.cache.pojo.annotation.Replicable;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;

@Replicable
public class ClusterInfoID implements Serializable {

	private static final String DELIM = "_";

	private static final long serialVersionUID = 7744353724001438007L;

	private String sessionID;
	private Target sessionStateTarget;

	public ClusterInfoID() {
		super();
	}

	public ClusterInfoID(String sessionID, Target sessionStateTarget) {
		super();
		this.sessionID = sessionID;
		this.sessionStateTarget = sessionStateTarget;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Enumerated(EnumType.ORDINAL)
	public Target getSessionStateTarget() {
		return sessionStateTarget;
	}

	public void setSessionStateTarget(Target sessionStateTarget) {
		this.sessionStateTarget = sessionStateTarget;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof ClusterInfoID
				&& ((ClusterInfoID) obj).toString().equals(toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		return sessionID + DELIM + sessionStateTarget;
	}

	public static ClusterInfoID valueOf(String string) {

		if (string == null || string.equals(""))
			return null;

		int delimIndex = string.lastIndexOf(DELIM);
		if (delimIndex == -1 || delimIndex == 0
				|| delimIndex == string.length())
			return null;

		String sessionID = string.substring(0, delimIndex);
		Target sessionStateTarget = null;

		try {
			sessionStateTarget = Target.valueOf(string
					.substring(delimIndex + 1));
		} catch (Exception e) {
			return null;
		}

		return new ClusterInfoID(sessionID, sessionStateTarget);
	}
}