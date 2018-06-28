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
<title>ShoppingCart</title>
</head>
<body bgcolor="#E6E5F4">
	<%
		if (session.getAttribute("Customer") == null) {
			response.sendRedirect("UserLogin.jsp");
		} else {
	%>
	<p>
		<font size="5"><strong>Product List </strong></font>
	</p>
	<form method="post" name="frm" action="search">
		<table border="0" width="500" align="center">
			<tr>
				<td colspan=2 style="font-size: 12pt align="center";>
					<center>
						<b>Search Item</b>
					</center>
				</td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="text" name="name"
					id="name"></td>
			</tr>
			<tr>
				<td colspan=2 align="center"><input type="submit" name="submit"
					value="Search"></td>
			</tr>
			<td><center>
					<span style="color: red"><%=(request.getAttribute("errMessage") == null) ? ""
						: request.getAttribute("errMessage")%></span>
				</center></td>
		</table>
	</form>
	<p />

	<%
		ProductData_IF productdata = new ProductDataImpl();
			ArrayList<Product> list = productdata.getData();
			request.setAttribute("list", list);
	%>
	<P>
		<b>Products for sale:</b>
	</P>
	<table width="100%" border="1">
		<center>
			<span style="color: Blue" font size="200"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span>
		</center>
		<tr>
			<c:forEach items="${list}" var="product">
				<td>

					<p>
						<font size="3"><b>Category Name:</b>
							${product.categoryName}</font> <input type="hidden" name="name"
							value="${product.categoryName}">
					</p>

					<p>
						<font size="3"><b>SubCategory Name:</b>
							${product.subcategoryName}</font> <input type="hidden" name="name"
							value="${product.subcategoryName}">
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
					</p> <a name="action"
					href="add?id=<c:out value='${product.productId}' />">Add to
						Cart</a>
				</td>
			</c:forEach>
		</tr>
	</table>

	<%
		}
	%>

</body>
</html>