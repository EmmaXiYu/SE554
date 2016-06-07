/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.servlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.depaul.cdm.se.yuxi.ejb.CustomerRegistrationServiceLocalBean;

/**
 *
 * @author apple
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    

 
    @EJB
    private CustomerRegistrationServiceLocalBean registerService;

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String firstName = (String) request.getParameter("firstName");
            String  lastName = (String) request.getParameter("lastName");
            String gender = (String) request.getParameter("Gender");
            String age =request.getParameter("age");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
           // registerService.register(firstName, lastName, gender, age,email,password);
            request.getRequestDispatcher("index.html").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}

