
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.List,com.fecund.shcart.entity.*,com.fecund.shcart.db.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<form action="insertSubCategory" method="POST">
		<table>
			<tr>
				<td>SubCategory Name:<input type="text" name="SubCategoryName" required/></td>
			</tr>
			<%
				ProductData_IF productdata = new ProductDataImpl();
				ArrayList<Category> list = productdata.getCategory();
				request.setAttribute("list", list);
			%>
			<TR>
				<TD>category Name:<select name="categoryid">
						<c:forEach items="${list}" var="category">
							<option value="${category.categoryid}">${category.categoryname}</option>
						</c:forEach>
				</select>

				</td>
			</tr>
		</table>
		<input type="submit" value="Add SubCategory">
	</form>
	<%} %>
	<span style="color: red"><%=(request.getAttribute("message") == null) ? ""
						: request.getAttribute("message")%></span>
	<p><input type="button" onclick="window.close();" value="Exit"/></p>
	
</body>
</html>

