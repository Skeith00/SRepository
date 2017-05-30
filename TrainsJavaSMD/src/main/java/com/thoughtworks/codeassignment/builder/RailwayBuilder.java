package com.thoughtworks.codeassignment.builder;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.codeassignment.entity.RailNetwork;
import com.thoughtworks.codeassignment.entity.Route;
import com.thoughtworks.codeassignment.entity.RouteInfo;
import com.thoughtworks.codeassignment.entity.Town;

public class RailwayBuilder {

	private Character[] nameTownsRailNetwork;
	private String[] nameRoutesRailNetwork;
	
	public RailwayBuilder(Character[] nameTownsRailNetwork, String[] nameRoutesRailNetwork){
		this.nameTownsRailNetwork = nameTownsRailNetwork;
		this.nameRoutesRailNetwork = nameRoutesRailNetwork;
	};
	
	public RailNetwork createRailNetwork(){
		RailNetwork railNetwork = null;

		try{
			Map<Character, Town> townsInNetwork = createTowns();
			includeRoutesToTowns(townsInNetwork);
			railNetwork = new RailNetwork(townsInNetwork);
			return railNetwork;
		}catch(Exception e){
			System.out.println("Problems creating the RailRoad. ");
		}
		
		return railNetwork;
	}	
	
	private Map<Character, Town> createTowns()  throws Exception {
		Map<Character, Town> townsInNetwork = new HashMap<Character, Town>();
		for(Character townName : nameTownsRailNetwork){
			townsInNetwork.put(townName, new Town(townName));
		}
		return townsInNetwork;
	}
	
	private void includeRoutesToTowns(Map<Character, Town> townsInNetwork) throws Exception {
		for(String routeName : nameRoutesRailNetwork){
			RouteInfo routeInfo = RouteInfo.generateRouteInfo(routeName);
			Town startingTown = townsInNetwork.get(routeInfo.getNameStartingTown());
			Town endingTown = townsInNetwork.get(routeInfo.getNameEndingTown());
			int distance = routeInfo.getDistance();
			Route routeToInclude = new Route(endingTown, distance);
			startingTown.addRoute(routeToInclude);
		}
	}
}
