<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ci" uri="/WEB-INF/tld/clusterinfo.tld"%>

<html>
<head>
<title>JBoss Cluster State</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
<meta http-equiv="cache-control" content="no-cache">
</head>
<body>

<a name="STATE0"></a>

<c:set var="skippedrows" value="0" scope="request" />
<fmt:setBundle var="clusterinfoprops" basename="clusterinfo" />

<table class="main">
	<tbody>
		<tr>
			<td colspan="2">
			<hr>
			</td>
		</tr>
		<tr>
			<td colspan="2" background="images/bg_top_red.gif">
			<table>
				<tbody>
					<tr>
						<td><img src="images/logo.png"
							alt="JBoss - A Division of Red Hat" border="0" /></td>
						<td><font size="6"><b>JBoss Cluster State</b></font></td>
					</tr>
					<tr>
						<td></td>
						<td>Version: <fmt:message bundle="${clusterinfoprops}"
							key="application.version.string" /> [<fmt:message
							bundle="${clusterinfoprops}" key="environment.target" />]</td>
					</tr>
				</tbody>
			</table>
			<br>
			<br>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<hr>
			</td>
		</tr>
		<tr>
			<td colspan="2"><c:forEach items="${clusterinfo}"
				var="clusterinfo" varStatus="loop">
				| <a href="#STATE${loop.index}"><ci:toLabel
					string="${clusterinfo.key}" /></a>
			</c:forEach>|</td>
		</tr>


		<c:forEach items="${clusterinfo}" var="clusterinfo" varStatus="loop">
			<c:set var="property" value="${clusterinfo.value}" scope="request" />

			<tr>
				<td colspan="2"><a name="STATE${loop.index}"></a>
				<hr>
				</td>
			</tr>

			<tr>
				<td><font size="5"><b><ci:toLabel
					string="${clusterinfo.key}" /></b></font></td>
				<td>
				<form name="input"
					action="${pageContext.request.requestURI}#STATE${loop.index}"
					method="get"><input type="hidden" name="target"
					value="${clusterinfo.key}"><input type="submit" name="mode"
					value="Refresh"> <input type="submit" name="mode"
					value="Update"> <input type="submit" name="mode"
					value="Reset"> &nbsp; &nbsp; &nbsp; | <a
					href="#STATE${loop.index + 1}">Next</a> | <a
					href="#STATE${loop.index - 1}">Prev</a> | <a href="#STATE0">Top</a>
				|</form>
				</td>
			</tr>

			<c:set var="propertyName" value="serverName" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="serverHost" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="serverVersion" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="serverBindAddress" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="serverIsStandalone" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="sessionStateTarget" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="timestamp" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

			<c:set var="propertyName" value="sessionID" scope="request" />
			<jsp:include page="jsp/fragment/clusterInfoPropertyDetail.jsp" />

		</c:forEach>

		<tr>
			<td colspan="2">
			<hr>
			</td>
		</tr>

		<tr>
			<td colspan="2" background="images/bg_top_red.gif"><br>
			<a href="mailto:graham@redhat.com">Graham Gear</a> <br>
			<i>Senior Solution Architect</i> <br>
			<b>JBoss by Red Hat</b>
			</td>
		</tr>
	</tbody>
</table>

<table>
	<tbody>
		<c:forEach var="i" begin="1" end="${skippedrows}">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>