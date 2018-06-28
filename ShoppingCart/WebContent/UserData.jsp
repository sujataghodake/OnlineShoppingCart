<%@ page import="com.fecund.shcart.controller.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,java.lang.*"%>
<%@ include file="Common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
<script type="text/javascript">
	  function confirmPass() {
		    var pass = document.getElementById("password").value
		    var confPass = document.getElementById("confirmpassword").value
		    if(pass != confPass) {
		        document.getElementById('error').innerHTML='wrong confirm password';
		    }
		    else
		    {
		        document.getElementById('error').innerHTML='';
		    }
		}
	</script>
</head>
<body bgcolor="#E6E5F4">
<%
if(session.getAttribute("Customer")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
	
%>
	<h2>User Information</h2>
	<form action="updateUser" method="post">
		<table style="with: 50%">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstname"
					value="${user.firstname}" required /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastname" value="${user.lastname}" required/></td>
			</tr>
			<tr>
				<td>Email_Id</td>
				<td><input type="email" name="email" value="${user.email}" required/></td>
			</tr>
			<tr>
				<td>Contact No</td>
				<td><input type="int" name="contactno"
					value="${user.contactno}" pattern="[1-9]{1}[0-9]{9}" title='Phone Number with 10 digit (Format: 9999999999)' required/></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username" value="${user.username}" required /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password"
					value="${user.password}" id="password" class="text" required/></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="text" name="confirmpassword"
					value="${user.confirmpassword}"  id="confirmpassword" class="text" onblur="confirmPass()" required/></td>
					<span id="error" style="color:#F00;"> </span>
			</tr>
		</table>
		<input type="submit" value="Confirm" /> &nbsp;
			
	</form>
<%} %>
</table>
<center><span style="color: Blue"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span></center>
<p>
<a name="action" href="DeleteUser?userid=<c:out value='${user.userid}' />">Delete Account</a></p>
<p><a href="CustomerHome.jsp">Exit</a></p>
</body>
</html>