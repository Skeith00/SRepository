package com.thoughtworks.codeassignment.filters;

import com.thoughtworks.codeassignment.generic.Filter;

public class SimpleFilter extends Filter{
	
	public SimpleFilter(int numStops){
		setNumFilter(numStops);
	}
	
	public int applyFilter(int numToFilter){
		if(numToFilter<getNumFilter()){
			return 1;
		}
		return 0;
	}
}
