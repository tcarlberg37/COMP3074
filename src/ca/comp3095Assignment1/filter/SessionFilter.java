/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the SessionFilter class which filters the urlPatterns 
* provided by calling the doFilter method, which checks if the user is logged in, 
* and if not, redirects them to the login page.
*********************************************************************************/
package ca.comp3095Assignment1.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// urlPatterns = urls to use the filter on (urlPatterns={"/*"} for all pages)
// has to be the exact same as web.xml?
@WebFilter(filterName="sessionFilter", urlPatterns={"/dashboard.jsp", "/futureEnhancement.jsp", "/navigation.jsp"})
public class SessionFilter implements Filter {

    public SessionFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest sessionRequest = (HttpServletRequest) request; // Have to cast this as HttpServletRequest so we can use request.getSession()
		HttpSession session = sessionRequest.getSession();
		if (session.getAttribute("user") != null) {
			chain.doFilter(request, response); // pass the request along the filter chain
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response); // bring user to login page if not logged in
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
