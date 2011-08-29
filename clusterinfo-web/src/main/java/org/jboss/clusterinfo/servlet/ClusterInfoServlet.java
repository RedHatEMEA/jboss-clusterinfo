package org.jboss.clusterinfo.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.clusterinfo.api.ClusterInfoAPI;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Mode;
import org.jboss.clusterinfo.api.ClusterInfoAPI.Target;
import org.jboss.clusterinfo.servlet.util.DataStoreFactory;
import org.jboss.logging.Logger;

@SuppressWarnings("serial")
public class ClusterInfoServlet extends HttpServlet {

	private static final String PARAM_MODE = "mode";
	private static final String PARAM_TARGET = "target";

	private static final String JSP_PATH = "/";

	private static final Logger log = Logger.getLogger(ClusterInfoServlet.class
			.getName());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	private void doGetPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {

			if (log.isInfoEnabled())
				log.info("http request received");

			// marshal request type
			Mode mode = request.getParameter(PARAM_MODE) == null ? Mode.Refresh
					: Mode.valueOf(ClusterInfoAPI.Mode.class, request
							.getParameter(PARAM_MODE));
			Target target = request.getParameter(PARAM_TARGET) == null ? Target.AllClusterState
					: Target.valueOf(ClusterInfoAPI.Target.class, request
							.getParameter(PARAM_TARGET));

			// map cookies to session state, for client side storage
			if (request.getCookies() != null)
				for (Cookie cookie : request.getCookies())
					request.getSession().setAttribute(cookie.getName(),
							cookie.getValue());

			// pass web tier session ID through to session
			request.getSession().setAttribute(
					ClusterInfoAPI.getName(Target.Request,
							ClusterInfoAPI.KEY_SESSION_ID), null);
			request.getSession().setAttribute(
					ClusterInfoAPI.getName(Target.Session,
							ClusterInfoAPI.KEY_SESSION_ID),
					request.getSession().getId());

			// process the target for a given mode
			ClusterInfoAPI.processRequest(mode, target, DataStoreFactory
					.getDataStore(request), DataStoreFactory
					.getDataStore(request.getSession()));

			// marshal the state into the request
			ClusterInfoAPI.processRequest(Mode.Refresh, Target.AllClusterState,
					DataStoreFactory.getDataStore(request), DataStoreFactory
							.getDataStore(request.getSession()));

			// map session to cookies state, for client side storage
			for (Target cookieTarget : Target.values()) {
				String cookieTargetName = ClusterInfoAPI.getName(cookieTarget,
						ClusterInfoAPI.KEY_SESSION_ID);
				String cookieTargetValue = (String) request.getSession()
						.getAttribute(cookieTargetName);
				if (cookieTargetValue != null)
					response.addCookie(new Cookie(cookieTargetName,
							cookieTargetValue));
			}

			// build response
			response.setContentType("text/html");
			getServletContext().getRequestDispatcher(JSP_PATH + "/index.jsp")
					.include(request, response);

		} catch (Throwable t) {
			throw new ServletException("Could not render response", t);
		} finally {

			if (log.isInfoEnabled())
				log.info("http request processed");

		}

	}
}