package com.fecund.shcart.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Order;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.Subcategory;
import com.fecund.shcart.entity.User;

public interface ProductData_IF {
	public int insertCategory(Category category);
	public int insertSubCategory(Subcategory subcategory);
	public int insertProduct(Product product);
	public ArrayList<Category> getCategory();
	public ArrayList<Subcategory> getSubCategory();
	public ArrayList<Product> getData();
	public Product addToCart(Product product);
	public ArrayList <Product> searchEngine(String name);
	public List<Product> reviewItem(String[] list, String[] quantity, String[] productcost, String[] unitcost, String[] itemIndex);
	public String[] getQuantity(String[] productid);
	
	public int getQuantity(String id);
	public ArrayList<Product> checkoutOrder(ArrayList<Product> list, Object userid,
			String username, String address, float total);
	
	public ArrayList<Order> updateOrderStatus(String[] orderid,
			String[] orderstatus);
	public int deleteProductDb(int id);
	public ArrayList<Order> SearchOrder(String name);
	public ArrayList<Product> checkoutOrder(ArrayList<Product> list,
			int userid, String address, float total);
}
