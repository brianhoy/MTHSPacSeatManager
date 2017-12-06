package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;

public class AddShowAction extends ActionSupport {
	private Show show;
	int status = 0;

	public String execute() {			
		if (!Utils.isLoggedIn()) {
			Utils.pushError("You must be logged in to add a show.");
			return "notloggedin";
		}

		Utils.pushError("dates string: " + show.getDates());
		
		ArrayList<java.util.Date> parsedDates = Utils.parseDates(show.getDates());
		
		Utils.pushMessage("Parsed dates string: " + parsedDates.toString());
		
		return "error";
		
		/*String result = Utils.addShow(show);

		if (result.equals("success")) {
			Utils.pushMessage("Successfully added show \"" + show.getName() + "\".");

			return "success";
		} else {
			Utils.pushError("Unable to add show. Error: " + result);
			return "error";
		}*/
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
	
}