package org.apache.struts.pac.action;

import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class Login implements SessionAware {  
	private String username, userpass;  
	SessionMap<String, String> sessionmap;  
	  
	public String getUsername() {  
	    return username;  
	}  
	  
	public void setUsername(String username) {  
	    this.username = username;  
	}  
	  
	public String getUserpass() {  
	    return userpass;  
	}  
	  
	public void setUserpass(String userpass) {  
	    this.userpass = userpass;  
	}  
	  
	public String execute(){
		//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA username: " + username + ", userpass: " + userpass);
		
	    if(username.equals("defuser") && userpass.equals("defpass")) {
	        return "success";
	    }  
	    else {  
	        return "error";  
	    }  
	}  
	  
	public void setSession(Map map) {  
	    sessionmap = (SessionMap<String,String>)map;  
	    sessionmap.put("login","true");  
	}  
	  
	public String logout(){  
	    sessionmap.invalidate();  
	    return "success";  
	}
}  
