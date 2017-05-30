package com.thoughtworks.codeassignment.service;

import java.util.Map;

import com.thoughtworks.codeassignment.entity.RailNetwork;
import com.thoughtworks.codeassignment.entity.Route;
import com.thoughtworks.codeassignment.entity.Town;
import com.thoughtworks.codeassignment.generic.Filter;
import com.thoughtworks.codeassignment.generic.RailwaySearchService;

public class RailwayTripService extends RailwaySearchService {
	
	public int getNumberTrips(Character from, Character to, RailNetwork railNetwork){	
		Map<Character, Town> towns = railNetwork.getTownsWithinNetwork();
		Town startingTown = towns.get(from);
		Town endingTown = towns.get(to);
		return calculateNumTrips(startingTown, endingTown, 1);
	}
		
	private int calculateNumTrips(Town startingTown, Town endingTown, int currentStops) {
		int numStops = filter.getNumFilter();
		if(currentStops > numStops)
			return 0;

		int totalTrips = 0;
		for(Route route : startingTown.getRoutesConnected()){
			Town nextTown = route.getEndingTown();
			if(nextTown == endingTown){
				totalTrips += filter.applyFilter(currentStops); 
			}
			totalTrips += calculateNumTrips(route.getEndingTown(), endingTown, currentStops+1);	
		}
		return totalTrips;
	}
	
	public void setFilter(Filter filter){
		this.filter = filter;
	}
}
