<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<form:form modelAttribute="pizza" action="${flowExecutionUrl}">
		<h1>
			<spring:message code="complimentMsg" />
		</h1>

		<table width="30%" border="0" cellspacing="3" cellpadding="1">
			<tr>
				<td>
					<button type="submit" id="previous" name="_eventId_previous">
						<spring:message code="previous" />
					</button>
				</td>
				<td>
					<button type="submit" id="next" name="_eventId_next">
						<spring:message code="next" />
					</button>
				</td>
				<td>
					<button type="submit" id="cancel" name="_eventId_cancel">
						<spring:message code="cancel" />
					</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>