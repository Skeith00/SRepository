package com.thoughtworks.codeassignment.trains;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.codeassignment.builder.RailwayBuilder;
import com.thoughtworks.codeassignment.entity.RailNetwork;
import com.thoughtworks.codeassignment.filters.AccuracyFilter;
import com.thoughtworks.codeassignment.filters.SimpleFilter;
import com.thoughtworks.codeassignment.generic.Filter;
import com.thoughtworks.codeassignment.generic.RailwaySearchService;
import com.thoughtworks.codeassignment.reader.RailwayReader;
import com.thoughtworks.codeassignment.service.RailrwayTravelExpressService;
import com.thoughtworks.codeassignment.service.RailwayRouteService;
import com.thoughtworks.codeassignment.service.RailwayTripService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

/**
 * Unit test for simple App.
 */
public class TestRailNetwork
{
	private RailNetwork railNetworkKiwiland;	
	private Character[] nameTownsRailNetwork = {'A', 'B', 'C', 'D', 'E'};
	private String[] nameRoutesRailNetwork = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
	private static String txtRoutesFile = "routes.txt";
	private static boolean readFromFile = false;
	
	private RailrwayTravelExpressService expressService;
	private RailwayTripService tripService;
	private RailwayRouteService routeService;;
		
	@Before
	public void init(){
		if(readFromFile){
			nameRoutesRailNetwork = RailwayReader.routeReader(txtRoutesFile);
		}
		RailwayBuilder kiwilandService = new RailwayBuilder(nameTownsRailNetwork, nameRoutesRailNetwork);
		railNetworkKiwiland = kiwilandService.createRailNetwork();
		
		tripService = new RailwayTripService();
		expressService = new RailrwayTravelExpressService();
		routeService = new RailwayRouteService();
	}

	@Test
	public void firsthTest(){
		Character[] nameOfTowns = {'A', 'B', 'C'};
		int resultingDistance = railNetworkKiwiland.getDistance(nameOfTowns);
		System.out.println("Output #1: "+print(resultingDistance));
		assertThat(resultingDistance,is(9));
	}
	
	@Test
	public void secondTest(){
		Character[] nameOfTowns = {'A', 'D'};
		int resultingDistance = railNetworkKiwiland.getDistance(nameOfTowns);
		System.out.println("Output #2: "+print(resultingDistance));
		assertThat(resultingDistance,is(5));
		
	}

	@Test
	public void thirdTest(){
		Character[] nameOfTowns = {'A', 'D', 'C'};
		int resultingDistance = railNetworkKiwiland.getDistance(nameOfTowns);
		System.out.println("Output #3: "+print(resultingDistance));
		assertThat(resultingDistance,is(13));
	}
	@Test
	public void fourthTest(){
		Character[] nameOfTowns = {'A', 'E', 'B', 'C', 'D'};
		int resultingDistance = railNetworkKiwiland.getDistance(nameOfTowns);
		System.out.println("Output #4: "+print(resultingDistance));
		assertThat(resultingDistance, is(22));
	}
	
	@Test
	public void fifthTest(){
		Character[] nameOfTowns = {'A', 'E', 'D'};
		int resultingDistance = railNetworkKiwiland.getDistance(nameOfTowns);
		System.out.println("Output #5: "+print(resultingDistance));
		assertThat(resultingDistance, is(-1));
	}
	
	@Test
	public void sixthTest(){
		Character from = 'C';
		Character to = 'C';
		int numStops = 3;
		
		AccuracyFilter maxStopsFilter = new AccuracyFilter(numStops, AccuracyFilter.MAXSTOPS);
		tripService.setFilter(maxStopsFilter);
		
		int resultingTrips = tripService.getNumberTrips(from, to, railNetworkKiwiland);
		System.out.println("Output #6: "+resultingTrips);
		assertThat(resultingTrips, is(2));
	}
	
	@Test
	public void seventhTest(){
		Character from = 'A';
		Character to = 'C';
		int numStops = 4;
		Filter maxStopsFilter = new AccuracyFilter(numStops, AccuracyFilter.EXACTLYSTOPS);
		tripService.setFilter(maxStopsFilter);
		
		int resultingTrips = tripService.getNumberTrips(from, to, railNetworkKiwiland);
		System.out.println("Output #7: "+resultingTrips);
		assertThat(resultingTrips, is(3));
	}
	
	@Test
	public void eighthTest(){
		Character from = 'A';
		Character to = 'C';

		int resultingDistance = expressService.getShortestRoute(from, to, railNetworkKiwiland);
		System.out.println("Output #8: "+resultingDistance);
		assertThat(resultingDistance, is(9));
	}
	
	@Test
	public void ninethTest(){
		Character from = 'B';
		Character to = 'B';

		int resultingDistance = expressService.getShortestRoute(from, to, railNetworkKiwiland);
		System.out.println("Output #9: "+resultingDistance);
		assertThat(resultingDistance, is(9));
	}
	
	@Test
	public void tenthTest(){
		Character from = 'C';
		Character to = 'C';
		Filter maxStopsFilter = new SimpleFilter(30);
		routeService.setFilter(maxStopsFilter);
		
		int resultingDistance = routeService.getTotalRoutes(from, to, railNetworkKiwiland);
		System.out.println("Output #10: "+resultingDistance);
		assertThat(resultingDistance, is(7));
	}
	
	public String print(int resultingDistance){
		if(resultingDistance == -1)
			return "NO SUCH ROUTE";
		return String.valueOf(resultingDistance);
	}
}
