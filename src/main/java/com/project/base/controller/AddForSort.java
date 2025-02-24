package com.project.base.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.base.entity.Employee;

@WebServlet("/addForSort")
public class AddForSort extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve data from request parameters
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        double sal = Double.parseDouble(req.getParameter("sal"));
        String sortCriteria = req.getParameter("sortCriteria");

        // Simulating a list of employees (in a real-world scenario, this could be a session or a temp list)
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(id, name, age, email, sal)); // Adding the new employee

        // Sorting based on user-selected criteria
        switch (sortCriteria.toLowerCase()) {
            case "id":
                Collections.sort(employees, Comparator.comparingInt(Employee::getId));
                break;
            case "name":
                Collections.sort(employees, Comparator.comparing(Employee::getName));
                break;
            case "age":
                Collections.sort(employees, Comparator.comparingInt(Employee::getAge));
                break;
            case "salary":
                Collections.sort(employees, Comparator.comparingDouble(Employee::getSal));
                break;
            default:
                break; // No sorting if invalid criteria is provided
        }

        // Set the sorted list in the request
        req.setAttribute("employees", employees);

        // Forward the request to SaveEmp servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("saveForSort");
        dispatcher.forward(req, resp);
    }
}
