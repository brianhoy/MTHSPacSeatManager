package org.apache.struts.pac.model;

public class ShowSeat {
	private int seatId;
	private int showDateId;
	private String name; // e.g. AA21
	private String state;
	
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getShowDateId() {
		return showDateId;
	}
	public void setShowDateId(int showDateId) {
		this.showDateId = showDateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
