/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the Register servlet that is called when a new user tries
* to register an account on registration.jsp. The form validation happens in the doPost method.
*********************************************************************************/
package ca.comp3095Assignment1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// to get these imports, had to download javax.mail.jar and copy it to tomcat\lib and WEB-INF\lib
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.comp3095Assignment1.beans.User;
import ca.comp3095Assignment1.beans.UserList;
import utilities.DatabaseAccess;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmPassword");
		
		// Settings these as attributes will keep the form fields filled on a failed registration redirect back to the same page
    	request.setAttribute("firstName", firstName);
    	request.setAttribute("lastName", firstName);
    	request.setAttribute("email", email);
    	request.setAttribute("address", address);  	
		
		/* Validation Rules:
		 *    First and Last names contain only alphabetical characters
		 *    Email must be valid email structure (taken care of by required type=email input tag already)
		 *    User's username == email (don't need to check for this either since email always == username)
		 *    Passwords must be >= characters and <= 12, contain >= 1 uppercase character and >= 1 special character (!@#$%&*?)
		 */
    	
    	if(!UserList.userExists(email)) { // first check that user does not exist already
			// [a-zA-z]+ is a regex that checks that first and last name are only alphabetical
			if (firstName.matches("[a-zA-z]+") && lastName.matches("[a-zA-z]+")) { 
				if (password.length() >= 6 && password.length() <= 12 && password.equals(confirm)){ //&& password.matches("(?=.*[A-Z])(?=.*[!@#$%&*?])")) {
					// regex (?=.*[characters]) == one or more of [characters]
					UserList.addUser(firstName, lastName, email, address, password);
					
					// send an email to the new user once registered
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress("admin@isp.net", "COMP3095 Admin"));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, firstName + " " + lastName));
						message.setSubject("You have registered on the COMP3095 Assignment 1 Page!");
						message.setText("Dear " + firstName + " " + lastName + ",\n You are receiving this email because you have successfully"
								+ " registered an account with your email " + email
								+ ". Please click <a href='http://localhost:8081/comp3095Assignment1/'>here</a> to login."
								+ "Best Regards,\nThe Brogrammers");
						Transport.send(message);
					} catch (Exception e) {
						log("An exception occured. Message: " + e.getMessage());
					}
					
					response.setContentType("text/html");
			        PrintWriter out = response.getWriter();
			        // create a popup alert using javascript once registered and email sent
					out.println("<script>alert('User successfully registered! An email has been sent');");
					out.println("location='login.jsp';</script>");
				} else {
					request.setAttribute("message", "Password must be between 6 and 12 characters and contain one or more"
							+ " capital letters and one or more special characters.");
					request.getRequestDispatcher("registration.jsp").include(request, response);
				}
			} else {
				request.setAttribute("message", "First and Last Names must only contain letters.");
				request.getRequestDispatcher("registration.jsp").include(request, response);
			}
		} else {
			request.setAttribute("message", "That username is taken. Please use another email address.");
			request.getRequestDispatcher("registration.jsp").include(request, response);
		}
    	
    	/*
		if (!DatabaseAccess.userExists(request)) { // first check that user does not exist already
			// [a-zA-z]+ is a regex that checks that first and last name are only alphabetical
			if (firstName.matches("[a-zA-z]+") && lastName.matches("[a-zA-z]+")) { 
				if (password.length() >= 6 && password.length() <= 12 && password.equals(confirm)){ //&& password.matches("(?=.*[A-Z])(?=.*[!@#$%&*?])")) {
					// regex (?=.*[characters]) == one or more of [characters]
					DatabaseAccess.addUser(request);
					
					// send an email to the new user once registered
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress("admin@isp.net", "COMP3095 Admin"));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, firstName + " " + lastName));
						message.setSubject("You have registered on the COMP3095 Assignment 1 Page!");
						message.setText("Dear " + firstName + " " + lastName + ",\n You are receiving this email because you have successfully"
								+ " registered an account with your email " + email
								+ ". Please click <a href='http://localhost:8081/comp3095Assignment1/'>here</a> to login."
								+ "Best Regards,\nThe Brogrammers");
						Transport.send(message);
					} catch (Exception e) {
						log("An exception occured. Message: " + e.getMessage());
					}
					
					response.setContentType("text/html");
			        PrintWriter out = response.getWriter();
			        // create a popup alert using javascript once registered and email sent
					out.println("<script>alert('User successfully registered! An email has been sent');");
					out.println("location='login.jsp';</script>");
				} else {
					request.setAttribute("message", "Password must be between 6 and 12 characters and contain one or more"
							+ " capital letters and one or more special characters.");
					request.getRequestDispatcher("registration.jsp").include(request, response);
				}
			} else {
				request.setAttribute("message", "First and Last Names must only contain letters.");
				request.getRequestDispatcher("registration.jsp").include(request, response);
			}
		} else {
			request.setAttribute("message", "That username is taken. Please use another email address.");
			request.getRequestDispatcher("registration.jsp").include(request, response);
		}
		*/
	}

}
