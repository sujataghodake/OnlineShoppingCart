<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<form action="insertCategory" method="POST"> 
<table> 
<tr><td>Category Name</td>
				<td><input type="text" name="CategoryName" required /></td>
			</tr>
</table>  
<input type="submit"  value="Add Category" > 
<!-- input type="submit"  value="Add Category" onClick="window.close()"--> 
</form> 
<%} %>
<p>
			<p><input type="button" onclick="window.close();" value="Exit"/></p>
			<span style="color: red"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span>
</body>
</html>