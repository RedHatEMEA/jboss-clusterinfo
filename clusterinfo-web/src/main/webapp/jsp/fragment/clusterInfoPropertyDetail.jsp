<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ci" uri="/WEB-INF/tld/clusterinfo.tld"%>

<c:set var="propertyValue"
	value="${requestScope['property'][propertyName]}" />
<c:set var="propertyValueClassLocal"
	value="${requestScope['property'][propertyName]['class']}" />

<c:choose>
	<c:when test="${!empty(propertyValue)}">
		<tr>
			<td><ci:toLabel string="${propertyName}" /></td>
			<td><c:choose>
				<c:when
					test="${propertyValueClass == 'class java.util.Date' || propertyValueClass == 'class java.sql.Timestamp'}">
					<fmt:formatDate value="${propertyValue}" type="both"
						timeStyle="long" dateStyle="long" />
				</c:when>
				<c:otherwise>
					<c:out value="${propertyValue}" />
				</c:otherwise>
			</c:choose></td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:set var="skippedrows" value="${skippedrows + 1}" scope="request" />
	</c:otherwise>
</c:choose>