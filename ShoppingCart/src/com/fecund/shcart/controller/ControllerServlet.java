package com.fecund.shcart.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;
import java.util.*;
import javax.mail.Transport;
import javax.mail.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.fecund.shcart.db.ConnectionManager;
import com.fecund.shcart.db.ProductDataImpl;
import com.fecund.shcart.db.ProductData_IF;
import com.fecund.shcart.db.UserDataImpl;
import com.fecund.shcart.db.UserData_IF;
import com.fecund.shcart.entity.Cart;
import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Javamail;

import com.fecund.shcart.entity.Order;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.Subcategory;
import com.fecund.shcart.entity.User;
import com.fecund.shcart.service.ProductManagementService;
import com.fecund.shcart.service.UserManagementService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
 
public class ControllerServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public ControllerServlet() {
}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	System.out.println("at doPost....41");
    String action = request.getServletPath();
    System.out.println("action is="+action);


    try {
        switch (action) {
        case "/register":
            register(request, response);
            break;
        case "/login":
            login(request, response);
            break;
        case "/getUser":
        	getUser(request, response);
            break;
        case "/updateUser":
        	updateUser(request, response);
            break;
        case "/getwaitingUsers":
        	getwaitingUsers(request, response);
            break;
        case "/updatewaitingUsers":
        	updatewaitingUsers(request, response);
            break;
        case "/getAllUsers":
        	getAllUsers(request, response);
            break;
        case "/logout":
            logout(request, response);
            break;
        case"/insertCategory":
        	insertCategory(request, response);
             break;
        case"/insertSubCategory":
        	insertSubCategory(request, response);
             break;
        case"/insertProduct":
        	insertProduct(request, response);
             break;
        case"/add":
        	addToCart(request,response);
             break;
        case"/search":
        	searchEngine(request,response);
             break;
        case"/SearchOrder":
        	SearchOrder(request,response);
             break;
        case"/cancelcart":
        	cancelcart(request,response);
             break;
        case"/deleteDB":
        	deleteProductDb(request,response);
             break;
        case"/checkout":
        	checkout(request,response);
             break;
        case"/orderstatus":
        	orderstatus(request,response);
             break;
        case"/DeleteUser":
        	DeleteUser(request,response);
             break;
        case"/DeleteProduct":
        	deleteProduct(request,response);
             break;
       case"/searchedorderstatus":
    	   searchedorderstatus(request, response);
             break;
       case"/OrderItem":
    	   OrderItem(request, response);
             break;
             
       case"/OrderItemAdmin":
    	   OrderItemAdmin(request, response);
             break;
        default:
            myMethod(request, response);
            break;
        }
    } catch (SQLException ex) {
        throw new ServletException(ex);
    }
    
}
private void OrderItemAdmin(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	 int id = Integer.parseInt(request.getParameter("id"));
	 ProductDataImpl productdata = new ProductDataImpl();
		ArrayList<Order> list = productdata.getOrderItem(id);	
		request.setAttribute("list", list);
		  RequestDispatcher rd = request.getRequestDispatcher("OrderItemAdmin.jsp");
			rd.forward(request, response);
	
}
private void OrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 int id = Integer.parseInt(request.getParameter("id"));

	 ProductDataImpl productdata = new ProductDataImpl();
		ArrayList<Order> list = productdata.getOrderItem(id);
		
		request.setAttribute("list", list);
		  RequestDispatcher rd = request.getRequestDispatcher("OrderItem.jsp");
			rd.forward(request, response);
	
}
private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
	HttpSession session = request.getSession();
    String ItemIndex = request.getParameter("index");
    System.out.println("coming here with id=="+ItemIndex);
    System.out.println("deleted index is=="+ItemIndex);
	Cart cart = null;

	Object object = session.getAttribute("cart");
	if (object != null) {
		cart = (Cart) object;
	} else {
		cart = new Cart();
	}
	cart.deleteCartItem(ItemIndex);
	response.sendRedirect("ShoppingCart.jsp");

}
private void deleteProductDb(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	 int id = Integer.parseInt(request.getParameter("id"));
	 System.out.println("product id for deletion is="+id);
	 ProductData_IF productdata=new ProductDataImpl();
	 int i= productdata.deleteProductDb(id);
     if(i==1)
    	request.setAttribute("message", "Product Deleted Sucessfully...!!!");
	    RequestDispatcher rd = request.getRequestDispatcher("AllProduct.jsp");
		rd.forward(request, response);
    	 
	
}
private void orderstatus(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	HashMap<String, String> hm = new HashMap<String, String>();

	 String[] orderid = request.getParameterValues("orderid");
	 String[] orderstatus = request.getParameterValues("status");
     ProductManagementService productsercice = new ProductManagementService();
		//ArrayList <Order> list1=productsercice.updateOrderStatus(hm);
 	ArrayList <Order> list1=productsercice.updateOrderStatus(orderid,orderstatus);
		System.out.println("well done...!!");
		request.setAttribute("message", "Status Update Sucessfully...!!!");
	    RequestDispatcher rd = request.getRequestDispatcher("AllOrder.jsp");
		rd.forward(request, response);
		
}
private void searchedorderstatus(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	HashMap<String, String> hm = new HashMap<String, String>();

	 String[] orderid = request.getParameterValues("orderid");
	 String[] orderstatus = request.getParameterValues("status");
     ProductManagementService productsercice = new ProductManagementService();
 	ArrayList <Order> list1=productsercice.updateOrderStatus(orderid,orderstatus);
		System.out.println("well done...!!");
		request.setAttribute("message", "Status Update Sucessfully...!!!");
	    RequestDispatcher rd = request.getRequestDispatcher("SearchedOrder.jsp");
		rd.forward(request, response);
		
}
private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
User user1=new User();
//User user=new User();
int userid=0;
HttpSession session = request.getSession();
	if(null == list)
	{
		if(null != request.getSession().getAttribute("list"))
		{
			list = (ArrayList<Product>)request.getSession().getAttribute("list");
			//Object userid=(Object)session.getAttribute("userid");
			user1=(User) session.getAttribute("user");
			//for(int i=0;i<user1.size();i++){
				userid=user1.getUserid();
				System.out.println("coming here");
			//}
			//String username=(String)session.getAttribute("Customer");
			//System.out.println("11=="+username);
			System.out.println("22=="+userid);
			
			System.out.println("checkout list size is="+list.size());
			String address=request.getParameter("address");
			float total=Float.parseFloat(request.getParameter("total"));
			
			System.out.println("coming total cost result is="+total);
			ProductManagementService productsercice = new ProductManagementService();
			//ArrayList <Product> list1=productsercice.checkoutOrder(list,userid,username,address,total);	
			ArrayList <Product> list1=productsercice.checkoutOrder(list,userid,address,total);
			
			
		}
		request.setAttribute("message", "Ordered Sucessfully...!!!");
		    RequestDispatcher rd = request.getRequestDispatcher("CustomerHome.jsp");
			rd.forward(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("OrderReview.jsp");
			rd.forward(request, response);
		}
}
private void cancelcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);

