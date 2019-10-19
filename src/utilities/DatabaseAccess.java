/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the DatabaseAccess class which we use to access the database as well
* as the methods for finding if a user exists and adding a new user to the database.
* This class is primarily invoked by the Login and Register servlets.
*********************************************************************************/
package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import ca.comp3095Assignment1.beans.User;


public class DatabaseAccess {
	  private static Connection connect = null;
	  private static String username = "admin@isp.net";
	  private static String password = "P@ssword1";
	  private static String database = "COMP3095";
	  
	  public static Connection connectDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/"+database+"?"
		              + "user="+username+"&password="+password);
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
	  
	  public static boolean userExists(HttpServletRequest request) { 
		  try {
			  Connection connection = connectDataBase();
			  String email = request.getParameter("email");
			  PreparedStatement statement = connection.prepareStatement("select * from Users where email='" + email + "';");
			  ResultSet results = statement.executeQuery();
			  return results.next(); // true if user exists in database already, false if not
		  } catch (Exception e) {
			  request.setAttribute("message", "An exception occured.");
			  return false;
		  }
	  }
	  
	  public static User getUser(HttpServletRequest request) {
		  try {
			  Connection connection = connectDataBase();
			  String email = request.getParameter("email");
			  String password = request.getParameter("password");
			  PreparedStatement statement = connection.prepareStatement("select * from Users where email='" + email + "'"
			  		+ "and password='" + password + "';");
			  ResultSet results = statement.executeQuery();
			  if (results.next()) { // if there is a result
				  String firstName = results.getString("firstname");
				  String lastName = results.getString("lastname");
				  String username = results.getString("email");
				  String role = results.getString("role");
				  User user = new User(firstName, lastName, username, "", "", role);
				  
				  return user;
			  }
			  else {
				  return null;
			  }
		  } catch (Exception e) {
			  request.setAttribute("message", "An exception occured.");
			  return null;
		  }
	  }
	  
	  public static void addUser(HttpServletRequest request) {
		  try {
			  Connection connection = connectDataBase();
			  String firstName = request.getParameter("firstName");
			  String lastName = request.getParameter("lastName");
			  String email = request.getParameter("email");
			  String address = request.getParameter("address");
			  String password = request.getParameter("password");
			  String role = "client"; // admin users will get directly injected into database, only need to create clients
			  PreparedStatement statement = connection.prepareStatement("insert into Users (firstname, lastname, email, address, role, password)"
			  		+ "values ('" + firstName + "', '" + lastName + "', '" + email + "', '" + address + "', '" + "', '" + role + "', '" + password + "');" );
			  statement.executeUpdate();
			  request.setAttribute("message", "User successfully registered in the system.");
		  } catch (Exception e) {
			  request.setAttribute("message", "An exception occured.");
		  }
	  }
}