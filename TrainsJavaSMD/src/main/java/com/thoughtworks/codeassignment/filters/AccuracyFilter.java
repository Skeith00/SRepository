package com.thoughtworks.codeassignment.filters;

import com.thoughtworks.codeassignment.generic.Filter;

public class AccuracyFilter extends Filter{
	
	final static public int EXACTLYSTOPS = 0;
	final static public int MAXSTOPS = 1;
	
	int typeFilter;

	public AccuracyFilter(int numStops,int typeFilter){
		setNumFilter(numStops);
		this.typeFilter = typeFilter;
	}
	
	public int applyFilter(int currentStops){
		if(typeFilter==AccuracyFilter.MAXSTOPS){
			if(currentStops<=getNumFilter())
				return 1;
		}
		else if(typeFilter==AccuracyFilter.EXACTLYSTOPS){
			if(currentStops==getNumFilter())
				return 1;
		}
		return 0;
	}

	public int getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(int typeFilter) {
		this.typeFilter = typeFilter;
	}	
}
