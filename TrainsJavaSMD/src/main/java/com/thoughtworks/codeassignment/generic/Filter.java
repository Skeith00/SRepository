package com.thoughtworks.codeassignment.generic;

public abstract class Filter {
	
	private int numFilter;
	
	public abstract int applyFilter(int numToFilter);

	public int getNumFilter() {
		return numFilter;
	}

	public void setNumFilter(int numFilter) {
		this.numFilter = numFilter;
	}
}