if(session!=null)
{
session.invalidate();
request.setAttribute("errMessage", "Cancelled the Cart");
RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerHome.jsp");
requestDispatcher.forward(request, response);
}
	
}
private void searchEngine(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

	String name = request.getParameter("name");
	System.out.println("searched name=="+name);
	if(name.isEmpty())
	{
		String nextPage = "/CustomerHome.jsp";
		request.setAttribute("errMessage", "Please Enter Input");
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	else{
	ProductManagementService productsercice = new ProductManagementService();
	ArrayList <Product> list=productsercice.searchEngine(name);	
	
	if(!list.isEmpty()) {
	HttpSession session = request.getSession();
	request.setAttribute("list", list);
	
 	String nextPage = "/SearchEngine.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(nextPage);
	rd.forward(request, response);
	}
	else
	{
	
		request.setAttribute("errMessage", "Product not found");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerHome.jsp");
		requestDispatcher.forward(request, response);
		
	}
	}
}
private void SearchOrder(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	String name = request.getParameter("name");
	System.out.println("searched order name=="+name);
	if(name.isEmpty())
	{
		String nextPage = "/AdminHome.jsp";
		request.setAttribute("errMessage", "Please Enter Input");
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	else{
	ProductManagementService productsercice = new ProductManagementService();
	ArrayList <Order> list=productsercice.SearchOrder(name);	
	
	if(!list.isEmpty()) {
		System.out.println("coimg at 292 in controllerservlet");
	request.setAttribute("list", list);
	RequestDispatcher rd = request.getRequestDispatcher("SearchedOrder.jsp");
	rd.forward(request, response);
	}
	else
	{
	
		request.setAttribute("errMessage", "No any Order for this Customer");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
		requestDispatcher.forward(request, response);
		
	}
	}
	
}
private void DeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 int UserId = Integer.parseInt(request.getParameter("userid"));
	 System.out.println("product id is="+UserId);
	 UserData_IF userdata=new UserDataImpl();
	 int i= userdata.DeleteUser(UserId);
	 if(i==1)
	 {
		 
		 HttpSession session = request.getSession(false); //Fetch session object
		 if(session!=null) //If session is not null
		 {
		 session.invalidate(); //removes all session attributes bound to the session
		 request.setAttribute("errMessage", "Deleted account successfully");
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.jsp");
		 requestDispatcher.forward(request, response);
		 } 
	 }
	
}
protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	Product product= new Product();
	HttpSession session = request.getSession();
	System.out.println("cominggggg at 76");
	 int id = Integer.parseInt(request.getParameter("id"));
	String quant = request.getParameter("quantity");
	 product.setProductId(id);
	 ProductData_IF productdata=new ProductDataImpl();
	 Product product1= productdata.addToCart(product);
	 
	 if(product1!=null)
	 {
		 int productid=product1.getProductId();
	 String Productname=product1.getProductName();
	 float price=product1.getProductPrice();
	 String description=product1.getDescription();
	 int quantity=product1.getQuantity();
	Cart cart = null;
	Object object = session.getAttribute("cart");
	if (object != null) {
		cart = (Cart) object;
	} else {
		cart = new Cart();
		session.setAttribute("cart", cart);
	}	
    int i=cart.addCartItem(productid,Productname, description, price, quantity,request);
    if(i==0)
    {
    	System.out.println("476---");
    	 request.setAttribute("message", "This Product already added to cart, Please increase quantity..!!");
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerHome.jsp");
		 requestDispatcher.forward(request, response);
    }
    else
    {
    User user=new User();
    String username=(String) session.getAttribute("Customer");
    user.setUsername(username);
    UserManagementService userservice = new UserManagementService();
    user = userservice.getUser(user);
    int userid=user.getUserid();
    System.out.println("33="+userid);
    session.setAttribute("userid", userid);
    session.setAttribute("cart1", i);
    request.setAttribute("message", "Product added to cart");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerHome.jsp");
	requestDispatcher.forward(request, response); 
	 }
	 }
	
}
private void register(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String email = request.getParameter("email");
	String contactno = request.getParameter("contactno");
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String confirmpassword = request.getParameter("confirmpassword");
	String active = request.getParameter("active");

	// validate given input
	if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || contactno.isEmpty()
		 || username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
		request.setAttribute("message", "Please Fill all Fields.....!!");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Register.jsp");
		requestDispatcher.forward(request, response);
	} else {
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setContactno(contactno);
		user.setUsername(username);
		user.setPassword(password);
		user.setConfirmpassword(confirmpassword);
		user.setStatus("Waiting");
		user.setUsertype(1);
         

		UserManagementService userservice = new UserManagementService();
		int i = userservice.addUser(user);
		if(i==1)
		{
			request.setAttribute("message","Duplicate registration entry");
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Register.jsp");
			requestDispatcher.forward(request, response); 
		}
		System.out.println("user successfully registered");

		if (i != 1) {
			request.setAttribute("successmessage", "Registered Successful");
			RequestDispatcher rd = request
					.getRequestDispatcher("UserLogin.jsp");
			rd.forward(request, response);
		}
	}
}

