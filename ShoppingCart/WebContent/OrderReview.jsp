<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Common.jsp"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function checkOut(){
    var newWindow = window.open('checkOut.jsp', 'name', 'height=500,width=600,centre');
 
}
</script>
</head>
<body bgcolor="#E6E5F4">
<%
if(session.getAttribute("Customer")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
List<Product> eList = (List) request.getAttribute("list1");
		request.setAttribute("list", eList); %>
	<p>Confirmation...!!</p>
	<c:set var="total" value="${0}"/>
	<form action="checkout" method="POST">
		<table width="100%">
			<tr>
				
			  <c:forEach items="${list}" var="product" varStatus="counter">
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
						 <input type='hidden' name='itemIndex' value='<c:out value="${counter.count}"/>'>
							<font size="3"><b>Product Quantity:</b>${product.productquantity}</font>
							<input type="hidden" name="quantity" value="${product.productquantity}">
						</p>
						<p>
							<font size="3"><b>Product Description:</b>${product.description}</font>
							<input type="hidden" name="description"
								value="${product.description}">
						</p>
					</td>
				 <c:set var="total" value="${total + product.productPrice}" />
				</c:forEach>
			</tr>
		</table>
		
			<% 	session.setAttribute("list", eList);%>
		<span style="color: blue" font size="100"><center><p><b>Total Cost: ${total}</b>
		<input type="hidden" name="total" value="${total}"> </p>
		<p><b>Enter Shipping address:</b><input type="test" name="address" required> </br></p>
		<p style="color:Red" font size="100">Payment mode: <b>Cash on Delivery</b></p>
		<input type="submit" value="Proccced to checkout" /></center></span>
	</form>
	<%} %>
	<a href="CustomerHome.jsp">Exit</a>
</body>
</html>