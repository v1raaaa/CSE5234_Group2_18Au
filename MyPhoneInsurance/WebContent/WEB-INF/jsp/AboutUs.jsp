<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
</head>
<body>
	<jsp:include page = "Header.jsp"/>	
	<h2>About Us</h2>
	<div class="bio">
		<img class = "img-circle" src = "./img/0.jpg">
		<h3>Alex Doan</h3>
		<p>
			Chief Operating Officer
			<br />
			The Ohio State University - Class of 2019 (B.S. CSE)
			<br />
			
		</p>
	</div>
	<div class="bio">
		<img class = "img-circle" src = "${pageContext.request.contextPath}/img/2.jpg">
		<h3>Yi Ling</h3>
		<p>
			Chief Information Officer
			<br />
			The Ohio State University - Class of 2019 (M.S. CSE)
			<br />
			
		</p>
	</div>
	<div class="bio">
		<img class = "img-circle" src = "/MyPhoneInsurance/img/1.jpg">
		<h3>Arnab Banerjee</h3>
		<p>
			Chief Technical Officer
			<br />
			The Ohio State University - Class of 2019 (M.S. CSE)
			<br />
		</p>
	</div>
	
	<jsp:include page = "Footer.jsp"/>
</body>
</html>