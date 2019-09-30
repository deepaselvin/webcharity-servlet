package com.revature.webcharityapp.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get Inputs
		String donorName = request.getParameter("UserName");
		long phone_no=Long.parseLong(request.getParameter("PhoneNo"));
		//long phone_no=Long.parseLong(localStorage.getItem("PhoneNo"));
		
		
		PrintWriter out = response.getWriter();
		System.out.println("UserName:" + donorName);
		System.out.println("PhoneNo:" + phone_no);
	    String json = AdminLoginController.login(donorName, phone_no);


		out.print(json);
		out.flush();

	

	}

}
