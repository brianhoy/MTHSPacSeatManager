package org.apache.struts.pac.action;

import org.apache.struts.pac.model.ShowStore;
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
	public ShowStore currentlyEditedShow;

	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();

		int showid = Integer.parseInt(ServletActionContext.getRequest().getParameter("showid"));

		if (Utils.isLoggedIn() && showid != -1) {
			boolean result = Utils.removeShow(showid);

			if(result == false) {
				context.put("errorMsg", new String("Unable to delete show with id " + showid + "."));
				stack.push(context);

				return "error";
			}
			
			context.put("msg", new String("Sucessfully deleted show with id " + showid + "."));
			stack.push(context);
			
			return "success";
		}


		context.put("errorMsg", new String("You must be logged in to edit a show. (showid = " + showid + ")"));
		stack.push(context);

		return "error";
	}
}