private void login(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	UserDataImpl userdata=new UserDataImpl();
	 HttpSession session = request.getSession(false);
	 if(null!=session)
	 session.invalidate();
	String username = request.getParameter("username");
	 String password = request.getParameter("password");
	 if(username.isEmpty() || username.isEmpty() )
		{
			System.out.println("coming at 655");
			request.setAttribute("message", "Please Enter Credential....!!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.jsp");
			requestDispatcher.forward(request, response);
		}
	 User user = new User();
	 String forwardTo = null;
	 
	 user.setUsername(username);
	 user.setPassword(password);
	 UserManagementService userservice = new UserManagementService();
	 try
	 {
		session= request.getSession(true);
	 String userValidate=userservice.verifyUser(user);
	 session.setAttribute("SessionUser", user);
	
		 if(userValidate.equals("Admin_Role"))
	 { 
		 forwardTo = "AdminHome.jsp";
		 User user1=userdata.getUserId(username);
		 System.out.println("Admin's Home");
		 session.setAttribute("Admin", username);
		 session.setAttribute("admin", user1);
	 }
	 else if(userValidate.equals("Guest_Role"))
	 {
		 System.out.println("Guest Home");
		 
		 forwardTo = "Guest.jsp";
		 session.setAttribute("Guest", username);
	 
	 }
	 else if(userValidate.equals("Customer_Role"))
	 {
		 //HttpSession sess
		 System.out.println("Customer's Home");
		 
		 User user2=userdata.getUserId(username);
		 forwardTo = "CustomerHome.jsp";
		 session.setAttribute("Customer", username);
		 session.setAttribute("user", user2);
	 
	 }
	 else if(userValidate.equals("Customer_Reject"))
	 {
		
        request.setAttribute("errMessage", userValidate);
		 request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
	 
	 }
	 else
	 {
		 session.setAttribute("SessionUser", null);
		 System.out.println("Error message = "+userValidate);
		 request.setAttribute("errMessage", userValidate);
		 
		 request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
	 }
	 //auto session expire in 1 min
	 //session.setMaxInactiveInterval(1*60);
	 request.setAttribute("username", username);
	 
	 request.getRequestDispatcher(forwardTo).forward(request, response);
	 }
	 catch (IOException e1)
	 {
	 e1.printStackTrace();
	 }
	 catch (Exception e2)
	 {
	 e2.printStackTrace();
	 }
}
private void getUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	PrintWriter out = response.getWriter();
	HttpSession session = request.getSession();
	String username = (String) session.getAttribute("Customer");
	System.out.println(username);
	User user = new User();
	user.setUsername(username);
	UserManagementService userservice = new UserManagementService();
	user = userservice.getUser(user);
	int userid=user.getUserid();
    System.out.println("dvfdhsdfvd id=="+userid);
    session.setAttribute("userid", userid);
	session.setAttribute("user", user);
	String nextPage = "/UserData.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(nextPage);
	rd.forward(request, response);
	
}

