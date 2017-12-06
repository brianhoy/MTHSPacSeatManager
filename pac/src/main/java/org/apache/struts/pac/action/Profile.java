package org.apache.struts.pac.action;
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;

import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;  
  
public class Profile {  
	public String execute(){  
		if(Utils.isLoggedIn()) {  
		    return "success";  
		} 
		else {
			Utils.pushError("Please login to see profile.");
		    return "error";  
		}  
	}  
}