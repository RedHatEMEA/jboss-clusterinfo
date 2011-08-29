package org.jboss.clusterinfo.aop;

import org.jboss.aop.advice.Interceptor;
import org.jboss.aop.joinpoint.ConstructorInvocation;
import org.jboss.aop.joinpoint.FieldReadInvocation;
import org.jboss.aop.joinpoint.FieldWriteInvocation;
import org.jboss.aop.joinpoint.Invocation;
import org.jboss.aop.joinpoint.MethodInvocation;

public abstract class BaseInterceptor implements Interceptor {

	protected String getInvocationInfo(Invocation invocation) {

		StringBuffer info = new StringBuffer("invocation class: "
				+ invocation.getClass().getName());

		if (invocation instanceof ConstructorInvocation) {
			ConstructorInvocation ci = (ConstructorInvocation) invocation;
			info.append(" type: constructor invocation");
			info.append(" constructor: " + ci.getConstructor());
		} else if (invocation instanceof MethodInvocation) {
			MethodInvocation mi = (MethodInvocation) invocation;
			info.append(" type: method invocation");
			info.append(" method: " + mi.getMethod().getName());
			info.append(" class containing method: "
					+ mi.getMethod().getDeclaringClass().getName());
		} else if (invocation instanceof FieldWriteInvocation) {
			FieldWriteInvocation fi = (FieldWriteInvocation) invocation;
			info.append(" type: field write invocation");
			info.append(" field: " + fi.getField());
			info.append(" class containing field: "
					+ fi.getField().getDeclaringClass().getName());
		} else if (invocation instanceof FieldReadInvocation) {
			FieldReadInvocation fi = (FieldReadInvocation) invocation;
			info.append(" type: field read invocation");
			info.append(" field: " + fi.getField());
			info.append(" class containing field: "
					+ fi.getField().getDeclaringClass().getName());
		}

		return info.toString();
	}

}
