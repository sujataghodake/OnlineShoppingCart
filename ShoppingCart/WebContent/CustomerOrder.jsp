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
	ArrayList<User> list1=(ArrayList<User>)session.getAttribute("user");
	int id=0;
	for(int i=0;i<list1.size();i++)
	{
		id=list1.get(i).getUserid();
	}
	System.out.println(userid);
           ProductDataImpl productdata = new ProductDataImpl();
			ArrayList<Order> list = productdata.getOrderData(id);
			if(list.size()==0)
			{%>
			<p>There is no any order...!</p>
				
			<%}
			else{
			session.setAttribute("list", list);
			System.out.println(list.size());
	%>
	<P>
		<b>Products for sale:</b>
	</P>
	<form>
	<table width="100%" border="1">
		<th>Order Id</th>
		<th>Shipping Address</th>
		<th>Order Date</th>
	     <th>Total Price</th>
		<th>Order Status</th>
	
		<c:forEach items="${list}" var="order">
			<tr>
			<td> <%-- <a href="OrderItem.jsp"> ${order.idordermaster}</a>
			<input type="hidden" name="idordermaster" value="${order.idordermaster}"> --%> 
			<a name="action" href="OrderItem?id=<c:out value='${order.idordermaster}'/>">${order.idordermaster}</a>
			 
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
				<td>
				${order.status} <input type="hidden"
					name="orderdate" value="${order.status}">
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