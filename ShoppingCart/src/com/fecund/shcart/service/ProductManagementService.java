package com.fecund.shcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fecund.shcart.db.ProductDataImpl;
import com.fecund.shcart.db.ProductData_IF;
import com.fecund.shcart.db.UserDataImpl;
import com.fecund.shcart.db.UserData_IF;
import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Order;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.Subcategory;
import com.fecund.shcart.entity.User;

public class ProductManagementService {
	public int insertCategory(Category category) {
		ProductData_IF productdata = new ProductDataImpl();
		int i=productdata.insertCategory(category);
		return i;
	
		
	}
	
	public int insertSubCategory(Subcategory subcategory) {
	
		ProductData_IF productdata = new ProductDataImpl();
		int i=productdata.insertSubCategory(subcategory);	
		return i;
	}


	public int insertProduct(Product product) {
		List <Product> list;
		ProductData_IF productdata = new ProductDataImpl();
		int i=productdata.insertProduct(product);	
		return i;
	
	}

	public ArrayList<Product> searchEngine(String name) {
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList <Product> list=productdata.searchEngine(name);	
		
		return list;
	}

	public ArrayList<Product> checkoutOrder(ArrayList<Product> list, Object userid,
			String username, String address, float total) {
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList <Product> list1=productdata.checkoutOrder(list, userid,username,address,total);	
		return null;
	}



	public ArrayList<Order> updateOrderStatus(String[] orderid,
			String[] orderstatus) {
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList <Order> list1=productdata.updateOrderStatus(orderid,orderstatus);	
		return null;
	}

	public ArrayList<Order> SearchOrder(String name) {
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList <Order> list=productdata.SearchOrder(name);	
		
		return list;
	}

	public ArrayList<Product> checkoutOrder(ArrayList<Product> list,
			int userid, String address, float total) {
		ProductData_IF productdata = new ProductDataImpl();
		ArrayList <Product> list1=productdata.checkoutOrder(list, userid,address,total);	
		return null;
	}



}
