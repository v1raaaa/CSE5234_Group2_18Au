<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href="css/mystyle.css">
<meta charset="ISO-8859-1">
<title>Payment Form</title>
</head>
<body>
	<h2>Enter your payment information:</h2>
	<form:form modelAttribute="paymentInfo" method="post" action="submitPayment">
	    <table>
			<tr><td>Credit Card Number</td><td><form:input path="creditCardNumber" /></td></tr>
			<tr><td>Expiration Date</td><td><form:input path="expirationDate" /></td></tr>
			<tr><td>CVV Code</td><td><form:input path="cvvCode" /></td></tr>
			<tr><td>Card Holder Name</td><td><form:input path="cardHolderName" /></td></tr>
		  	<tr>
				<td colspan="4" height="50"><input type="submit" value="Confirm"></td>
		  	</tr>
	    </table>
	</form:form>	
</body>
</html>