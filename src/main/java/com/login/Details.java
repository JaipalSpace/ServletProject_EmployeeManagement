package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Details")
public class Details extends HttpServlet {
	private String id;
	private double sal;
	private int age;
	
	public Details() { 
		super(); 
		}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		id=req.getParameter("id");		
		sal=Double.parseDouble(req.getParameter("sal"));
		age=Integer.parseInt(req.getParameter("age"));
		
		PrintWriter out=resp.getWriter();
		out.println("<html>");
		out.println("<body>");		
		out.println("<h3>Thank You!!</h3>");
		out.println("<form action=\"Login.html\">");
		out.println("<h4>Your details successfully saved!!</h3>");		
		out.println("<input type=\"submit\" value=Logout>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		System.out.println();
		System.out.println("Received Details: "); 
		System.out.println("ID: " + id);
		System.out.println("Salary: " + sal); 
		System.out.println("Age: " + age);
		
		//Details d=new Details(getId(),getSal(),getAge());
		//System.out.println(d);
		
		
	}
	
	public String getId() {
		return id;
	}
	public Double getSal() {
		return sal;
	}
	public int getAge() {
		return age;
	}

	/*
	@Override
	public String toString() {
		return "Details [id=" + id + ", sal=" + sal + ", age=" + age + "]";
	}
	*/
	
}
