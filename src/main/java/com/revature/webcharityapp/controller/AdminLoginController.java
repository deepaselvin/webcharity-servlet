package com.revature.webcharityapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.webcharity_core.webcharityapp.projectdept2.dao.UserDAODetails;
import com.revature.webcharity_core.webcharityapp.projectdept2.exception.DBException;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.AdminAct;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.ContributeRequest;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.RegUserDetails;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.adminreg;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.requestlist;
import com.revature.webcharity_core.webcharityapp.service.AdminService;
import com.revature.webcharity_core.webcharityapp.service.UserService;

public class AdminLoginController {
	public static String login(String enter_name, long enter_phn_no) {
        
        String errorMessage = null;
       
        adminreg user  = null;
        try {
        	user =AdminService.loginProcess(enter_name, enter_phn_no);
        	//user = UserDAODetails.adminLogin( enter_name, enter_phn_no);
             if(user == null) {
            	 throw new Exception("invalid");
             }
        } catch (Exception e) {
            //e.printStackTrace();
            errorMessage = e.getMessage();
        }       
         
        // Prepare JSON Object
        String json = null;
        Gson gson = new Gson();
        if( user != null) {
             json = gson.toJson(user);
        }
        else if ( user == null ) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
            json = obj.toString();
        }
       
		return json;         
    }
	public static String Register(String donorName, long phone_no) {
        
        String errorMessage = null;
        String Message = null;
        adminreg user = new adminreg();
        user  = null;
        try {
        	user.setAdminName(donorName);
    		user.setPhnoneno((phone_no));
    		AdminService.registerProcess(user);
    		//UserDAODetails.adminRegister(user);
    		Message="sucess";
    		 }
         catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }       
         
        // Prepare JSON Object
       String json =null;
        JsonObject obj = new JsonObject();
        if (Message != null) {
            obj.addProperty("MESSAGE:", Message);
        } 
        else if (errorMessage != null) {
       
        	obj.addProperty("errorMessage", errorMessage);
            json=obj.toString();
        }
        return json;        
        
	}
    

	public static String Addrequest (String request_type,double fund_needed) {
        
        String errorMessage = null;
        String Message = null;
        AdminAct user = new AdminAct();
        
        try {
        	user.setRequest_type(request_type);
        	user.setFund_needed(fund_needed);
        
    	UserDAODetails.adminRequest(user);
    		Message="sucess";
    		 }
         catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }       
         
        // Prepare JSON Object
       
        JsonObject obj = new JsonObject();
        if (Message != null) {
            obj.addProperty("MESSAGE:", Message);
        } else if (errorMessage != null) {
            obj.addProperty("errorMessage", errorMessage);
        }
        return obj.toString();        
        
         
    }

	public static String listuser1() throws SQLException {
		String json = null;
		String errorMessage = null;
		List<ContributeRequest> list = null;
		//list =UserDAODetails.userlist();
      
        if (list != null) {
            Gson gson = new Gson();
            json = gson.toJson(list);
        }
        if (errorMessage != null) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
        }
        System.out.println("list"+json);
        return json;
    }
	public static String listuser() throws SQLException, DBException {
        String json = null;
        List<ContributeRequest> list = null;
        String errorMessage = null;
        list=UserDAODetails.userRequest();
       // System.out.println(list);
    
       
       
        if (list != null) {
            Gson gson = new Gson();
            json = gson.toJson(list);
        }
        if (errorMessage != null) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
        }
        System.out.println("list"+json);
        return json;
    }
	 
	
    public static void main(String[] args) throws DBException {
         
    	//TestLogin();
    	//Testregister();
   	TestAddrequest() ;
    //	listuser();
	
    }
    private static void TestLogin() {
    	 System.out.println("Test Case 1: Valid User");
         String validUserJson = AdminLoginController.login("pradeepa",9488544846l);
         System.out.println(validUserJson);
          
         System.out.println("Test Case 2: Invalid User");
         String invalidUserJson = AdminLoginController.login("invalid",56548);
         System.out.println(invalidUserJson);
    }
    private static void Testregister() {
		System.out.println("Test Case 1: Valid User Register");
        String validUserJson = AdminLoginController.Register("gayu",1236547898l);
        System.out.println(validUserJson);
         
        System.out.println("Test Case 2: Invalid User");
        String invalidUserJson = AdminLoginController.Register("invalid",56548);
        System.out.println(invalidUserJson);
         
	}
    private static void TestAddrequest() {
		System.out.println("Test Case 1: Valid User Register");
        String validUserJson = AdminLoginController.Addrequest("cleaning",9000.0);
        System.out.println(validUserJson);
         
        
	}


}
