/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the UserList class which we are using an ArrayList to hold registered users
* in place of the database while the database connectivity is not working due to a seemingly unsolvable UnsatisfiedLinkError
*********************************************************************************/
package ca.comp3095Assignment1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

//non-database super simple terrible practice way of doing authentication
public class UserList {
	public static User adminUser = new User("Admin", "", "admin@isp.net", "", "P@ssword1", "admin");
	public static List<User> users = new ArrayList<User>(){{add(adminUser);}};

	public UserList() {
		User adminUser = new User("Admin", "", "admin@isp.net", "", "P@ssword1", "admin");
    	users.add(adminUser);
	}
	
	public static User authenticate(String email, String password) {
		for (User u : users) { 
			// log(u.toString()); // why does log work in some files and not others? log only allowed in servlets?
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public static boolean userExists(String email) {
		for (User u : users) {
			if (u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public static User addUser(String firstName, String lastName, String email, String address, String password) {
		User user = new User(firstName, lastName, email, address, password, "client");
		users.add(user);
		return user;
	}
}
