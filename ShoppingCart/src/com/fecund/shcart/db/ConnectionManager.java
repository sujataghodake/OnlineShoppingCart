package com.fecund.shcart.db;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionManager {
	static Connection con = null;
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydatabase", "root", "root");
			System.out.println("Connection Established");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	// TODO - closeConnection ..
	public static Connection closeConnection() {
		  try{
		        if (con != null && !con.isClosed()) {
		        	con.close();
		        	System.out.println("connection closed");
		        }
		  }
		  catch (Exception e) {
			e.printStackTrace();
		}
		  return con;
		
	}

}