
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ include file="Common.jsp"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
<html>
<head>
<%
	User user = (User) session.getAttribute("SessionUser");
	String Username = user.getUsername();
	System.out.println("current username=" + Username);
	if (Username == null) {
		response.sendRedirect("UserLogin.jsp");
	} else {
		if (session.getAttribute("cart") != null) {
%>

<title>ShoppingCart</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body bgcolor="#E6E5F4">

	<form method="POST" action="CartController">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<p>
			<font size="5"><strong>Shopping Cart</strong></font>
		</p>
		<p>
			<a href="CustomerHome.jsp" mce_href="CustomerHome.jsp">ProductList</a>
		</p>

		<jsp:useBean id="cart" scope="session"
			class="com.fecund.shcart.entity.Cart" />

		<table width="75%" border="1">
			<tr bgcolor="#CCCCCC">

				<td><strong><font size="3">Select</font></strong></td>
				<td><strong><font size="3">Product</font></strong></td>
				<td><strong><font size="2">Quantity</font></strong></td>
				<td><strong><font size="2">Unit Price</font></strong></td>
				<td><strong><font size="2">Total</font></strong></td>
				<td><strong><font size="2">Action</font></strong></td>
			</tr>
			<c:forEach var="product" items="${cart.cartItems}"
				varStatus="counter">

				<tr>
					<td><input type="checkbox" name="itemId"
						value="${product.productId}"> <input type="hidden"
						name="name" value="${product.productId}"></td>
					<td><font size="3"> <c:out
								value="${product.productName}" /></b><br /> <c:out
								value="${product.description}" /></font> <input type="hidden"
						name="id" value="${product.productId}"></td>
					<td><font size="2"> <input type='hidden'
							name='itemIndex' value='<c:out value="${counter.count}"/>'>
							<input type='text' name="quantity"
							value='<c:out value="${product.productquantity}"/>'></br> <input
							type="submit" name="action" value="Update"></font></td>
					<td><font size="3"><c:out value="${product.unitCost}" /></font>
						<input type="hidden" name="unitcost" value="${product.unitCost}"></td>
					<td><font size="3"><c:out value="${product.totalCost}" /></font>
						<input type="hidden" name="productcost"
						value="${product.totalCost}"></td>
					<td><a name="action"
						href="DeleteProduct?index=<c:out value='${counter.count}'/>">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2"></td>
				<td></td>
				<td></td>
				<td><font size="3">Subtotal:<c:out
							value="${cart.orderTotal}" /></font></td>
			</tr>
			<td><input type="submit" name="action" value="Buy Now"></td>
			<td><input type="submit" name="action" value="Cancel Cart"></td>
		</table>
	</form>
	<span style="color: blue"><%=(request.getAttribute("quantityMessage") == null) ? ""
							: request.getAttribute("quantityMessage")%></span>
	<%
		} else {
	%>
	<p>
		Cart is currently empty-<br /> Please, Add product on cart.
	</p>
	<a href="CustomerHome.jsp" mce_href="CustomerHome.jsp">ProductList</a>
	<%
		}

		}
	%>
	<p>
		<a href="CustomerHome.jsp">Exit</a>
	</p>
</body>
</html>
