package com.project.base.service;

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

@WebServlet("/updateEmployee")
public class UpdateEmployee extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        double sal = Double.parseDouble(req.getParameter("sal"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstschematest", "root", "root");

            String sql = "UPDATE employee SET name=?, age=?, email=?, sal=? WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, name);
            pstm.setInt(2, age);
            pstm.setString(3, email);
            pstm.setDouble(4, sal);
            pstm.setInt(5, id);
            
            int num = pstm.executeUpdate();
            
            if (num > 0) {
                resp.getWriter().println("Employee updated successfully.");
            } else {
                resp.getWriter().println("Failed to update employee, Check the Id!");
            }
            
            //pstm.close();
            //con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}			//<input type="hidden" id="id" name="id" value="<%= request.getParameter("id")%>">
