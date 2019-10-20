package ca.comp3095Assignment1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

//non-database super simple terrible practice way of doing authentication
public class UserList {
	public static List<User> users = new ArrayList<User>();

	public UserList() {
		User adminUser = new User("Admin", "", "admin@isp.net", "", "P@ssword1", "admin");
    	users.add(adminUser);
	}
	
	public static User authenticate(String email, String password) {
		for (User u : users) { 
			// log(u.toString());
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
