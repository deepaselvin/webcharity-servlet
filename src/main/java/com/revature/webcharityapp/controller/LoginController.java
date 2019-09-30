package com.revature.webcharityapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.webcharity_core.webcharityapp.projectdept2.dao.UserDAODetails;
import com.revature.webcharity_core.webcharityapp.projectdept2.exception.DBException;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.RegUserDetails;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.adminreg;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.loginUser;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.requestlist;
import com.revature.webcharity_core.webcharityapp.service.UserService;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.AdminAct;
import com.revature.webcharity_core.webcharityapp.projectdept2.model.ContributeRequest;

public class LoginController {
	@SuppressWarnings("unused")
	public static String login(String name, long phnno) {
        
        String errorMessage = null;
        loginUser user  = null;
           
        try {
        	//user = UserService.loginProcess(name, phnno);
        	user = UserDAODetails.login( name, phnno);
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
        RegUserDetails user  = new RegUserDetails();
        try {
        	user.setName(donorName);
    		user.setPhoneno((phone_no));
    		UserService.registerProcess(user);
    		//UserDAODetails.register(user);
    		Message="sucess";
    		 }
         catch (Exception e) {
          //  e.printStackTrace();
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
	
	public static String listRequest1() throws SQLException, DBException {
        String json = null;
        List<AdminAct> list = null;
        String errorMessage = null;
        list=UserDAODetails.Requestlist();
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
	
	public static String contributereq(long userId,int reqid,double amt) {
		
		 String errorMessage = null;
	        String Message = null;
	        ContributeRequest User1  = new ContributeRequest();
	        try {
	        	User1.setUserId(userId);
				 User1.setRequestid(reqid);
				 User1.setAmountdonated(amt);
				 UserDAODetails.insert(User1);
				 UserDAODetails.update(User1);
					
	    		Message="sucess";
	    		 }
	         catch (Exception e) {
	        	 e.printStackTrace();
	            errorMessage = e.getMessage();
	        }       
	           
	        JsonObject obj = new JsonObject();
	        if (Message != null) {
	            obj.addProperty("MESSAGE:", Message);
	        } else if (errorMessage != null) {
	            obj.addProperty("errorMessage", errorMessage);
	        }
	        return obj.toString();        
	}

	public static String contributereq1(double amt,int reqid) {
		
		 String errorMessage = null;
	        String Message = null;
	        ContributeRequest User1  = new ContributeRequest();
	        try {
	        	
	        	
				 User1.setFund_needed(amt);
				 User1.setRequestid(reqid);
				 UserDAODetails.update(User1);
				
	    		Message="sucess";
	    		 }
	         catch (Exception e) {
	        	 e.printStackTrace();
	            errorMessage = e.getMessage();
	        }       
	           
	        JsonObject obj = new JsonObject();
	        if (Message != null) {
	            obj.addProperty("MESSAGE:", Message);
	        } else if (errorMessage != null) {
	            obj.addProperty("errorMessage", errorMessage);
	        }
	        return obj.toString();        
	}
	
	
	public static void main(String[] args) {
    	//Testlogin();
    	//Testregister();
    	//Testcontribute();
				try {
					listRequest1();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
    }
    
    
    
    
	private static void Testlogin() {
		System.out.println("Test Case 1: Valid User Login");
        String validUserJson = LoginController.login("deepa",8760051846l);
        System.out.println(validUserJson);
         
//        System.out.println("Test Case 2: Invalid User");
//        String invalidUserJson = LoginController.login("invalchid",565849615548l,-2);
//        System.out.println(invalidUserJson);
         }
	private static void Testregister() {
		System.out.println("Test Case 1: Valid User Register");
        String validUserJson = LoginController.Register("d",100);
        System.out.println(validUserJson);
       
        System.out.println("Test Case 2: Invalid User");
        String invalidUserJson = LoginController.Register("invalid",56548);
        System.out.println(invalidUserJson);
         
	}

	private static void Testcontribute() {
		System.out.println("Test Case 1: Valid User contribute");
        String validUserJson = LoginController.contributereq1(50.0,2);
        System.out.println(validUserJson);
         
        System.out.println("Test Case 2: Invalid User");
        String invalidUserJson = LoginController.contributereq(56548,00,-1);
        System.out.println(invalidUserJson);
         
	}

	private static void Testcontribute1() {
		System.out.println("Test Case 1: Valid User contribute");
        String validUserJson = LoginController.contributereq(1, 2, 50);
        System.out.println(validUserJson);
         
        System.out.println("Test Case 2: Invalid User");
        String invalidUserJson = LoginController.contributereq(56548,00,-1);
        System.out.println(invalidUserJson);
         
	}

	
}
