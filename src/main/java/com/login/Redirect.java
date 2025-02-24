package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/redirect")
public class Redirect extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		//String name=(String) req.getAttribute("name");
		
		
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("name")) {
					String name= cookie.getValue();
				}
			}
		}
		
		HttpSession session =req.getSession();		
		String name=(String) session.getAttribute("name");
		
		
		
		out.println("<html>");
		out.println("<body>");
		
		out.println("Hi!! I am triggered by first");
		out.println("<form action=\"Login.html\">");
		out.println("<h3>Hi!! "+name+"</h3>");		
		out.println("<input type=\"submit\" value=Logout>");
		out.println("</form>");
		out.println("<h4>Tell me more about you!!</h4>");
		out.println("<form action=\"Enter.html\">");
		out.println("<input type=\"submit\" value=Enter>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
				
	}
}
