package org.apache.struts.pac.action;

import org.apache.struts.pac.model.ShowStore;
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;  


public class EditShowAction extends ActionSupport {
	int showid;
	ShowStore currentlyEditedShow;
	
    public String execute() {    	    	
		ValueStack stack = ActionContext.getContext().getValueStack();
		Object value = stack.findValue("showid");

    	if(Utils.isLoggedIn() && value != null) {
    		showid = Integer.parseInt((String)value);
    		
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! PAC DEBUG: showid: " + showid);
    		
    		return "success";
    	}
    	
		Map<String, Object> context = new HashMap<String, Object>();

		context.put("errorMsg", new String("You must be logged in to edit a show."));
		stack.push(context);
    	
        return "error";
    }
    
    public void finishEdit() {
    	
    }
}
