<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Common.jsp"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E6E5F4">
<%
if(session.getAttribute("Customer")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
	

		ArrayList<Product> list = (ArrayList<Product>) request
				.getAttribute("list");
		request.setAttribute("list", list);
	%>
	<p> <b>Search Result:</b></p>
	<table width="100%" border="1">
		<tr>
			<c:forEach items="${list}" var="product">
				<td>
				<p>
						<font size="3"><b>Category Name:</b> ${product.categoryName}</font>
						<input type="hidden" name="name" value="${product.categoryName}">
					</p>
			
						<p>
						<font size="3"><b>SubCategory Name:</b> ${product.subcategoryName}</font>
						<input type="hidden" name="name" value="${product.subcategoryName}">
					</p>
					<p>
						<font size="3"><b>Product Name:</b> ${product.productName}</font>
						<input type="hidden" name="name" value="${product.productName}">
					</p>
					<p>
						<font size="3"><b>Product Price:</b>${product.productPrice}</font>
						<input type="hidden" name="price" value="${product.productPrice}">
					</p>
						<p>
						<font size="3"><b>Available Quantity:</b>${product.productquantity}</font>
						<input type="hidden" name="quantity" value="${product.productquantity}">
					</p>
					<p>
						<font size="3"><b>Product Description:</b>${product.description}</font>
						<input type="hidden" name="description"
							value="${product.description}">
					</p> 
					<a name="action" href="add?id=<c:out value='${product.productId}'/>">Add to Cart</a>

				</td>
			</c:forEach>
		</tr>
	</table>
	<%} %>
	<a href="CustomerHome.jsp">Exit</a>
</body>
</html>