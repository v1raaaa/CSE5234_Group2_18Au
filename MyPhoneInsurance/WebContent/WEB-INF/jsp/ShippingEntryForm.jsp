<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shipping Form</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<form:form modelAttribute="shippingInfo" method="post" action="submitShipping">
	    <table>
	       	<tr><th>Enter your shipping information:</th><th/></tr>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Address Line 1</td>
				<td><form:input path="addressLine1" /></td>
			</tr>
			<tr>
				<td>Address Line 2</td>
				<td><form:input path="addressLine2" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><form:input path="city" /></td>
			</tr>
			<tr>
				<td>State</td>
				<td><form:input path="state" /></td>
			</tr>
			<tr>
				<td>ZIP</td>
				<td><form:input path="zip" /></td>
			</tr>
	    </table>
	    <input class="btn" type="submit" value="Submit">
	</form:form>
	<jsp:include page = "Footer.jsp"/>
</body>
</html>