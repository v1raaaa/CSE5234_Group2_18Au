<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Order Form</title>
	<script src="/MyPhoneInsurance/js/utilityFunctions.js"></script>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<div>
		<c:if test="${sessionScope.invalidItemAvailability != null}">
			<p class="error"><%= request.getSession().getAttribute("invalidItemAvailability")%></p>		
		</c:if>
	</div>
	<div id="invalid" class="invalid"></div>
	<form:form name="orderForm" modelAttribute="order" onsubmit="return validateQuantity()" method="post" action="/MyPhoneInsurance/purchase/submitItems">
	    <table>
	    	<tr>
	    		<th colspan="1">Item</th>
	    		<th>Price</th>
	    		<th>Quantity</th>
	    	</tr>
			<c:forEach items="${order.lineItems}" var="lineItem" varStatus="loop">
				<tr>
					<td><c:out value="${lineItem.itemName}"></c:out></td>
					<td><c:out value="$${lineItem.price}"></c:out></td>
					<td><form:input path="lineItems[${loop.index}].quantity"/></td>
					<form:hidden path="lineItems[${loop.index}].itemId" value = "${lineItem.id}"/>
					<form:hidden path="lineItems[${loop.index}].itemName" value = "${lineItem.itemName}"/>
					<form:hidden path="lineItems[${loop.index}].itemId" value = "${lineItem.itemId}"/>
					<form:hidden path="lineItems[${loop.index}].price" value = "${lineItem.price}"/>
				</tr>
			</c:forEach>
	    </table>
	    <input class="btn" type="submit" value="Purchase">
	</form:form>
	<jsp:include page = "Footer.jsp"/>
</body>
</html>