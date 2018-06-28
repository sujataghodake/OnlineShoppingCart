package com.fecund.shcart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fecund.shcart.db.ProductDataImpl;
import com.fecund.shcart.db.ProductData_IF;
import com.fecund.shcart.entity.Cart;
import com.fecund.shcart.entity.Product;

public class CartController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String string = request.getParameter("action");
		if (string != null && !string.equals("")) {
			if (string.equals("Update")) {
				updateCart(request, response);
			} else if (string.equals("Delete")) {
				deleteCart(request, response);
			} else if (string.equals("Buy Now")) {
				reviewCart(request, response);
			} else if (string.equals("Cancel Cart")) {

				cancelcart(request, response);
			}
		}
	}

	private void cancelcart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.removeAttribute("cart");
			request.setAttribute("errMessage", "Cancelled the Cart");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("CustomerHome.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	private void reviewCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] list = request.getParameterValues("itemId");
		System.out.println("coming here");
		if (list == null) {
			request.setAttribute("quantityMessage", "Please select Item to Buy");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("ShoppingCart.jsp");
			requestDispatcher.forward(request, response);
		} else {
			String[] quantity = request.getParameterValues("quantity");
			String[] productcost = request.getParameterValues("productcost");
			String[] Unitcost = request.getParameterValues("unitcost");
			String[] ItemIndex = request.getParameterValues("itemIndex");
			String index=request.getParameter("itemId");
			System.out.println("coming productid is="+index);
			
			
			ProductData_IF productdata = new ProductDataImpl();
			List<Product> list1 = productdata.reviewItem(list, quantity,
					productcost, Unitcost,ItemIndex);
			request.setAttribute("list1", list1);
			request.setAttribute("quantity", quantity);	
			String nextPage = "/OrderReview.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
	}

	protected void deleteCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String ItemIndex = request.getParameter("itemIndex");
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

	protected void updateCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int j=0;
		System.out.println("ccomig in here");
		HttpSession session = request.getSession();
		String[] quantity = request.getParameterValues("quantity");
		String[] ItemIndex = request.getParameterValues("itemIndex");
		String[] productid=request.getParameterValues("name");
		ProductData_IF productdata=new ProductDataImpl();
		String[] dbquantity=productdata.getQuantity(productid);
		
		for(int i=0;i<productid.length && i<dbquantity.length  && i<quantity.length;i++)
		{
		System.out.println("dataaa=" +productid[i]+  "db quantity=" +dbquantity[i]+ "user quantity=" +quantity[i]+ ".");
		}
		Cart cart = null;
		Object object = session.getAttribute("cart");
		if (object != null) {
			cart = (Cart) object;
		} else {
			cart = new Cart();
		}
		for (int i = 0; i < quantity.length &&  i < ItemIndex.length &&  i < dbquantity.length; i++) {
			int dbquant=Integer.parseInt(dbquantity[i]);
			int userquant=Integer.parseInt(quantity[i]);
			System.out.println("00="+dbquant);
			System.out.println("11="+userquant);
			if(userquant>=dbquant)
			{
				j=1;
				continue;
				
			}
			System.out.println("@@");
				int ItemIndex1 = Integer.parseInt(ItemIndex[i]);
				int quantity1=Integer.parseInt(quantity[i]);
				cart.updateCartItem1(ItemIndex1, quantity1);
		}
		if(j==1)
		{
			request.setAttribute("quantityMessage", "Quantity Out of stock..!!");
		}
		else
		{
			request.setAttribute("quantityMessage", "Quantity update successfully");
		}
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("ShoppingCart.jsp");
		requestDispatcher.forward(request, response);
	}
}