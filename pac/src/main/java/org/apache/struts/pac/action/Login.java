package org.apache.struts.pac.action;

import java.util.Map;

import org.apache.struts.pac.other.Utils;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;

public class Login implements SessionAware {  
	private String username, password;  
	SessionMap<String, String> sessionmap;  
	  
	public String getUsername() {  
	    return username;  
	}  
	  
	public void setUsername(String username) {  
	    this.username = username;  
	}  
	  
	public String getUserpass() {  
	    return password;  
	}  
	  
	public void setPassword(String password) {  
	    this.password = password;  
	}  
	  
	public String execute(){
	    if(username.equals("defuser") && password.equals("defpass")) {
		    sessionmap.put("login","true");  
	        return "success";
	    }
	    else {  
	    	Utils.pushError("Invalid user name or password. Try again.");
	        return "error";  
	    }  
	}  
	  
	public void setSession(Map map) {  
	    sessionmap = (SessionMap<String,String>)map;  
	}  
	  
	public String logout(){  
	    sessionmap.invalidate();  
	    return "success";  
	}
}  
