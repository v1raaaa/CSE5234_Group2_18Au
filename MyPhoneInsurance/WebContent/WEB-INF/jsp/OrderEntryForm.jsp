<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Form</title>
</head>
<body>
	<form:form modelAttribute="order" method="post" action="/MyPhoneInsurance/purchase/submitItems">
	    <table>
	    	<tr>
	    		<td colspan="1">Item</td>
	    		<td>Price</td>
	    		<td>Enter Quantity</td>
	    	</tr>
			<c:forEach items="${order.items}" var="item" varStatus="loop">
				<tr>
					<td><c:out value="${item.name}"></c:out></td>
					<td><c:out value="$${item.price}"></c:out></td>
					<td><form:input path="items[${loop.index}].quantity" /></td>
					<form:hidden path="items[${loop.index}].name" value="${item.name}"/>
					<form:hidden path="items[${loop.index}].price" value="${item.price}"/>
				</tr>
			</c:forEach>
		  	<tr>
				<td colspan="2"><input type="submit" value="Purchase"></td>
		  	</tr>
	    </table>
	</form:form>

</body>
</html>