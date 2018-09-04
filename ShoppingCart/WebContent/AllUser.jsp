<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="CommonAdmin.jsp"%>
<%@ page import="java.util.List,com.fecund.shcart.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body bgcolor="#E6E5F5">
<%
if(session.getAttribute("Admin")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
 	ArrayList eList = (ArrayList) request.getAttribute("empList");
 	request.setAttribute("eList", eList);
 %>
 
	<h2>User Information</h2>
	<form>
		<table width="50%" border="solid">
			<tr>
				<td>UserId</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Email_Id</td>
				<td>Contact No</td>
				<td>User Name</td>
				<td>Password</td>
				<td>ConfirmPassword</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${eList}" var="user">
			<tr>
					<td> ${user.userid} </td>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.email}</td>
					<td>${user.contactno}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
					<td>${user.confirmpassword}</td>
					<td>${user.status}</td>
			</tr>
			</c:forEach>
		</table>
		<p>
			<a href="AdminHome.jsp">Exit</a>
		</p>
	</form>
	<%} %>
</body>
</html>