package com.thoughtworks.codeassignment.entity;

public class Route {
	private Town endingTown;
	private int distance;
	
	public Route(Town endingTown, int distance){
		this.endingTown = endingTown;
		this.distance = distance;
		
	}
	
	public Town getEndingTown() {
		return endingTown;
	}
	public void setEndingTown(Town endingTown) {
		this.endingTown = endingTown;
	}

	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}