package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.model.ShowDate;
import org.apache.struts.pac.model.ShowForm;
import org.apache.struts.pac.other.ShowDB;
import org.apache.struts.pac.other.Utils;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AddShowAction extends ActionSupport {
	private ShowForm showForm;
	
	int status = 0;

	public String execute() {
		try {
			if (!Utils.isLoggedIn()) {
				Utils.pushError("You must be logged in to add a show.");
				return "notloggedin";
			}
			
			int duration = -1;
			try {
				int pHours = Integer.parseInt(showForm.getHours());
				int pMinutes = 0;
				try {
					pMinutes = Integer.parseInt(showForm.getMinutes());
				}
				catch(Exception e) {}
				duration = pHours * 60 + pMinutes;
			}
			catch(Exception e) { }
			
			ArrayList<java.util.Date> parsedDates = Utils.parseDates(showForm.getDates());
			if(parsedDates == null) {
				return "error";
			}
			java.util.Date firstDate = parsedDates.get(0);
			
			//System.out.println("ParsedDates size: " + parsedDates.size());
			Utils.pushMessage("ParsedDates size: " + parsedDates.size() + ", stringified: " + parsedDates.toString());
			
			java.util.Date lastDate = parsedDates.get(parsedDates.size() - 1);

			Show show = new Show();
			show.setTitle(showForm.getTitle());
			show.setDescription(showForm.getDescription());
			show.setFirstDate(firstDate);
			if(firstDate != lastDate) { show.setLastDate(lastDate); }
			show.setImagePath(showForm.getImagePath());
			if(duration != -1) { show.setDuration(java.time.Duration.ofMinutes(duration)); }
			
			Utils.pushMessage("Parsed dates string: " + parsedDates.toString());
			
			ArrayList<ShowDate> showDates = new ArrayList<ShowDate>();
			for(java.util.Date d : parsedDates) {
				ShowDate showDate = new ShowDate();
				showDate.setDate(d);
				showDates.add(showDate);
			}
			String result = ShowDB.addShow(show, showDates);
			if(result != null && result.equals("success")) {
				return "success";
			}
		}
		catch(Exception e) {
			//Utils.pushError("Error adding show: " + e.getMessage());
		}
		
		return "error";
	}

	public ShowForm getShowForm() {
		return showForm;
	}

	public void setShowForm(ShowForm showForm) {
		this.showForm = showForm;
	}	
}