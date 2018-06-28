<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
header {
	background-color: #cccccc;
	font-size: 25px;
}
</style>
<% String username=(String)session.getAttribute("Admin");%>
</head>

<body bgcolor="#E6E5F4">
	<header class="dev"> 
	<a href="AdminHome.jsp">Home</a> &nbsp; &nbsp; <a
		href="getwaitingUsers">Check Awaiting Approvals</a>&nbsp; &nbsp; <a
		href="getAllUsers">Get All Customers</a>&nbsp; &nbsp; <a
		href="AllProduct.jsp">Show All Products</a>&nbsp; &nbsp;
		 <a href="NewOrder.jsp">Show New Order</a>&nbsp; &nbsp; <a
		href="AllOrder.jsp">Show All Order</a>&nbsp; &nbsp;
		<a href="AllOrderItem.jsp">Show All Order Item</a>&nbsp; &nbsp;
		<a href="logout" style="float: right">Logout</a> &nbsp; </header>
		<h2 style="float: right"><% out.println("Welcome "+username);%></h2>
</body>
</html>