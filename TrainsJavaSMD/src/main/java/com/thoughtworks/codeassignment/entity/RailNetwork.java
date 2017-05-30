package com.thoughtworks.codeassignment.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RailNetwork {
	
	private Map<Character, Town> townsWithinRailNetwork;
	
	public RailNetwork(Map<Character, Town> townsInRailNetwork){
		this.townsWithinRailNetwork = townsInRailNetwork;
	} 
	
	public int getDistance(Character[] namesOfTheTowns){
		return getDistanceAmongTowns(namesOfTheTowns);
	}
	
	public int getDistanceAmongTowns(Character[] namesOfTheTowns){
		List<Character> namesOfTheTownsList = new ArrayList<Character>(Arrays.asList(namesOfTheTowns));
		Iterator<Character> iteratorTowns = namesOfTheTownsList.iterator();
		Character nameStartingTown = iteratorTowns.next();
		int totalDistance = 0;
		
		while(iteratorTowns.hasNext()) {
			Character nameNextTown = iteratorTowns.next();
			int distance = getDistanceBetweenTwoTowns(nameStartingTown, nameNextTown);
			
			if(distance == -1)
				return distance;
			else
				totalDistance += distance;
			
			nameStartingTown = nameNextTown;
		}
		
		return totalDistance;
	}
	
	public int getDistanceBetweenTwoTowns(Character nameStartingTown, Character nameNextTown){
		Town startingTown = townsWithinRailNetwork.get(nameStartingTown);
		
		for(Route route : startingTown.getRoutesConnected()){
			if(nameNextTown.equals(route.getEndingTown().getName())){
				return route.getDistance();
			}
		}
		return -1;
	}

	public Map<Character, Town> getTownsWithinNetwork() {
		return townsWithinRailNetwork;
	}

	public void setTownsOnNetwork(Map<Character, Town> townsInNetwork) {
		this.townsWithinRailNetwork = townsInNetwork;
	}
}