/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the Authentication servlet that is called from the Login
* servlet to authenticate a user when they try to log in.
*********************************************************************************/
package ca.comp3095Assignment1.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.comp3095Assignment1.beans.User;
import ca.comp3095Assignment1.beans.UserList;
import utilities.DatabaseAccess;

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Authentication() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User user = DatabaseAccess.getUser(request);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = UserList.authenticate(email, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(-1); // session ends when browser is closed
	        log("User session: " + session.getAttribute("user").toString());
	        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
		request.setAttribute("message", "Invalid Credentials Provided");
		request.getRequestDispatcher("login.jsp").include(request, response);
	}

}
