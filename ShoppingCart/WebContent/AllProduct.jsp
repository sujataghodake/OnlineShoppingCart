<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="CommonAdmin.jsp"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
if(session.getAttribute("Admin")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList<Product> list = productdata.getData();
		request.setAttribute("list", list);
		System.out.println(list.size());
	%>
	<P>
		<b>Products for sale:</b>
	</P>
	<table width="100%" border="1">
		<th>Category Name:</th>
		<th>SubCategory Name</th>
		<th>Product Name:</th>
		<th>Product Price:</th>
		<th>Product Description:</th>
		<th>Product Quantity:</th>
	
	
		<c:forEach items="${list}" var="product">
			<tr>

				<td>${product.categoryName} <input type="hidden" name="name"
					value="${product.categoryName}"></td>
				<td>${product.subcategoryName} <input type="hidden" name="name"
					value="${product.subcategoryName}">
				</td>
				<td>${product.productName} <input type="hidden" name="name"
					value="${product.productName}">
				</td>
				<td>${product.productPrice} <input type="hidden" name="price"
					value="${product.productPrice}">
				</td>
				<td>${product.description} <input type="hidden"
					name="description" value="${product.description}">
				</td>
				<td>${product.productquantity} <input type="hidden"
					name="productquanity" value="${product.productquantity}">
				</td>
			</tr>
		</c:forEach>
		</tr>
	</table>
	<%} %>
		<span style="color: green"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span>
	<p><a href="AdminHome.jsp">Exit</a></p>
</body>
</html>