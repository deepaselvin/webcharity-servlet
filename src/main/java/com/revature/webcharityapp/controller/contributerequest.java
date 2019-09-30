package com.revature.webcharityapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class contributerequest
 */
public class contributerequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long userId=Long.parseLong(request.getParameter("userId")); 
		int requestId=Integer.parseInt(request.getParameter("myselect")); 
	        double requestAmount = Double.parseDouble(request.getParameter("amount"));
	     //  String json = LoginController.contributereq1(requestAmount, requestId);
	        String json = LoginController.contributereq(userId,requestId, requestAmount );
	        PrintWriter out = response.getWriter();
	        out.write(json);
	        out.flush();
	}


}
