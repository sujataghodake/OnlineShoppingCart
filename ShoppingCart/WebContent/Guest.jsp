<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Editor Page</title>
<style>
header
{
background-color: #cccccc;
font-size: 25px;
}</style>
</head>
<body bgcolor="#E6E5F4">
<header >
		<a href="Guest.jsp">Home</a> &nbsp;
		<a href="logout">Logout</a> &nbsp;
  </header>
<center><h2>Editor's Home</h2></center>
 
Welcome <%=request.getAttribute("username") %>

 
</body>
</html>