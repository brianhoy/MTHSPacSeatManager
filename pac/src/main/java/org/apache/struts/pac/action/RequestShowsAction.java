package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.other.ShowDB;
import org.apache.struts.pac.other.Utils;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

public class RequestShowsAction extends ActionSupport {
	ArrayList<Show> shows;

	public String execute() {
		shows = new ArrayList<Show>();
		shows = ShowDB.getShows();

		String addon = "";
		
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

	public ArrayList<Show> getShows() {
		return shows;
	}
}
