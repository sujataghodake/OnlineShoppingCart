package com.fecund.shcart.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.User;
import com.fecund.shcart.db.ConnectionManager;

/**
 * ABOUT CLASS
 * 
 * @author Sujata
 * 
 */
public class UserDataImpl implements UserData_IF {

	Connection con = null;

	public int addUser(User user) {
		int i=0;
		try {
			con = ConnectionManager.getConnection();
			String query = "insert into user (firstname,lastname,email,contactno,username,password,confirmpassword,status,usertype)values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getContactno());
			ps.setString(5, user.getUsername());
			ps.setString(6, user.getPassword());
			ps.setString(7, user.getConfirmpassword());
			ps.setString(8, user.getStatus());
			ps.setInt(9, user.getUsertype());
			ps.executeUpdate();
			System.out.println("in userdataimpl.... 55");
			

		} catch (SQLException e) {
			i=1;
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
		return i;
	}

	public String verifyUser(User user) {
		String userName = user.getUsername();
		String password = user.getPassword();
		String userNameDB = "";
		String passwordDB = "";
		int usertype;
		System.out.println("at line...78");

		try {
			con = ConnectionManager.getConnection();
			
			PreparedStatement ps = con
					.prepareStatement("select * from user where username=?");
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				User user1 = new User();
				userNameDB = resultSet.getString("username");
				passwordDB = resultSet.getString("password");
				usertype = resultSet.getInt("usertype");
				String status = resultSet.getString("status");
				user1.setUserid(resultSet.getInt("userid"));
				user1.setFirstname(resultSet.getString("firstname"));
				user1.setLastname(resultSet.getString("lastname"));
				user1.setEmail(resultSet.getString("email"));
				user1.setContactno(resultSet.getString("contactno"));
				user1.setUsername(userNameDB);
				user1.setPassword(passwordDB);

				System.out.println("User status=" + status);
				if (userName.equals(userNameDB) && password.equals(passwordDB)
						&& usertype == 1 && status.equals("Approved"))
					return "Customer_Role";
				else if (userName.equals(userNameDB) && password.equals(passwordDB)
						&& usertype == 1 && status.equals("Reject"))
					//return "Customer_Reject";
					return "You are rejected...";
				else if(userName.equals(userNameDB) && password.equals(passwordDB)
						&& usertype == 1 && status.equals("Waiting"))
					return "Waiting for Approval...!!";

				else if (userName.equals(userNameDB)
						&& password.equals(passwordDB) && usertype == 2
						&& status.equals("Approved"))
					return "Admin_Role";
				else if (userName.equals(userNameDB)
						&& password.equals(passwordDB) && usertype == 3
						&& status.equals("Approved"))
					return "Guest_Role";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Invalid user credentials";
	}

	public User getUser(User user) {

		Connection con;
		try {
			con = ConnectionManager.getConnection();
			String query = "SELECT * FROM user where username=?";
			PreparedStatement ps = con.prepareStatement(query);
			String name = user.getUsername();
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setContactno(rs.getString("contactno"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setConfirmpassword(rs.getString("confirmpassword"));
			

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}

	@Override
	public User updateUser(User user) {
		try {
			con = ConnectionManager.getConnection();
			String query = "update user set firstname = ?, lastname = ?, email = ?, contactno = ?, password = ?, confirmpassword = ? where username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getContactno());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getConfirmpassword());
			ps.setString(7, user.getUsername());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;

	}

	public ArrayList<User> getWaitingUsers() {
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			String query = "SELECT * FROM user where status ='Waiting'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setContactno(rs.getString("contactno"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setConfirmpassword(rs.getString("confirmpassword"));
				user.setStatus(rs.getString("status"));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<User> updateWaitingUsers(String[] userId,String[] userStatus) {
		ArrayList<User> list = new ArrayList<User>();
		 for(int i=0; i<userId.length; i++){
			  System.out.println("userid are===="+userId[i]);
			      }

			      for(int i=0; i<userStatus.length; i++){
			  System.out.println("status resiult is==="+userStatus[i]);
			      }
		try {
			con = ConnectionManager.getConnection();
			for(int i=0; i<userStatus.length&&i<userId.length; i++){
			String query = "update user set STATUS = ? where userid=?";
			PreparedStatement ps = con.prepareStatement(query);
			
	       ps.setObject(1, userStatus[i]);
	       ps.setObject(2, userId[i]);
			
			ps.executeUpdate();
			System.out.println("updated order Table");
			
			String query1 = "SELECT * FROM user where status ='Approved' && usertype='1'";
			ps = con.prepareStatement(query1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setContactno(rs.getString("contactno"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setConfirmpassword(rs.getString("confirmpassword"));
				user.setStatus(rs.getString("status"));
				list.add(user);
				
			}
			System.out.println("at line 236 in userdataimpl");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<User> updateWaitingUsers(ArrayList<User> eList) {
		System.out.println(eList.size());
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement ps;
			String query = "update user set status=? where userid=?";
			ps = con.prepareStatement(query);

			for (int i = 0; i < eList.size(); i++) {
				ps.setString(1, (eList.get(i).getStatus()));
				ps.setInt(2, (eList.get(i).getUserid()));
				ps.addBatch();
			}
			ps.executeBatch();
			System.out.println("updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eList;
	}

	@Override
	public ArrayList<User> getAllUsers(User user, int i) {
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			String query = "SELECT * FROM user where usertype =?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setContactno(rs.getString("contactno"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setConfirmpassword(rs.getString("confirmpassword"));
				user.setStatus(rs.getString("status"));
				list.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int DeleteUser(int userId) {
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from user where userid=?");
			ps.setInt(1, userId);
		   ps.executeUpdate();
			System.out.println("deleted User sucessfully");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public ArrayList <User> getUserId(String username) {
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;
		User user=null;
		try {
			con = (Connection) ConnectionManager.getConnection();
			String query = "SELECT * FROM user where username =?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setContactno(rs.getString("contactno"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setConfirmpassword(rs.getString("confirmpassword"));
				user.setStatus(rs.getString("status"));
				list.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
