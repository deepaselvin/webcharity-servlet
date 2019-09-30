package com.revature.webcharityapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   		String RequestName = request.getParameter("Fundname"); 
   	 Double fund=Double.parseDouble(request.getParameter("Fund")); 
        PrintWriter out = response.getWriter();
    
	
		// Convert list to json
        
        String json = AdminLoginController.Addrequest(RequestName,fund);
        out.write(json);
        out.flush();
	
   	}

}
