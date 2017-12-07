package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.pac.other.ShowDB;
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;

import java.sql.*;

public class RemoveShowAction extends ActionSupport {
	public String execute() {
		int showId = -1;
		
		if (!Utils.isLoggedIn()) {
			Utils.pushError("You must be logged in to remove a show.");
			return "notloggedin";
		}
		
		try {
			showId = Integer.parseInt(ServletActionContext.getRequest().getParameter("showId"));
		}
		catch(Exception e) {
			Utils.pushError("Unable to parse show " + ServletActionContext.getRequest().getParameter("showId") + ".");
		}
		
		if(!ShowDB.removeShow(showId)) {
			Utils.pushError("Unable to delete show with id " + showId + ".");
			return "error";
		}
		
		Utils.pushMessage("Sucessfully deleted show with id " + showId + ".");			
		return "success";
	}
}
