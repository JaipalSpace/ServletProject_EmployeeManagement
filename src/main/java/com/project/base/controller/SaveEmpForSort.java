package com.project.base.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.base.entity.Employee;

@WebServlet("/saveForSort")
public class SaveEmpForSort extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstschematest", "root", "root");

            // Retrieve the sorted list of employees from the request
            @SuppressWarnings("unchecked")
            List<Employee> employees = (List<Employee>) req.getAttribute("employees");

            // Prepare SQL query
            String sql = "INSERT INTO employee (id, name, age, email, sal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);

            // Insert each employee into the database
            for (Employee e : employees) {
                pstm.setInt(1, e.getId());
                pstm.setString(2, e.getName());
                pstm.setInt(3, e.getAge());
                pstm.setString(4, e.getEmail());
                pstm.setDouble(5, e.getSal());
                pstm.addBatch(); // Add to batch
            }

            // Execute batch insertion
            int[] results = pstm.executeBatch();

            // Respond to the user with success message
            resp.getWriter().println(results.length + " employees inserted successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
