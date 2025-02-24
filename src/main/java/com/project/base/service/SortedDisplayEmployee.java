package com.project.base.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayForSort")
public class SortedDisplayEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortBy = req.getParameter("sortBy");
        String orderColumn = "id"; // Default sorting column

        if ("name".equalsIgnoreCase(sortBy)) {
            orderColumn = "name";
        } else if ("age".equalsIgnoreCase(sortBy)) {
            orderColumn = "age";
        } else if ("salary".equalsIgnoreCase(sortBy)) {
            orderColumn = "sal";
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstschematest", "root", "root");

            String sql = "SELECT * FROM employee ORDER BY " + orderColumn;
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");

            out.println("<html><body>");
            out.println("<h2>Employee List</h2>");

            // Add a dropdown for sorting
            out.println("<form action='displayForSort' method='get'>");
            out.println("Sort by: ");
            out.println("<select name='sortBy'>");
            out.println("<option value='id'" + ("id".equals(sortBy) ? " selected" : "") + ">ID</option>");
            out.println("<option value='name'" + ("name".equals(sortBy) ? " selected" : "") + ">Name</option>");
            out.println("<option value='age'" + ("age".equals(sortBy) ? " selected" : "") + ">Age</option>");
            out.println("<option value='salary'" + ("salary".equals(sortBy) ? " selected" : "") + ">Salary</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Sort'>");
            out.println("</form>");

            // Employee Table
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Email</th><th>Salary</th><th>Actions</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                double sal = rs.getDouble("sal");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + age + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + sal + "</td>");
                out.println("<td>");
                out.println("<form action='ProjectUpdate.html' method='get' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
                out.println("<form action='ProjectDelete.html' method='post' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<input type='submit' value='Delete'>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<form action='ProjectWelcomeForSort.html'>");
            out.println("<input type='submit' value='Back'>");
            out.println("</form>");
            out.println("</body></html>");

            rs.close();
            pstm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
