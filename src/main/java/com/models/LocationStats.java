package com.models;

public class LocationStats {
	
	private String state;
	private String country;
	private int latestTotalcases,diffFromPrevDay;
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocationStats() {
		super();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalcases() {
		return latestTotalcases;
	}
	public void setLatestTotalcases(int latestTotalcases) {
		this.latestTotalcases = latestTotalcases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalcases=" + latestTotalcases
				+ "]";
	}
	
	

}
