package com.springproject.covid.CovidProject.model;

public class Location {

	private String state;
	private String country;
	private int latestcase;
	private int varCases;
	public Location() {
		
	}
	
	public Location(String state, String country, int latestcase, int varCases) {
		super();
		this.state = state;
		this.country = country;
		this.latestcase = latestcase;
		this.varCases = varCases;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestcase() {
		return latestcase;
	}
	public void setLatestcase(int latestcase) {
		this.latestcase = latestcase;
	}
	
	public int getVarCases() {
		return varCases;
	}
	public void setVarCases(int varCases) {
		this.varCases = varCases;
	}

	@Override
	public String toString() {
		return "Location [state=" + state + ", country=" + country + ", latestcase=" + latestcase + ", varCases="
				+ varCases + "]";
	}
	
	
	 
}
