package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.model.ShowForm;
import org.apache.struts.pac.other.ShowDB;
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EditShowAction extends ActionSupport {
	private int eee = 1;
	private Show currentShow;
	private ShowForm showForm; // used in the form
	public Show getCurrentShow() {
		return currentShow;
	}
	public void setCurrentShow(Show currentShow) {
		this.currentShow = currentShow;
	}
	
	public String execute() {
		if (!Utils.isLoggedIn()) {
			return "notloggedin";
		}
		
		int showId = -1;
		try {
			showId = Integer.parseInt(ServletActionContext.getRequest().getParameter("showId"));
		}
		catch(Exception e) {
			Utils.pushError("Unable to parse show ID " + ServletActionContext.getRequest().getParameter("showId"));
			return "error";
		}
		
		currentShow = ShowDB.getShow(showId);

		if (currentShow == null) {
			Utils.pushError("Unable to retrieve show " + showId + " for editing.");
			return "error";
		}

		return "success";
	}

	public String finishEdit() {
		if (Utils.isLoggedIn()) {
			boolean result = ShowDB.editShow(currentShow);

			if (!result) {
				Utils.pushError("Failed to edit show " + currentShow.getShowId() + " with title " + currentShow.getTitle()
						+ ". Check the console.");

				return "error";
			}
			Utils.pushMessage("Successfully updated show \"" + currentShow.getTitle() + "\".");

			return "success";
		}
		Utils.pushError("You must be logged in to submit a show edit.");

		return "notloggedin";
	}

	public ShowForm getShowForm() {
		return showForm;
	}

	public void setShowForm(ShowForm showForm) {
		this.showForm = showForm;
	}
	public int getEee() {
		return eee;
	}
	public void setEee(int eee) {
		this.eee = eee;
	}
}
