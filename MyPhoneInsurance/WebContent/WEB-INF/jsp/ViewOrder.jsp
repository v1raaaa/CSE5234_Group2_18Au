<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Order</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<h2>Review Your Order</h2>
	<form:form method="post" action="confirmOrder">
		 <table>
	    	<tr>
	    		<th colspan="1">Item</th>
	    		<th>Price</th>
	    		<th>Quantity</th>
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
   		<h3>Total Price: $<%= request.getSession().getAttribute("totalPrice") %></h3>    	
    	<input class="btn" type="submit" value="Confirm">
	</form:form>
	<jsp:include page = "Footer.jsp"/>
</body>
</html>