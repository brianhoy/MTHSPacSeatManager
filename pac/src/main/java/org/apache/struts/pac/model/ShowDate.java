package org.apache.struts.pac.model;

// Represents a row in the ShowDates table
public class ShowDate {
	private int showDateId;
	private int showId;
	private java.util.Date date;
	
	public void setShowDateId(int showDateId) {
		this.showDateId = showDateId;
	}
	public int getShowDateId() {
		return this.showDateId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getShowId() {
		return showId;
	}
	
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public java.util.Date getDate() {
		return this.date;
	}
}
