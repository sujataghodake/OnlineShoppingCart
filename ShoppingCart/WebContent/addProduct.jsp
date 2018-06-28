<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page
	import="java.sql.Connection,com.fecund.shcart.db.*,com.fecund.shcart.entity.*"%>
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
	<form action="insertProduct" method="POST">
		<table>
			<tr>
				<td>Product Name</td>
				<td>:</td>
				<td><input type="text" name="name" size="10" required></td>
			</tr>
			<tr>
				<td>Product Price</td>
				<td>:</td>
				<td><input type="text" name="price" size="15" required></td>
			</tr>
			<tr>
				<td>Product Quantity</td>
				<td>:</td>
				<td><input type="text" name="quantity" size="15" required></td>
			</tr>
			<tr>
				<td>Product Descripton</td>
				<td>:</td>
				
				<td><textarea id="txtArea"  name="description" rows="10" cols="60" required></textarea></td>
			</tr>
	
			<%
				ProductData_IF productdata = new ProductDataImpl();
				ArrayList<Subcategory> list = productdata.getSubCategory();
				request.setAttribute("list", list);
				Subcategory subcat = new Subcategory();
			%>
			<tr>
					<td>Subcategory Name</td>
				<td>:</td>
				<td> <select name="subcategoryid">
						<c:forEach items="${list}" var="subcategory">
							<option value="${subcategory.subcategoryid}">${subcategory.subcategoryname}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="Insert Product">
	</form><span style="color: red"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span>
	<%} %>
 
		<p><input type="button" onclick="window.close();" value="Exit"/></p>
</body>
</html>