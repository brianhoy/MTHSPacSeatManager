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
	public int showid = -1;
	public ShowStore currentlyEditedShow;

	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		// Object value = stack.findValue("showid");

		int showid = Integer.parseInt(ServletActionContext.getRequest().getParameter("showid"));

		if (Utils.isLoggedIn() && showid != -1) {
			currentlyEditedShow = Utils.getShow(showid);

			return "success";
		}

		Map<String, Object> context = new HashMap<String, Object>();

		context.put("errorMsg", new String("You must be logged in to edit a show. (showid = " + showid + ")"));
		stack.push(context);

		return "error";
	}

	public void finishEdit() {

	}

}
