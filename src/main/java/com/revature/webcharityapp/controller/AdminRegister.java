package com.revature.webcharityapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.webcharity_core.webcharityapp.projectdept2.dao.UserDAODetails;
import com.revature.webcharity_core.webcharityapp.projectdept2.exception.DBException;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.adminreg;

/**
 * Servlet implementation class Register
 */
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//Get Inputs
        String adminName = request.getParameter("UserName"); 
        String phone_no=request.getParameter("PhoneNo"); 
        PrintWriter out = response.getWriter();
        out.println("UserName:" + adminName);
        out.println("PhoneNo:" + phone_no);
        
        
        
        //to call dao
        adminreg user=new adminreg();
	    user.setAdminName(adminName);
		user.setPhnoneno(Long.parseLong(phone_no));
		
		try {
			UserDAODetails.adminRegister(user);
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (DBException e) {
	
			e.printStackTrace();
		}
	  
		// Convert list to json
        Gson gson = new Gson();
        String json = gson.toJson(user);


		out.print(json);
		out.flush();
	
	
	}

}
