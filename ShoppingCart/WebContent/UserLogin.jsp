<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
<style>
body {
	background-color: url("image/bg.jpg ");
}

header {
	background-color: #cccccc;
	font-size: 25px;
}
</style>
<script type="text/javascript">
function required()
{
var empt = document.form1.username.value;
if (empt === "")
{
alert("Please input a Value");
return false;
}
else
	{
	return true;
	}
}
</script>
</head>
<body bgcolor="#E6E5F4">

	<header> <a class="brand" href="index.jsp">Home</a> </header>
	<span style="color: green"><%=(request.getAttribute("successmessage") == null) ? ""
					: request.getAttribute("successmessage")%></span>
	<h2>Login Here</h2>
	<form action="login" method="post" name="form1">

		<table align="center">

			<tr>
				<td>Username</td>
				<td><input type="text" name="username" onblur="required()" required /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" required /></td>
			</tr>
			<tr>
				<td><span style="color: red"><%=(request.getAttribute("errMessage") == null) ? ""
					: request.getAttribute("errMessage")%></span></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login"></input><input
					type="reset" value="Reset"></input></td>
			</tr>
		</table>
		<center>
			<span style="color: red"><%=(request.getAttribute("message") == null) ? "" : request
					.getAttribute("message")%></span>
		</center>
	</form>
	<p>
		New user...<a href="Register.jsp">Register Here</a>
	<p>
		<a href="index.jsp">Exit</a>
	</p>
</body>
</html>