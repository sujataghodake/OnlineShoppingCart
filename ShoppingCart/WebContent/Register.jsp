<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	  function validateForm() {
		    var x = document.forms["myForm"]["email"].value;
		    var atpos = x.indexOf("@");
		    var dotpos = x.lastIndexOf(".");
		    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
		        alert("Not a valid e-mail address");
		        return false;
		    }
		}
	</script>
</head>
<body bgcolor="#E6E5F4">
	<header> <a href="index.jsp">Home</a></header>
	<center><h1>Registration Form</h1></center>
	<form action="register" method="post" name="myForm" />
		<table style="with: 50%"  align="center">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstname" required /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastname" required/></td>
			</tr>
			<tr>
				<td>Email_Id</td>
				<td><input type="email" name="email" onblur="return validateForm()" required/></td>
			</tr>
	
			<tr>
				<td>Contact No</td>
				<td><input type='int' name="contactno" pattern="[1-9]{1}[0-9]{9}" title='Phone Number with 10 digit (Format: 9999999999)' required></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username" required/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td>
				<input type="password" id="password" class="text" name=password value="" />
				</td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td>
				<input type="password" id="confirmpassword" class="text" name="confirmpassword" value="" onblur="confirmPass()" required/>
				</td>
				<span id="error" style="color:#F00;"> </span>
			</tr>
		</table>
		
		<center><input type="submit" value="Register" Name="register"/>
		<input type="reset" value="Reset" /></center>
	</form>
	</div>
	<center><span style="color: red"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span></center>
	<p>
		<a href="index.jsp">Exit</a></p>
</body>
</html>