package com.thoughtworks.codeassignment.entity;


public class RouteInfo {

	final static int STARTINGTOWN = 0;
	final static int ENDINGTOWN = 1;
	final static int DISTANCE = 2;
	
	private Character nameStartingTown;
	private Character nameEndingTown;
	private int distance;
		
	public RouteInfo(Character nameStartingTown, Character nameEndingTown, int distance){
		this.nameStartingTown = nameStartingTown;
		this.nameEndingTown = nameEndingTown;
		this.distance = distance;
	}

	public static RouteInfo generateRouteInfo(String routeName) throws Exception{
		if(routeName.length() == 3){
			Character nameStartingTown = routeName.charAt(STARTINGTOWN);
			Character nameEndingTown = routeName.charAt(ENDINGTOWN);
			int distance;
			if(!Character.isDigit(routeName.charAt(DISTANCE))){
				throw new Exception("Incorrect Route Info. Incorrect distance");
			}
			else{
				distance = Character.getNumericValue(routeName.charAt(DISTANCE));
			}
			return new RouteInfo(nameStartingTown, nameEndingTown, distance);
		}
		else{
			throw new Exception("Incorrect Route Info. Too Long");
		}
	}
	
	public Character getNameStartingTown() {
		return nameStartingTown;
	}

	public void setNameStartingTown(Character nameStartingTown) {
		this.nameStartingTown = nameStartingTown;
	}

	public Character getNameEndingTown() {
		return nameEndingTown;
	}

	public void setNameEndingTown(Character nameEndingTown) {
		this.nameEndingTown = nameEndingTown;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
