package com.project.base.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.base.entity.Employee;

@WebServlet("/add")
public class Add extends HttpServlet {    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        double sal = Double.parseDouble(req.getParameter("sal"));

        req.setAttribute("employee", new Employee(id, name, age, email, sal));
        System.out.println("done");
        RequestDispatcher dispatcher = req.getRequestDispatcher("save");
        dispatcher.forward(req, resp);
    }
}
