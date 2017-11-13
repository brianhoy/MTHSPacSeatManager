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

public class RequestShowsAction extends ActionSupport {
	ArrayList<ShowStore> shows;

	int helloCount = 5;

	public String execute() {
		shows = new ArrayList<ShowStore>();
		shows = Utils.getShows();

		String addon = "";
		if(localIsLoggedIn() != Utils.isLoggedIn()) {
			Utils.pushError("Mismatch.");
			System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE mismatch");
		}
		else {
			Utils.pushMessage("No mismatch.");
			System.out.println("FEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE no mismatch");
		}
		
		if (Utils.isLoggedIn()) {
			addon += "_loggedin";
		}
		if (shows == null) {
			Utils.pushError("Unable to load shows.");
			return "error" + addon;
		} else {
			return "success" + addon;
		}
	}

	public ArrayList<ShowStore> getShows() {
		return shows;
	}
	
	public boolean localIsLoggedIn() {
		HttpServletRequest request = ServletActionContext.getRequest();  
		HttpSession session = request.getSession();  
		  
		String s = (String)session.getAttribute("login");  
		return (s != null && !s.equals(""));
	}
}
