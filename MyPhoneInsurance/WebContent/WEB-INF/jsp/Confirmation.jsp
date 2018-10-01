<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Thank you, <c:out value="${shippingInfo.name}"/>! Your order has been confirmed. Your order ID is <%= request.getSession().getAttribute("uuid") %>.</h3>
	
	
	<h4>Shipping Address:</h4>
	<table>
		<tr><td><c:out value="${shippingInfo.addressLine1}"/></td></tr>
		<tr><td><c:out value="${shippingInfo.addressLine2}"/></td></tr>
		<tr><td><c:out value="${shippingInfo.city}, ${shippingInfo.state} ${shippingInfo.zip}"/></td></tr>
	</table>
	
	<h4>Order Information:</h4>
	<table>
		<tr style="text-decoration: underline"><td>Item</td><td>Price</td><td>Quantity</td></tr>
		<c:forEach  items="${order.items}" var="item" varStatus="loop">
			<c:if test = "${not empty item.quantity}">
				<tr>
					<td><c:out value="${item.name}"/></td>
					<td><c:out value="$${item.price}"/></td>
					<td><c:out value="${item.quantity}"/></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<b>Total Price: $<%= request.getSession().getAttribute("totalPrice") %></b>
	
	<h4>Payment Information:</h4>
	<table>
		<tr><td><c:out value="${paymentInfo.cardHolderName}"/></td></tr>
		<tr><td><c:out value="${paymentInfo.creditCardNumber}"/></td></tr>

	</table>
</body>
</html>