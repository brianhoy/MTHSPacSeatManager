package org.apache.struts.pac.action;

import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;

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
		ValueStack stack = ActionContext.getContext().getValueStack();

	    if(username.equals("defuser") && userpass.equals("defpass")) {
		    sessionmap.put("login","true");  
	        return "success";
	    }  
	    else {  
	    	Map<String, Object> context = new HashMap<String, Object>();
	    	context.put("errorMsg", new String("Invalid user name or password. Try again.")); 
	    	stack.push(context);
	    	
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
