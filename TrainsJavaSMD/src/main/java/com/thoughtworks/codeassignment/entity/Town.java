package com.thoughtworks.codeassignment.entity;

import java.util.ArrayList;
import java.util.List;

public class Town{
	private Character name;
	private List<Route> routesOut;
	
	public Town(Character name){
		this.name = name;
		routesOut = new ArrayList<Route>();
	}
	
	public Character getName() {
		return name;
	}
	public void setName(Character name) {
		this.name = name;
	}
	public List<Route> getRoutesConnected() {
		return routesOut;
	}
	public void setRoutesConnected(List<Route> routesOut) {
		this.routesOut = routesOut;
	}

	public void addRoute(Route singleRouteOut) {
		this.routesOut.add(singleRouteOut);
	}
}