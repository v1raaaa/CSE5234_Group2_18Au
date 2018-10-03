<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href="css/mystyle.css">
<meta charset="ISO-8859-1">
<title>Shipping Form</title>
</head>
<body>
	<h2>Enter your shipping information:</h2>
	<form:form modelAttribute="shippingInfo" method="post" action="submitShipping">
	    <table>
			<tr>
				<td>Name</td>
				<td><form:input path="name" /></td>
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
		  	<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
		  	</tr>
	    </table>
	</form:form>
</body>
</html>