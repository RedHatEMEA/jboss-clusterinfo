package org.jboss.clusterinfo.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class ClusterInfoLoginModule extends UsernamePasswordLoginModule {

	private static final String OPT_ROLE_DELIM = ",";
	private static final String OPT_USERS = "clusterinfo-users";
	private static final String OPT_ROLES = "clusterinfo-roles";

	private Properties users;
	private Properties roles;

	private static final Logger log = Logger
			.getLogger(ClusterInfoLoginModule.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {

		try {
			if (log.isTraceEnabled()) {
				log.trace("pre initialise");
			}
			super.initialize(subject, callbackHandler, sharedState, options);
			users = loadProps(OPT_USERS, (String) options.get(OPT_USERS));
			roles = loadProps(OPT_ROLES, (String) options.get(OPT_ROLES));
		} catch (Throwable t) {
			log.error("could not initialise login module", t);
			users = roles = new Properties();
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("post initialise");
			}
		}
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		String password = null;
		try {
			if (log.isTraceEnabled()) {
				log.trace("pre retrieve password for user [" + getUsername()
						+ "]");
			}
			password = users.getProperty(getUsername());
			if (password == null)
				throw new LoginException(
						"could not retrieve password for user ["
								+ getUsername() + "]");
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("post retrieve password for user [" + getUsername()
						+ "] with password [" + (password != null) + "]");
			}
		}
		return password;
	}

	@Override
	protected Group[] getRoleSets() throws LoginException {

		Group group = new SimpleGroup("Roles");
		try {
			if (log.isTraceEnabled()) {
				log.trace("pre role set retrieval for user [" + getUsername()
						+ "]");
			}
			if (roles.getProperty(getUsername()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(roles
						.getProperty(getUsername()), OPT_ROLE_DELIM);
				while (tokenizer.hasMoreTokens()) {
					String role = tokenizer.nextToken();
					Principal rolePrincipal = new SimplePrincipal(role);
					group.addMember(rolePrincipal);
				}
			}
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("post role set retrieval for user [" + getUsername()
						+ "] and roles [" + group + "]");
			}
		}

		return new Group[] { group };
	}

	private Properties loadProps(String propsName, String propsSrc)
			throws LoginException, IOException {

		Properties props = new Properties();
		try {
			if (log.isTraceEnabled()) {
				log.trace("pre load [" + propsName + "]");
			}
			if (propsSrc == null)
				throw new LoginException("module properties [" + propsName
						+ "] not found");
			InputStream propsStream = getClass().getResourceAsStream(
					"/" + propsSrc);
			try {
				props.load(propsStream);
			} finally {
				if (propsStream != null)
					propsStream.close();
			}
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("post load [" + propsName + "]");
			}
		}
		return props;
	}

}
