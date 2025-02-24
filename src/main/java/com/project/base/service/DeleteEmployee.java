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

@WebServlet("/deleteEmployee")
public class DeleteEmployee extends HttpServlet {  

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstschematest", "root", "root");

            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);

            int num = pstm.executeUpdate();
            
            if (num > 0) {
                resp.getWriter().println("Employee deleted successfully.");
            } else {
                resp.getWriter().println("Failed to delete employee.");
            }
            
            //pstm.close();
            //con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
