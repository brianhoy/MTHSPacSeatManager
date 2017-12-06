package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
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
	private int showid = -1;
	private Show currentlyEditedShow;
	private Show show; // used in the form

	public String execute() {
		int showid = Integer.parseInt(ServletActionContext.getRequest().getParameter("showid"));

		if (Utils.isLoggedIn() && showid != -1) {
			currentlyEditedShow = Utils.getShow(showid);

			if (currentlyEditedShow == null) {
				Utils.pushError("Unable to retrieve show " + showid + " for editing.");
				return "error";
			}

			return "success";
		}

		Utils.pushError("You must be logged in to edit a show.");
		
		return "notloggedin";
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Show getCurrentlyEditedShow() {
		return currentlyEditedShow;
	}

	public String finishEdit() {
		if (Utils.isLoggedIn()) {
			boolean result = Utils.editShow(show);

			if (!result) {
				Utils.pushError("Failed to edit show " + show.getId() + " with title " + show.getName()
						+ ". Check the console.");

				return "error";
			}
			Utils.pushMessage("Successfully updated show \"" + show.getName() + "\".");

			return "success";
		}
		Utils.pushError("You must be logged in to submit a show edit.");

		return "notloggedin";
	}
}
