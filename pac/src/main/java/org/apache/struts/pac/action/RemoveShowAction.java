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
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;

import java.sql.*;

public class RemoveShowAction extends ActionSupport {
	private int showid = -1;
	public Show currentlyEditedShow;

	public String execute() {
		int showid = Integer.parseInt(ServletActionContext.getRequest().getParameter("showid"));

		if (!Utils.isLoggedIn()) {
			Utils.pushError("You must be logged in to edit a show.");
			return "notloggedin";
		}
		if(showid != -1) {
			Utils.pushError("Unable to delete show with id " + showid + ".");
			return "error";			
		}
		if(!Utils.removeShow(showid)) {
			Utils.pushError("Unable to delete show with id " + showid + ".");
			return "error";
		}
		
		Utils.pushMessage("Sucessfully deleted show with id " + showid + ".");			
		return "success";
	}
}
