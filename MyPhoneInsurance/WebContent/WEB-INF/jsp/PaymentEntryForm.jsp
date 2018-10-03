<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Form</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<form:form modelAttribute="paymentInfo" method="post" action="submitPayment">
	    <table>
	    	<tr><th>Enter your payment information:</th><th/></tr>
			<tr><td>Credit Card Number</td><td><form:input path="creditCardNumber" /></td></tr>
			<tr><td>Expiration Date</td><td><form:input path="expirationDate" /></td></tr>
			<tr><td>CVV Code</td><td><form:input path="cvvCode" /></td></tr>
			<tr><td>Card Holder Name</td><td><form:input path="cardHolderName" /></td></tr>
	    </table>
	    <input class="btn" type="submit" value="Confirm">
	</form:form>	
	<jsp:include page = "Footer.jsp"/>
</body>
</html>