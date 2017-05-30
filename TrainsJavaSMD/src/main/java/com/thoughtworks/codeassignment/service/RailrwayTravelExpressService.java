package com.thoughtworks.codeassignment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.codeassignment.entity.RailNetwork;
import com.thoughtworks.codeassignment.entity.Route;
import com.thoughtworks.codeassignment.entity.Town;
import com.thoughtworks.codeassignment.generic.RailwaySearchService;

public class RailrwayTravelExpressService extends RailwaySearchService{

	private Map<Town, Boolean> settledTowns;
	private Map<Town, Route> dijkstraTable;

	private void initSettledTowns(Map<Character, Town> towns){
		settledTowns = new HashMap<Town, Boolean>();
		dijkstraTable = new HashMap<Town, Route>();
		for(Town singleTown: towns.values())
		{
			settledTowns.put(singleTown, false);
			dijkstraTable.put(singleTown, null);
		}
	}
	
	public int getShortestRoute(Character from, Character to, RailNetwork railNetwork){
		Map<Character, Town> towns = railNetwork.getTownsWithinNetwork();
		Town startingTown = towns.get(from);
		Town endingTown = towns.get(to);
		initSettledTowns(railNetwork.getTownsWithinNetwork());
		return calculateShortestRoute(startingTown, endingTown, 0);
	}
	
	// Dijkstra Algorithm
	private int calculateShortestRoute(Town startingTown, Town endingTown, int distance){
		settleTown(startingTown);
		List<Route> routes = startingTown.getRoutesConnected();
		for(Route route : routes){
			Route routeForTheRouteTable = new Route(startingTown, route.getDistance()+distance);
			addToDijkstraTable(route.getEndingTown(), routeForTheRouteTable);
		}
		Town shortestTown = findShortestInsideDijkstraTable();
		if(shortestTown == null){
			return dijkstraTable.get(endingTown).getDistance();
		}
		else {
			return calculateShortestRoute(shortestTown, endingTown, dijkstraTable.get(shortestTown).getDistance());
		}
	}
	
	private Town findShortestInsideDijkstraTable(){
		int minDistance = -1;
		Town shortestTown = null;
		
		for (Entry<Town, Route> townRoutePair : dijkstraTable.entrySet()) {
			Town townDijkstra = townRoutePair.getKey();
			if(!settledTowns.get(townDijkstra)){
				Route routeRelatedToTown = townRoutePair.getValue();
				if(routeRelatedToTown != null){
				    if (minDistance == -1 || minDistance > routeRelatedToTown.getDistance()) {
				    	minDistance = routeRelatedToTown.getDistance();
				    	shortestTown = townDijkstra;
				    }
			    }
			}
		}
		return shortestTown;
	}
	
	private void settleTown(Town town){
		settledTowns.put(town, true);
	}
	
	private void addToDijkstraTable(Town town, Route route){
		if(dijkstraTable.get(town)== null || dijkstraTable.get(town).getDistance()>route.getDistance())
			dijkstraTable.put(town, route);
	}
}
