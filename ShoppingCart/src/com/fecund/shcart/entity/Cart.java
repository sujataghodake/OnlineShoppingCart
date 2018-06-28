package com.fecund.shcart.entity;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Cart {
	private ArrayList CartItems = new ArrayList();
	
	private double OrderTotal;

	public int getLineItemCount() {
		return CartItems.size();
	}

	public void deleteCartItem(String ItemIndex) {
		int itemIndex = 0;
		try {
			itemIndex = Integer.parseInt(ItemIndex);
			System.out.println("array length is="+CartItems.size());
			CartItems.remove(itemIndex - 1);
			calculateOrderTotal();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	}
		public void updateCartItem(String ItemIndex, int quantity2) {
		double TotalCost = 0.0;
		double UnitCost = 0.0;
		int quantity = 0;
		int itemIndex = 0;
		Product product = null;
		try {
			itemIndex = Integer.parseInt(ItemIndex);
			quantity = quantity2;
			if (quantity > 0) {
				product = (Product) CartItems.get(itemIndex - 1);
				UnitCost = product.getUnitCost();
				TotalCost = UnitCost * quantity;
				product.setProductquantity(quantity);
				product.setTotalCost(TotalCost);
				calculateOrderTotal();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public int addCartItem(int productid, String ProductName, String Description,
			float price, int quantity2, HttpServletRequest request) throws ServletException, IOException {
		float totalCost = 0;
		float unitCost = 0;
		int quantity = 0;
		Product product = new Product();
		
		try {
		
			unitCost = price;
			quantity = 1;
			System.out.println("quantity is="+quantity);
			if(CartItems.size()!=0)
			{
			for(int i=0;i<CartItems.size();i++)
			{
			int id = ((Product) CartItems.get(i)).getProductId();
			System.out.println("present id from arraylist is=&="+id);
			System.out.println("coming id from user is=&="+productid);
			if(productid==id)
			{
			return 0;
			}
			}
			}
			if (quantity > 0) {
				System.out.println("coming on cart");
				totalCost = unitCost * quantity;
				product.setProductId(productid);
				product.setProductName(ProductName);
				product.setDescription(Description);
				product.setUnitCost(unitCost);
				product.setProductquantity(quantity);
				product.setTotalCost(totalCost);
				CartItems.add(product);
				System.out.println("size is="+CartItems);
				calculateOrderTotal();
			}

		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return 1;
	}

	public void addCartItem(Product cartItem) {
		CartItems.add(cartItem);
	}

	public Product getCartItem(int ItemIndex) {
		Product cartItem = null;
		if (CartItems.size() > ItemIndex) {
			cartItem = (Product) CartItems.get(ItemIndex);
		}
		return cartItem;
	}

	protected void calculateOrderTotal() {
		double Total = 0;
		System.out.println("coming here now  ");
		for (int counter = 0; counter < CartItems.size(); counter++) {
			Product cartItem = (Product) CartItems.get(counter);
			Total += cartItem.getTotalCost();
			System.out.println("total value is="+Total);

		}
		setOrderTotal(Total);
	}

	public ArrayList getCartItems() {
		return CartItems;
	}

	public void setCartItems(ArrayList CartItems) {
		this.CartItems = CartItems;
	}

	public double getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(double OrderTotal) {
		this.OrderTotal = OrderTotal;
	}


	public void updateCartItem1(int itemIndex1, int quantity1) {
		double TotalCost = 0.0;
		double UnitCost = 0.0;
		Product product = null;
		try {
			if (quantity1 > 0) {
				product = (Product) CartItems.get(itemIndex1 - 1);
				UnitCost = product.getUnitCost();
				TotalCost = UnitCost * quantity1;
				product.setProductquantity(quantity1);
				product.setTotalCost(TotalCost);
				calculateOrderTotal();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
}

