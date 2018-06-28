
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
<body bgcolor="#E6E5F4">
<%
if(session.getAttribute("Admin")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
%>
	<%
		ArrayList<User> eList = (ArrayList<User>) request.getAttribute("empList");
	if(eList.size()!=0)
	{
		request.setAttribute("eList", eList);
	%>
	<h2>User Information</h2>
	
	<form action="updatewaitingUsers">
		<table border="1">
			<tr>
			<td>User Id</td>
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
					<td><c:out value="${user.userid}" />
					 <input type="hidden" name="userid"
					value="${user.userid}"></td>
					<td><c:out value="${user.firstname}" /></td>
					<td><c:out value="${user.lastname}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.contactno}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.confirmpassword}" /></td>
					  <td> <select name="status" value="%{status}" >
			<option value="${user.status}">${user.status}</option>
			<option value="Approved">Approved</option>
			<option value="Reject">Reject</option>
			<option value="Waiing">Waiting</option>
			    </select>
			    </td>
				</tr>
				
			</c:forEach>
			</table>
	<input type="submit" Value="Submit" name="submit">
    <p><a href="AdminHome.jsp">Exit</a></p>
	</form>
	<%}
	else{%>
	<p> No waiting approvals</p>
	<%
	}
	}%>
	</body>
	</html>
