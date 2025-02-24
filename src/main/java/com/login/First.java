package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/First")
public class First extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
					
		System.out.println("Hi!! I am First.java triggered by login.html");
		
		String user=req.getParameter("name");		
		String password=req.getParameter("password");
		
		HttpSession session =req.getSession();
		
		session.setAttribute("name", user);
		session.setAttribute("password",password);
		
		Cookie c=new Cookie("name", user);
		resp.addCookie(c);		
		
		if(!user.equals("jaipal")||user==null) {
			RequestDispatcher dispatcher=req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
			
			return;
		}
		
		
		
		//PrintWriter out = resp.getWriter();
		//out.println("<html>");
		//out.println("<body>");
		
		//out.println("<h3>Hi!! I am triggered by first</h3>");
		//out.println("<form action=\"redirect\">");
		//out.println("<h3>Your details matched</h3>");
		//out.println("<input type=\"submit\" value=OK>");
		
		//RequestDispatcher dispatcher=req.getRequestDispatcher("redirect");
		
		//dispatcher.forward(req, resp);
		
		resp.sendRedirect("redirect");
		
	}
}
