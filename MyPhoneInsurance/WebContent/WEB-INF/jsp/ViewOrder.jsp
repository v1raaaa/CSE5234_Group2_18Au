<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href="css/mystyle.css">
<meta charset="ISO-8859-1">
<title>View Order</title>
</head>
<body>
	<h2>Review Your Order</h2>
	<h3>Total Price: $<%= request.getSession().getAttribute("totalPrice") %></h3>
	<form:form method="post" action="confirmOrder">
		 <table style="border: 1px">
	    	<tr>
	    		<td colspan="1">Item</td>
	    		<td>Price</td>
	    		<td>Quantity</td>
	    	</tr>
			<c:forEach items="${order.items}" var="item" varStatus="loop">
				<c:if test = "${not empty item.quantity}">
					<tr>
						<td><c:out value="${item.name}"></c:out></td>
						<td><c:out value="$${item.price}"></c:out></td>
						<td><c:out value="${item.quantity}"></c:out></td>
					</tr>
				</c:if>
			</c:forEach>
    	</table>	
    	<br />
    	<br />
    	<input type="submit" value="Confirm">
	</form:form>
</body>
</html>