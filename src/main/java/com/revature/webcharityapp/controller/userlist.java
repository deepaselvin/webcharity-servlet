package com.revature.webcharityapp.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class userlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
				
				String json;
				try {
					json = AdminLoginController.listuser();
					PrintWriter out = response.getWriter();
					out.write(json);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
						
				}
	}


