package com.thoughtworks.codeassignment.service;

import java.util.Map;

import com.thoughtworks.codeassignment.entity.RailNetwork;
import com.thoughtworks.codeassignment.entity.Route;
import com.thoughtworks.codeassignment.entity.Town;
import com.thoughtworks.codeassignment.generic.RailwaySearchService;

public class RailwayRouteService extends RailwaySearchService{
	
	public int getTotalRoutes(Character from, Character to, RailNetwork railNetwork){
		Map<Character, Town> towns = railNetwork.getTownsWithinNetwork();
		Town startingTown = towns.get(from);
		Town endingTown = towns.get(to);
		return calculateTotalRoutes(startingTown, endingTown, 0);
	}
	
	public int calculateTotalRoutes(Town startingTown, Town endingTown, int currentDistance) {
		int maxDistance = filter.getNumFilter();
		if(currentDistance >= maxDistance)
			return 0;

		int totalTrips = 0;
		for(Route route : startingTown.getRoutesConnected()){
			Town nextTown = route.getEndingTown();
			if(nextTown == endingTown)
				totalTrips += filter.applyFilter(currentDistance+route.getDistance()); ;

			totalTrips += calculateTotalRoutes(route.getEndingTown(), endingTown, currentDistance+route.getDistance());	
		}
		return totalTrips;
	}
	

}
