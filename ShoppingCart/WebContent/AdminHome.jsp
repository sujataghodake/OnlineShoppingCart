 <%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ include file="CommonAdmin.jsp"%>
<%@ page import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
function addCategory(){
    var newWindow = window.open('addCategory.jsp', 'name', 'height=500,width=600,centre');
 
}
function addSubCategory(){
    var newWindow = window.open('addSubCategory.jsp', 'name', 'height=500,width=600');
}
function addProduct(){
    var newWindow = window.open('addProduct.jsp', 'name', 'height=500,width=600');
}
</script>
</head>
<body bgcolor="#E6E5F4">

<%
//String username=(String)session.getAttribute("Admin");
if(session.getAttribute("Admin")==null)
	
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
%>

<p><input type="button" value="Add Category" onclick="addCategory()">
<input type="button" value="Add SubCategory" onclick="addSubCategory()">
<input type="button" value="Add Product" onclick="addProduct()"></p>

	<p><form method="post" name="frm" action="SearchOrder">
		<table border="0" width="500" align="center">
			<tr>
				<td colspan=2 style="font-size: 12pt align="center";>
					<center>
						<b>Search Order for Customer</b>
					</center>
				</td>
			</tr>
			<tr><td colspan=2 align="center"><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="submit" name="submit"
					value="Search"></td>
			</tr>
			<td><center><span style="color: red"><%=(request.getAttribute("errMessage") == null) ? ""
						: request.getAttribute("errMessage")%></span></center></td>
		</table>
	</form></p>
	
	
	
	<%
	UserData_IF userdata = new UserDataImpl();
	ArrayList<User> eList =userdata.getWaitingUsers();
	if(eList.size()!=0)
	{
	request.setAttribute("eList", eList);%>
	<h2>Awaiting Users</h2>
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
 
	</form>
<%}	
	else{%>
	<p>  </p>
	<% }%>
<%} %>

	<p style="color: Blue"><%=(request.getAttribute("message") == null) ? "": request.getAttribute("message")%></p>
		
</body>

</html>