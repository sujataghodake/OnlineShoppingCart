<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ include file="Common.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E6E5F4">

<%
String userid=(String)session.getAttribute("Customer");
System.out.println(userid);
if(session.getAttribute("Customer")==null)
	{ response.sendRedirect("UserLogin.jsp");
} 
else{
			ArrayList<Order> list=(ArrayList<Order>)request.getAttribute("list");
			if(list.size()==0)
			{%>
			<p>There is no any order...!</p>
				
			<%}
			else{
			request.setAttribute("list", list);
			System.out.println(list.size());
	%>
	<P>
		<b>Products for sale:</b>
	</P>
	<form>
	<table width="100%" border="1">
	<th>OrderItem Id</th>
			<th>Item Name</th>
		<th>Item Quantity</th>
	     <th>Item Price</th>
		
	
		<c:forEach items="${list}" var="order">
			<tr>
			<td> ${order.idorderitem}<input type="hidden"
					name="idorderitem" value="${order.idorderitem}">
				</td>
				<td>${order.productname} <input type="hidden"
					name="productname" value="${order.productname}">
				</td>
				<td>${order.quantity} <input type="hidden"
					name="quantity" value="${order.quantity}">
				</td>
				<td>${order.unitprice} <input type="hidden"
					name="unitprice" value="${order.unitprice}">
				</td>
		
			</tr>
		</c:forEach>
		</tr>
	</table>
	</form>
	<a href="CustomerHome.jsp">Exit</a>
	<%} 
	}%>
</body>
</html>