<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
<style>
header {
	background-color: #cccccc;
	font-size: 25px;
}
</style>
<header> <a class="brand" href="index.jsp">Home</a> </header>
</head>
<body bgcolor="#E6E5F4">
<%
if(session.getAttribute("Customer")!=null)
	{ response.sendRedirect("CustomerHome.jsp");
} 
%>
	<h1>Shopping Bazar- An On-line Shopping Portal</h1>
	<h3>
		<marquee>Welcome To Online-Shopping </marquee>
	</h3>
	
	<a href="Register.jsp">Register</a>
	</br>
	<a href="UserLogin.jsp">Login</a>
	</br>


</body>
</html>