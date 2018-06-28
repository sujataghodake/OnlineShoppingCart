package com.fecund.shcart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fecund.shcart.db.UserDataImpl;
import com.fecund.shcart.db.UserData_IF;
import com.fecund.shcart.entity.Category;
import com.fecund.shcart.entity.Product;
import com.fecund.shcart.entity.User;

public class UserManagementService {
	public int addUser(User user) {
		UserData_IF userdata = new UserDataImpl();
		int i=userdata.addUser(user);
		return i;
	}

	public String verifyUser(User user) {
		UserData_IF userdata = new UserDataImpl();
		String userValidate = userdata.verifyUser(user);
		return userValidate;
	}
	public User getUser(User user) {
		UserData_IF userdata = new UserDataImpl();
		user = userdata.getUser(user);
		return user;
	}

	public int updateUser(User user) {
		UserData_IF userdata = new UserDataImpl();
		userdata.updateUser(user);
		return 1;
	}

	public ArrayList<User> getWaitingUsers() {
		ArrayList<User> list;
		UserData_IF userdata = new UserDataImpl();
		list = userdata.getWaitingUsers();
		return list;

	}
	public ArrayList<User> updateWaitingUsers(String[] userId, String[] userStatus) {
		UserData_IF userdata = new UserDataImpl();
		ArrayList<User> list=userdata.updateWaitingUsers(userId,userStatus);
		return list;

	}
	public ArrayList<User> updateWaitingUsers(ArrayList<User> eList) {
		UserData_IF userdata = new UserDataImpl();
		ArrayList<User> list=userdata.updateWaitingUsers(eList);
		return list;

	}

	public List<User> getAllUsers(User user, int i) {
		List list;
		UserData_IF userdata = new UserDataImpl();
		list = userdata.getAllUsers(user,i);
		return list;
	}


	
	

	/*public void updateUsers(ArrayList<User> eList) {
		UserData_IF userdata = new UserDataImpl();
		userdata.updateUsers(eList);

	}
	

*/

	/*public void updateUsers(ArrayList<User> eList, String result) {
		UserData_IF userdata = new UserDataImpl();
		userdata.updateUsers(eList,result);

	}*/


	

	

}
