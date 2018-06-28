<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style border: solid 1px black;>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
<style>
header
{
background-color: #cccccc;
font-size: 25px;
}</style>
<% String username=(String)session.getAttribute("Customer");%>
</head>
<body bgcolor="#E6E5F4">
	<header class="dev">
	
		<a href="CustomerHome.jsp">Home</a> &nbsp;
		<a href="getUser">Edit Profile</a> &nbsp;
		<a href="ShoppingCart.jsp">View Cart</a> &nbsp;
		<a href="CustomerOrder.jsp">Your Order</a> &nbsp;
		<a href="logout" style="float:right">Logout</a> &nbsp;  </header>
		<h2 style="float: right"><% out.println("Welcome "+username);%></h2>

</body>
</html>