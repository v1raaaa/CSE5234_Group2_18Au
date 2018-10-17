<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Confirmed</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<h3>Thank you, <c:out value="${shippingInfo.name}"/>! Your order has been confirmed. <br/>
	Your confirmation code is <%= request.getSession().getAttribute("confirmationCode") %>.</h3>
	
	
	<table>
		<tr><th>Shipping Address</th></tr>
		<tr><td><c:out value="${shippingInfo.addressLine1}"/></td></tr>
		<tr><td><c:out value="${shippingInfo.addressLine2}"/></td></tr>
		<tr><td><c:out value="${shippingInfo.city}, ${shippingInfo.state} ${shippingInfo.zip}"/></td></tr>
	</table>
	
	<table>
		<tr><th>Order Information</th><th></th><th>Total Price: $<%= request.getSession().getAttribute("totalPrice") %></th></tr>	
		<tr style="font-weight: bold"><td>Item</td><td>Price</td><td>Quantity</td></tr>
		<c:forEach  items="${order.lineItems}" var="item" varStatus="loop">
			<c:if test = "${not empty item.quantity}">
				<tr>
					<td><c:out value="${item.itemName}"/></td>
					<td><c:out value="$${item.price}"/></td>
					<td><c:out value="${item.quantity}"/></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	
	<table>
		<tr><th>Payment Information</th></tr>	
		<tr><td><c:out value="${paymentInfo.cardHolderName}"/></td></tr>
		<tr><td><c:out value="${paymentInfo.creditCardNumber}"/></td></tr>

	</table>
	<jsp:include page = "Footer.jsp"/>
	
</body>
</html>