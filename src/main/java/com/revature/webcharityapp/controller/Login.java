package com.revature.webcharityapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get Inputs
		String donorName = request.getParameter("UserName");
		long phone_no=Long.parseLong(request.getParameter("PhoneNo")); 
		PrintWriter out = response.getWriter();
		
	    String json = LoginController.login(donorName, phone_no);
        out.write(json);
        out.flush();
	
	}

}
