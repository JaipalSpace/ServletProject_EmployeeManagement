package com.project.base.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.base.entity.Employee;

@WebServlet("/save")
public class SaveEmp extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstschematest", "root", "root");

            Employee e = (Employee) req.getAttribute("employee");

            String sql = "INSERT INTO employee (id, name, age, email, sal) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setInt(1, e.getId());
            pstm.setString(2, e.getName());
            pstm.setInt(3, e.getAge());
            pstm.setString(4, e.getEmail());
            pstm.setDouble(5, e.getSal());

            int num = pstm.executeUpdate();
            
            if (num > 0) {
                resp.getWriter().println("Employee data inserted successfully.");
            } else {
                resp.getWriter().println("Failed to insert employee data.");
            }
            
            
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
        
    }
}