private void getAllUsers(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	   User user = new User();
       int i=1;
	UserManagementService userservice = new UserManagementService();
	List<User> list = userservice.getAllUsers(user,i);
	// create session
	HttpSession session = request.getSession();
	request.setAttribute("empList", list);

	String nextPage ="/AllUser.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(nextPage);
	rd.forward(request, response);

}

private void updateUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String email = request.getParameter("email");
	String contactno = request.getParameter("contactno");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String confirmpassword = request.getParameter("confirmpassword");
	System.out.println("coming username to update is="+username);
	User user = new User();
	user.setFirstname(firstname);
	user.setLastname(lastname);
	user.setEmail(email);
	user.setContactno(contactno);
	user.setUsername(username);
	user.setPassword(password);
	user.setConfirmpassword(confirmpassword);
 	UserManagementService userservice = new UserManagementService();
	HttpSession session = request.getSession();
	session.setAttribute("user", user);
	int i = userservice.updateUser(user);
	if (i == 1) {
		request.setAttribute("message", "User update successfully");
		RequestDispatcher rd = request
				.getRequestDispatcher("UserData.jsp");
		rd.forward(request, response);
	}
	
}
private void getwaitingUsers(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	

	UserManagementService userservice = new UserManagementService();
	ArrayList<User> list = userservice.getWaitingUsers();
	// create session
	HttpSession session = request.getSession();
	request.setAttribute("empList", list);
    String nextPage = "/UserEntryApproval.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(nextPage);
	rd.forward(request, response);
	
}
private void updatewaitingUsers(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {

	 String[] UserId = request.getParameterValues("userid");
	 String[] UserStatus = request.getParameterValues("status");

			UserManagementService userservice = new UserManagementService();
			ArrayList<User> list=userservice.updateWaitingUsers(UserId,UserStatus);
			System.out.println("well done..Update User sucessfully...!");
			request.setAttribute("list", list);
			request.setAttribute("message", "Status Update Sucessfully...!!!");
		    RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
			rd.forward(request, response);
	}
private void logout(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	
	 
HttpSession session = request.getSession(false); //Fetch session object

if(session!=null) //If session is not null
{
session.invalidate(); //removes all session attributes bound to the session
request.setAttribute("errMessage", "You have logged out successfully");
RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.jsp");
requestDispatcher.forward(request, response);
System.out.println("Logged out");
}	

}
private void insertCategory(HttpServletRequest request,
		HttpServletResponse response)  throws SQLException, IOException, ServletException {

	String categoryName = request.getParameter("CategoryName");
	System.out.println("coming category name is="+categoryName);
	if(categoryName.isEmpty())
	{
		System.out.println("coming at 655");
		request.setAttribute("message", "Please Enter CategoryName");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("addCategory.jsp");
		requestDispatcher.forward(request, response);
	}
	else
	{
	Category category=new Category();
	Category category1=new Category();
	category.setCategoryname(categoryName);
	ProductManagementService productsercice = new ProductManagementService();
	int i=productsercice.insertCategory(category);
	if(i==1)
	{
	 PrintWriter out = response.getWriter(); 
     out.println ("<script>"); 
     out.println ("window.close()"); 
     out.println ("</script>"); 
	}
	}
}
	

private void insertSubCategory(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	String subCategoryName = request.getParameter("SubCategoryName");
	String categoryid=request.getParameter("categoryid");
	if(subCategoryName.isEmpty() || categoryid.isEmpty())
	{
		System.out.println("coming at 655");
		request.setAttribute("message", "Please fill all fields...!!");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("addSubCategory.jsp");
		requestDispatcher.forward(request, response);
	}
	else
	{
	
	Category category=new Category();
	Subcategory subcat=new Subcategory();
	Category cat=new Category();

	System.out.println("categoryid="+categoryid);
	category.setCategoryid(Integer.parseInt(categoryid));
	Subcategory subcategory=new Subcategory();
	subcategory.setSubcategoryname(subCategoryName);
	subcategory.setCat(category);
	ProductManagementService productsercice = new ProductManagementService();
	int i= productsercice.insertSubCategory(subcategory);
	if(i==1)
	{
	 PrintWriter out = response.getWriter(); 
     out.println ("<script>"); 
     out.println ("window.close()"); 
     out.println ("</script>");
	}
	}
	
	
}

private void insertProduct(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {
	String productname = request.getParameter("name");
	String productprice =request.getParameter("price");
	String quantity = request.getParameter("quantity");
	String productdescription = request.getParameter("description");
	if(productname.isEmpty()|| productprice.isEmpty()||quantity.isEmpty() || productdescription.isEmpty())
	{
		System.out.println("coming at 715");
		request.setAttribute("message", "Please fill all fields");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("addProduct.jsp");
		requestDispatcher.forward(request, response);
	}
	else{
	int subcategoryid=Integer.parseInt(request.getParameter("subcategoryid"));
	System.out.println("subcat id is="+subcategoryid);
	Product product=new Product();
	Subcategory subcategory=new Subcategory();
	subcategory.setSubcategoryid(subcategoryid);
	product.setProductName(productname);
	product.setProductPrice(Float.parseFloat(productprice));
	product.setQuantity(Integer.parseInt(quantity));
	product.setDescription(productdescription);
	product.setSubcat(subcategory);
	ProductManagementService productsercice = new ProductManagementService();
	int i= productsercice.insertProduct(product);
	if(i==1)
	{
	 PrintWriter out = response.getWriter(); 
     out.println ("<script>"); 
     out.println ("window.close()"); 
     out.println ("</script>"); 
	}
	}
}
private void myMethod(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	System.out.println("invalid");
}
}
