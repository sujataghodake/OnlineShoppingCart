/**
 * 
 */
package com.fecund.shcart.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.User;

/**
 * @author Sujata
 * 
 */
public interface UserData_IF {

	public int addUser(User user);
    public String verifyUser(User user);
   public User getUser(User user);

	public User updateUser(User user);

	public ArrayList<User> getWaitingUsers();
	public ArrayList<User>updateWaitingUsers(String[] userId,String[] userStatus);
	public List getAllUsers(User user, int i);
	public ArrayList<User>updateWaitingUsers(ArrayList<User> eList);
	public int DeleteUser(int userId);
	

}
