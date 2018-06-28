<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>
	<%@ include file="CommonAdmin.jsp"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E6E5F4">

<%
if(session.getAttribute("Admin")==null)
	{ 
	response.sendRedirect("UserLogin.jsp");
} 
else{
	

		ArrayList<Order> list = (ArrayList<Order>) request.getAttribute("list");
		request.setAttribute("list", list);
	%>
	<p> <b>Searched Result:</b></p>
	<form action="orderstatus" method="post">
	
	<table width="100%" border="1">
		<th>Order Id</th>
		<th>User Id</th>
		<th>Shipping Address</th>
		<th>Order Date</th>
	     <th>Total Price</th>
		<th>Order Status</th>
	
	
		<c:forEach items="${list}" var="order">
			<tr>
			<td> <%-- <a href="OrderItem.jsp"> ${order.idordermaster}</a>
			<input type="hidden" name="idordermaster" value="${order.idordermaster}"> --%> 
			<a name="action" href="OrderItemAdmin?id=<c:out value='${order.idordermaster}'/>">${order.idordermaster}</a>
			 <input type="hidden"
					name="orderid" value="${order.idordermaster}">
				</td>
				<td>${order.userid} <input type="hidden"
					name="userid" value="${order.userid}">
				</td>
				<td>${order.address} <input type="hidden"
					name="address" value="${order.address}">
				</td>
				<td>${order.orderdate} <input type="hidden"
					name="orderdate" value="${order.orderdate}">
				</td>
				<td>${order.amount} <input type="hidden"
					name="amount" value="${order.amount}">
				</td>
				    <td><select name="status" value="%{status}" >
			<option value="${order.status}">${order.status}</option>
			<option value="New">New</option>
			<option value="Processing">Processing</option>
			<option value="Shipped">Shipped</option>
			<option value="Completed">Completed</option>
			    </select>

			</td>
			</tr>
			
		</c:forEach>
		</tr>
	</table>
		<input type="submit" Value="Submit" name="submit">
		</form>
	<%} %>
	<a href="AdminHome.jsp">Exit</a>
</body>
</html>