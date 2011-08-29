package org.jboss.clusterinfo.aop;

import org.jboss.aop.joinpoint.Invocation;
import org.jboss.logging.Logger;

public class LoggingInterceptor extends BaseInterceptor {

	private static final Logger log = Logger.getLogger(LoggingInterceptor.class
			.getName());

	public String getName() {
		return LoggingInterceptor.class.getName();
	}

	public Object invoke(Invocation invocation) throws Throwable {

		long time = 0l;
		try {

			if (log.isTraceEnabled())
				log.trace("Enter [" + getInvocationInfo(invocation) + "]");

			time = System.currentTimeMillis();

			return invocation.invokeNext();

		} finally {
			if (log.isTraceEnabled())
				log.trace("Leave [" + getInvocationInfo(invocation)
						+ "], processing time ["
						+ (System.currentTimeMillis() - time) + " ms]");
		}
	}
}
