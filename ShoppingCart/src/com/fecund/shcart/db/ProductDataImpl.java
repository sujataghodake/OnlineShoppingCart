package com.fecund.shcart.db;

import java.io.File;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.InputStream;

import com.fecund.shcart.entity.Cart;
import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Order;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.Subcategory;
import com.fecund.shcart.entity.User;
import com.mysql.jdbc.Statement;

public class ProductDataImpl implements ProductData_IF {
	Connection con = null;
	PreparedStatement ps;
	int CatgoryId;
	String CatgoryName;
	Category category1 = new Category();
	Category category = new Category();

	public int insertCategory(Category category) {
		try {
			con = ConnectionManager.getConnection();
			String categoryName = category.getCategoryname();
			System.out.println("categorynme=" + categoryName);
			String query = "INSERT INTO category (categoryname) VALUES (?)";
			ps = con.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.executeUpdate();
			System.out.println("Updated category");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public ArrayList<Category> getCategory() {
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			con = ConnectionManager.getConnection();
			String q = "select * from category";
			PreparedStatement ps = con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category cat = new Category();
				cat.setCategoryid(rs.getInt("categoryid"));
				cat.setCategoryname(rs.getString("categoryname"));
				list.add(cat);
				System.out.println("Get category");
				System.out.println("Category list size=" + list.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int insertSubCategory(Subcategory subcategory) {
		try {
			con = ConnectionManager.getConnection();
			Category category;
			String subcategoryName = subcategory.getSubcategoryname();
			category = subcategory.getCat();
			int categoryid = category.getCategoryid();
			System.out.println("Subcategoryname=" + subcategoryName);
			System.out.println("categoryid=" + categoryid);
			String query = "INSERT INTO subcategory (subcategoryname,categoryid) VALUES (?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, subcategoryName);
			ps.setInt(2, categoryid);
			ps.executeUpdate();
			System.out.println("Updated Subcategory");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
		return 1;

	}

	public ArrayList<Subcategory> getSubCategory() {
		ArrayList<Subcategory> list = new ArrayList<Subcategory>();
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from subcategory");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Subcategory subcategory1 = new Subcategory();
				Category category = new Category();
				int categoryid1 = resultSet.getInt("categoryid");
				int subcategoryid = resultSet.getInt("subcategoryid");
				String subcategoryName1 = resultSet
						.getString("subcategoryname");
				System.out.println("result is=" + categoryid1);
				System.out.println("result is=" + subcategoryid);
				System.out.println("result is=" + subcategoryName1);
				subcategory1.setSubcategoryid(subcategoryid);
				subcategory1.setSubcategoryname(subcategoryName1);
				category.setCategoryid(categoryid1);
				subcategory1.setCat(category);
				list.add(subcategory1);
				System.out.println("Get category");
				System.out.println("Subcategory list size=" + list.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertProduct(Product product) {
		int categoryid = 0;
		try {
			con = ConnectionManager.getConnection();
			Subcategory subcategory;
			String productname = product.getProductName();
			Float productprice = product.getProductPrice();
			int quantity = product.getQuantity();
			String productdescription = product.getDescription();
			subcategory = product.getSubcat();
			int subcategoryid = subcategory.getSubcategoryid();
			ps = con.prepareStatement("select categoryid from subcategory where subcategoryid=?");
			ps.setInt(1, subcategoryid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				categoryid = resultSet.getInt("categoryid");
			}
	
			String query = "INSERT INTO product (productname,productprice,productquantity,description,subcategoryid,categoryid) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, productname);
			ps.setFloat(2, productprice);
			ps.setInt(3, quantity);
			ps.setString(4, productdescription);
			ps.setInt(5, subcategoryid);
			ps.setInt(6, categoryid);
			ps.executeUpdate();
			System.out.println("Updated product");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
		return 1;

	}

	public ArrayList<Product> getData() {
		ArrayList<Product> list = new ArrayList<Product>();
		String categoryname = null;
		String subcategoryname = null;
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from product");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				Subcategory subcategory = new Subcategory();
				Category cat = new Category();
				int categoryid = resultSet.getInt("categoryid");
				int subcategoryid = resultSet.getInt("subcategoryid");
				ps = con.prepareStatement("select categoryname,subcategoryname from subcategory LEFT JOIN category ON category.categoryid=subcategory.categoryid where subcategoryid=?");
				ps.setInt(1, subcategoryid);
				ResultSet resultSet1 = ps.executeQuery();
				while (resultSet1.next()) {
					categoryname = resultSet1.getString("categoryname");
					System.out.println("1==" + categoryid);
					System.out.println("1==" + categoryname);
					subcategoryname = resultSet1.getString("subcategoryname");
					System.out.println("2==" + subcategoryid);
					System.out.println("2==" + subcategoryname);
					cat.setCategoryid(categoryid);
					cat.setCategoryname(categoryname);
					subcategory.setSubcategoryid(subcategoryid);
					subcategory.setSubcategoryname(subcategoryname);
					subcategory.setCat(cat);
				}
				int productid = resultSet.getInt("productid");
				String productname = resultSet.getString("productname");
				Float productprice = resultSet.getFloat("productprice");
				int productquantity = resultSet.getInt("productquantity");
				String productdescription = resultSet.getString("description");
				product.setProductId(productid);
				product.setProductName(productname);
				product.setProductPrice(productprice);
				product.setProductquantity(productquantity);
				product.setDescription(productdescription);
				product.setSubcat(subcategory);
				product.setCat(cat);
				product.setCategoryName(categoryname);
				product.setSubcategoryName(subcategoryname);
				list.add(product);
				System.out.println("Get product");
				System.out.println("Product list size=" + list.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
		return list;
	}

	@Override
	public Product addToCart(Product product) {
		int productid = product.getProductId();
		Product product1 = null;
		// HttpSession session = request.getSession();
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from product where productid=?");
			ps.setInt(1, productid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				product1 = new Product();
				String productname = resultSet.getString("productname");
				float productprice = resultSet.getFloat("productprice");
				int productquantity = resultSet.getInt("productquantity");
				System.out.println("productdata quantity=" + productquantity);
				String productdescription = resultSet.getString("description");
				int categoryid = resultSet.getInt("categoryid");
				int subcategoryid = resultSet.getInt("subcategoryid");
				product1.setProductId(productid);
				product1.setProductName(productname);
				product1.setProductPrice(productprice);
				product1.setProductquantity(productquantity);
				product1.setDescription(productdescription);
				System.out.println(productname);
				// product.setSubcat(subcat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product1;

	}

	public ArrayList<Product> searchEngine(String name) {
		ArrayList<Product> list = new ArrayList<Product>();
		String categoryname = null;
		String subcategoryname = null;
		int subcategoryid = 0;
		int categoryid = 0;
		int userid =0;
		// by category
		try {
			con = ConnectionManager.getConnection();
		
			ps = con.prepareStatement("select categoryid,categoryname from category where categoryname LIKE '%"
					+ name + "%'");
			ResultSet resultSet = ps.executeQuery();
			System.out.println("searched in category");
			while (resultSet.next()) {
				categoryid = resultSet.getInt("categoryid");
				categoryname = resultSet.getString("categoryname");
				ps = con.prepareStatement("select subcategoryname,subcategoryid from subcategory where categoryid=?");
				ps.setInt(1, categoryid);
				ResultSet resultSet1 = ps.executeQuery();
				while (resultSet1.next()) {
					subcategoryname = resultSet1.getString("subcategoryname");
					subcategoryid = Integer.parseInt(resultSet1
							.getString("subcategoryid"));

					ps = con.prepareStatement("select * from product where subcategoryid=?");
					ps.setInt(1, subcategoryid);
					ResultSet resultSet3 = ps.executeQuery();
					while (resultSet3.next()) {
						Product product = new Product();
						int productid = resultSet3.getInt("productid");
						String productname = resultSet3
								.getString("productname");
						System.out.println("searched data is=" + productname);
						float productprice = resultSet3
								.getFloat("productprice");
						int productquantity = resultSet3
								.getInt("productquantity");
						String productdescription = resultSet3
								.getString("description");
						product.setProductId(productid);
						product.setProductName(productname);
						product.setProductPrice(productprice);
						product.setProductquantity(productquantity);
						product.setDescription(productdescription);
						product.setCategoryName(categoryname);
						product.setSubcategoryName(subcategoryname);
						list.add(product);
					}
					
				}

			}
        //by subcategory
			ps = con.prepareStatement("select * from subcategory where subcategoryname LIKE '%"+ name + "%'    ");
			System.out.println("control in subcat search");
			ResultSet resultSet4 = ps.executeQuery();
			while (resultSet4.next()) {
				categoryid = Integer.parseInt(resultSet4
						.getString("categoryid"));
				subcategoryname=resultSet4.getString("subcategoryname");
				subcategoryid= Integer.parseInt(resultSet4
						.getString("subcategoryid"));
		
				ps = con.prepareStatement("select categoryname from category where categoryid=?");
				ps.setInt(1, categoryid);
				ResultSet resultSet5 = ps.executeQuery();
				while (resultSet5.next()) {
					categoryname = resultSet5.getString("categoryname");

					ps = con.prepareStatement("select * from product where subcategoryid=?");
					ps.setInt(1, subcategoryid);
					ResultSet resultSet6 = ps.executeQuery();
					while (resultSet6.next()) {
						Product product = new Product();
						int productid = resultSet6.getInt("productid");
						String productname = resultSet6
								.getString("productname");
						System.out.println("searched subcat data is=" + productname);
						float productprice = resultSet6
								.getFloat("productprice");
						int productquantity = resultSet6
								.getInt("productquantity");
						String productdescription = resultSet6
								.getString("description");
						product.setProductId(productid);
						product.setProductName(productname);
						product.setProductPrice(productprice);
						product.setProductquantity(productquantity);
						product.setDescription(productdescription);
						product.setCategoryName(categoryname);
						product.setSubcategoryName(subcategoryname);
						list.add(product);
					}

				}

			}
			ps = con.prepareStatement("select * from product where productname LIKE '%"
					+ name + "%'");
			System.out.println("Searched in product");
			ResultSet resultSet7 = ps.executeQuery();
			while (resultSet7.next()) {
				Product product = new Product();
				categoryid = resultSet7.getInt("categoryid");
				ps = con.prepareStatement("select categoryname from category where categoryid=?");
				ps.setInt(1, categoryid);
				ResultSet resultSet8 = ps.executeQuery();
				while (resultSet8.next()) {
					categoryname = resultSet8.getString("categoryname");
				}
				ps = con.prepareStatement("select subcategoryname from subcategory where categoryid=?");
				ps.setInt(1, categoryid);
				ResultSet resultSet9 = ps.executeQuery();
				while (resultSet9.next()) {
					subcategoryname = resultSet9.getString("subcategoryname");
				}
				int productid = resultSet7.getInt("productid");
				String productname = resultSet7.getString("productname");
				System.out.println("searched data is=" + productname);
				float productprice = resultSet7.getFloat("productprice");
				int productquantity = resultSet7.getInt("productquantity");
				String productdescription = resultSet7.getString("description");
				product.setProductId(productid);
				product.setProductName(productname);
				product.setProductPrice(productprice);
				product.setProductquantity(productquantity);
				product.setDescription(productdescription);
				product.setCategoryName(categoryname);
				product.setSubcategoryName(subcategoryname);
				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> reviewItem(String[] list, String[] quantity,
			String[] productcost, String[] unitcost, String[] itemIndex) {
		List<Product> list1=new ArrayList<Product>();
		float totalprice = 0;
		String categoryname=null;
		String subcategoryname=null;
	  for(int i=0;i<list.length && i<quantity.length  && i<productcost.length && i<unitcost.length && i<itemIndex.length ;i++)
	  {
		  int productid=Integer.parseInt(list[i]);
		  int productquantity=Integer.parseInt(quantity[i]);
		  float productprice=Float.parseFloat(productcost[i]);
		  double unitcost1=Double.parseDouble(unitcost[i]);
		  String itemindex=itemIndex[i];
		  try{
			  con = ConnectionManager.getConnection();
		ps = con.prepareStatement("select * from product where productid=?");
		ps.setInt(1, productid);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			Product product = new Product();
			int categoryid = resultSet.getInt("categoryid");
			int subcategoryid = resultSet.getInt("subcategoryid");
			ps = con.prepareStatement("select categoryname from category where categoryid=?");
			ps.setInt(1, categoryid);
			ResultSet resultSet1 = ps.executeQuery();
			
			while (resultSet1.next()) {
				categoryname= resultSet1.getString("categoryname");
		
				ps = con.prepareStatement("select subcategoryname from subcategory where subcategoryid=?");
				ps.setInt(1, subcategoryid);
				ResultSet resultSet2 = ps.executeQuery();
				while (resultSet2.next()) {
					subcategoryname = resultSet2.getString("subcategoryname");
				}
			}
			String productname = resultSet.getString("productname");
			System.out.println("review product name="+productname);
			String productdescription = resultSet.getString("description");
			product.setProductId(productid);
			product.setProductName(productname);
			product.setProductPrice(productprice);
			product.setProductquantity(productquantity);
			product.setDescription(productdescription);
			System.out.println(productname);
			product.setCategoryName(categoryname);
			product.setSubcategoryName(subcategoryname);
			product.setUnitCost(unitcost1);
			product.setIndex(itemindex);
			//product.setTotalprice(totalprice);
			list1.add(product);
			}
		
		 HttpServletRequest request = null;
		   request.setAttribute("totalprice", totalprice);
		  }catch (Exception e)
			{
			e.printStackTrace();
			}
		  }
		return list1;
		
	  }
	@Override
	public ArrayList<Product> checkoutOrder(ArrayList<Product> list, Object userid,String username,
			String address,float total) {
		int quantity=0;
			System.out.println(list.size());
            java.util.Date date=new java.util.Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
            System.out.println("Today's date is="+sqlDate);
			try {
				con = ConnectionManager.getConnection();
				for (int i = 0; i < list.size(); i++) {
				String query = "insert into ProductOrder (userid,productid,username,amount,address,quantity,status,OrderDate,productname,TotalCost,unitcost)values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				
				System.out.println("updated 1");
				
				ps.setObject(1, userid);
				ps.setInt(2, (list.get(i).getProductId()));
				ps.setString(3, username);
				ps.setFloat(4, (list.get(i).getProductPrice()));
				ps.setString(5, address);
				ps.setInt(6, (list.get(i).getProductquantity()));
				ps.setString(7, "New");
				ps.setDate(8, sqlDate);
				ps.setString(9, (list.get(i).getProductName()));
				ps.setFloat(10, total);
				ps.setDouble(11, (list.get(i).getUnitCost()));
				System.out.println("updated 8");
				ps.executeUpdate();
				System.out.println("updated order Table");
				
				 ps = con.prepareStatement( "select productquantity from product where productid=?");
				ps.setInt(1, (list.get(i).getProductId()));
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					quantity = Integer.parseInt(resultSet.getString("productquantity"));
					quantity=quantity-(list.get(i).getProductquantity());
				 ps = con.prepareStatement("update product set productquantity=? where productid=?");
				 ps.setInt(1, quantity);
				 ps.setInt(2, (list.get(i).getProductId()));
				 ps.executeUpdate();
					System.out.println("updated quantity in order Table");
					
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	public ArrayList<Order> getAllOrderData()
	{
		ArrayList<Order> list = new ArrayList<Order>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ordermaster");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setUserid(rs.getInt("UserId"));
				int masterid=rs.getInt("idOrderMaster");
				order.setIdordermaster(masterid);
				order.setAddress(rs.getString("ShippingAddress"));
				order.setAmount(rs.getFloat("TotalPrice"));
				order.setStatus(rs.getString("status"));
				order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<Order> getNewOrderData()
	{
		ArrayList<Order> list = new ArrayList<Order>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ordermaster where Status='New'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setUserid(rs.getInt("UserId"));
				int masterid=rs.getInt("idOrderMaster");
				order.setIdordermaster(masterid);
				order.setAddress(rs.getString("ShippingAddress"));
				order.setAmount(rs.getFloat("TotalPrice"));
				order.setStatus(rs.getString("status"));
				order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public ArrayList<Order> getOrderData(int id)
	{
		ArrayList<Order> list = new ArrayList<Order>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ordermaster where UserId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setUserid(rs.getInt("UserId"));
				int masterid=rs.getInt("idOrderMaster");
				order.setIdordermaster(masterid);
				order.setAddress(rs.getString("ShippingAddress"));
				order.setAmount(rs.getFloat("TotalPrice"));
				order.setStatus(rs.getString("status"));
				order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<Order> getAllOrderItem()
	{
		//int id1=Integer.parseInt("id");
		ArrayList<Order> list = new ArrayList<Order>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM orderitem");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setIdorderitem(rs.getInt("idOrderItem"));
				order.setOrderid(rs.getInt("orderId"));
				int productid=rs.getInt("ProductId");
				//order.setProductid(rs.getInt("ProductId"));
				ps = con.prepareStatement("SELECT * FROM product where productid=?");
				ps.setInt(1, productid);
				ResultSet rs3 = ps.executeQuery();
				while (rs3.next()) {
				order.setProductname(rs3.getString("productname"));
				}
				order.setProductid(productid);
				order.setUserid(rs.getInt("UserId"));
				order.setUnitprice(rs.getFloat("ItemPrice"));
				order.setQuantity(rs.getInt("ItemQuantity"));
				//order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public ArrayList<Order> getOrderItem(int id)
	{
		//int id1=Integer.parseInt("id");
		ArrayList<Order> list = new ArrayList<Order>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM orderitem where orderId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setIdorderitem(rs.getInt("idOrderItem"));
				order.setOrderid(rs.getInt("orderId"));
				int productid=rs.getInt("ProductId");
				//order.setProductid(rs.getInt("ProductId"));
				ps = con.prepareStatement("SELECT * FROM product where productid=?");
				ps.setInt(1, productid);
				ResultSet rs3 = ps.executeQuery();
				while (rs3.next()) {
				order.setProductname(rs3.getString("productname"));
				}
				order.setProductid(productid);
				order.setUserid(rs.getInt("UserId"));
				order.setUnitprice(rs.getFloat("ItemPrice"));
				order.setQuantity(rs.getInt("ItemQuantity"));
				//order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	@Override
	public ArrayList<Order> updateOrderStatus(String[] orderid,
			String[] orderstatus) {
		  for(int i=0; i<orderid.length; i++){
			  System.out.println("orderid are===="+orderid[i]);
			      }

			      for(int i=0; i<orderstatus.length; i++){
			  System.out.println("status resiult is==="+orderstatus[i]);
			      }
		try {
			con = ConnectionManager.getConnection();
			for(int i=0; i<orderstatus.length&&i<orderid.length; i++){
			String query = "update ordermaster set Status = ? where idOrderMaster=?";
			PreparedStatement ps = con.prepareStatement(query);
			
	       ps.setObject(1, orderstatus[i]);
	       ps.setObject(2, orderid[i]);
			
			ps.executeUpdate();
			System.out.println("updated order Table");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteProductDb(int id) {
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("delete from product where productid=?");
			ps.setInt(1, id);
		   ps.executeUpdate();
			System.out.println("deleted product sucessfully");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public ArrayList<Order> SearchOrder(String name) {
		ArrayList<Order> list = new ArrayList<Order>();
		int userid=0;
			try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select userid from user where username LIKE '%"
					+ name + "%'");
			
			//ps.setString(1, name);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userid= resultSet.getInt("userid");
			}
			ps = con.prepareStatement("select * from ordermaster where userid =?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order=new Order();
				order.setUserid(rs.getInt("UserId"));
				int masterid=rs.getInt("idOrderMaster");
				order.setIdordermaster(masterid);
				order.setAddress(rs.getString("ShippingAddress"));
				order.setAmount(rs.getFloat("TotalPrice"));
				order.setStatus(rs.getString("status"));
				order.setOrderdate(rs.getDate("OrderTime"));
				list.add(order);
				System.out.println(list.size());
				}
			System.out.println("in order search method="+list.size());
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
	
	}

	@Override
	public String[] getQuantity(String[] productid) {
		
	String[] quantity=new String[100];
		int x = 0;
		try {
			System.out.println("at 717..");
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select productquantity from product where productid=?");
			for(int i=0;i<productid.length;i++)
			{
				int id=Integer.parseInt(productid[i]);
				System.out.println("coming with id-"+id);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String quant = resultSet.getString("productquantity");
				   quantity[x++]=quant;
			}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}

	@Override
	public int getQuantity(String id) {
			int quantity=Integer.parseInt(id);
			int quant1=0;
			try {
				con = ConnectionManager.getConnection();
				ps = con.prepareStatement("select productquantity from product where productid=?");
				
				ps.setInt(1, quantity);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					quant1 = Integer.parseInt(resultSet.getString("productquantity"));
				}
				
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return quant1;
		
	}

	@Override
	public ArrayList<Product> checkoutOrder(ArrayList<Product> list,
			int userid, String address, float total) {
		int quantity=0;
		int id=0;
		System.out.println(list.size());
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        System.out.println("Today's date is="+sqlDate);
		try {
			con = ConnectionManager.getConnection();
			
			String query = "insert into ordermaster (userid,ShippingAddress,OrderTime,status,TotalPrice)values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setString(2,address );
			ps.setDate(3, sqlDate);
			ps.setString(4, "New");
			ps.setDouble(5, total);
			ps.executeUpdate();
			System.out.println("updated Masterorder Table");
			 for (int i = 0; i < list.size(); i++) {
			 ps = con.prepareStatement( "select idOrderMaster from ordermaster where userid=?");
				ps.setInt(1, userid);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					id=Integer.parseInt(resultSet.getString("idOrderMaster"));
					System.out.println("getting id="+id);
				}
				
				String query1 = "insert into orderitem (orderId,ProductID,UserId,ItemPrice,ItemQuantity)values(?,?,?,?,?)";
				 ps = con.prepareStatement(query1);
				
				ps.setInt(1, id);
				ps.setInt(2, (list.get(i).getProductId()));
				ps.setInt(3, userid);
				ps.setDouble(4,(list.get(i).getUnitCost()));
				ps.setInt(5, (list.get(i).getProductquantity()));
				//ps.setDouble(6, (list.get(i).getTotalCost()));
				ps.executeUpdate();
				System.out.println("updated orderItem Table");
				
					
			 ps = con.prepareStatement( "select productquantity from product where productid=?");
			ps.setInt(1, (list.get(i).getProductId()));
			ResultSet resultSet1 = ps.executeQuery();
			while (resultSet1.next()) {
				quantity = Integer.parseInt(resultSet1.getString("productquantity"));
				quantity=quantity-(list.get(i).getProductquantity());
			 ps = con.prepareStatement("update product set productquantity=? where productid=?");
			 ps.setInt(1, quantity);
			 ps.setInt(2, (list.get(i).getProductId()));
			 ps.executeUpdate();
				System.out.println("updated quantity in order Table");
				
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return null;
	}

	
}


