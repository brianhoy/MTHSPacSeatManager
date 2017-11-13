package org.apache.struts.pac.action;

import org.apache.struts.pac.model.ShowStore;
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
	private ShowStore show;
	int status = 0;

	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
				
		if (!Utils.isLoggedIn()) {
			context.put("errorMsg", new String("You must be logged in to add a show."));
			stack.push(context);
			return "notloggedin";
		}

		boolean success = Utils.addShow(show);

		if (success) {
			Utils.pushMessage("Successfully added show \"" + show.getName() + "\".");

			return "success";
		} else {
			Utils.pushError("Unable to add show. Ensure the SQL database is running and that it is configured correctly.");
			return "error";
		}
	}

	public ShowStore getShow() {
		return show;
	}

	public void setShow(ShowStore show) {
		this.show = show;
	}
	
}