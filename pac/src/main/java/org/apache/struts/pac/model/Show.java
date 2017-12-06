package org.apache.struts.pac.model;

// Represents a row in the Shows table
public class Show {
	private int showId;
	private String title;
	private String description;
	private String imagePath;
	private java.util.Date firstDate;
	private java.util.Date lastDate;
	private java.time.Duration duration;
	
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public java.util.Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(java.util.Date firstDate) {
		this.firstDate = firstDate;
	}
	public java.util.Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(java.util.Date lastDate) {
		this.lastDate = lastDate;
	}
	public java.time.Duration getDuration() {
		return duration;
	}
	public void setDuration(java.time.Duration duration) {
		this.duration = duration;
	}
}
