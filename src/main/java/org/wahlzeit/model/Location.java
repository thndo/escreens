package org.wahlzeit.model;

public class Location {
	private String name;
	private Coordinate coordinate;
	
	/**
	 * @methodType constructor
	 * @param name the name of the location
	 * @param coordinate the coordinate of the location
	 */
	public Location(String name, Coordinate coordinate){
		this.name = name;
		this.coordinate = coordinate;
	}
	
	/**
	 * @methodType get
	 * @methodProperties primitive
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @methodType set
	 * @methodProperties primitive
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @methodType get
	 * @methodProperties primitive
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * @methodType set
	 * @methodProperties primitive
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	
}
