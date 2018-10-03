<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>
	<h2>Phone Insurance 4All</h2>
	
	<h4>Our Vision</h4>
	<div class="home">
		<p>To be the answer for all phone hardware issues</p>
	</div>
	
	<h4>Our Mission</h4>
	<div class="home">
		<p>To provide affordable insurance on phone equipment and accessories to the entire world</p>
	
	</div>
	
	<h4>Business Strategy</h4>
	<div class="home">
		<p>We aim to attain these goals by continuing to provide guaranteed inventory and <br /> SAME DAY SHIPPING</p>
	
	</div>
	
	<table>
		<tr><th>Products</th></tr>
		<tr><td>Screen Insurance</td></tr>
		<tr><td>Battery Insurance</td></tr>
		<tr><td>Camera Insurance</td></tr>
		<tr><td>Charger Insurance</td></tr>
		<tr><td>Bundle</td></tr>
	</table>
	<jsp:include page = "Footer.jsp"/>
</body>
</html>