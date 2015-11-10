package org.wahlzeit.model;

public interface Coordinate {

	/**
	 * Calculates the distance between 2 Coordinates
	 * (not Euclidian distance)
	 * @methodType comparison
	 */
	double getDistance(Coordinate other);

	/**
	 * Compares 2 Coordinates and determines whether their representations
	 * are equal
	 * 
	 * @methodType comparison
	 */
	boolean isEqual(Coordinate other);

	/**
	 * Returns a representation of the coordinate which can be used
	 * to construct a SphericCoordinate
	 * 
	 * [0] should contain the latitude, [1] should contain the
	 * longitude, and [2] should contain the radius
	 * 
	 * For conversion formula see: https://de.wikipedia.org/wiki/Kugelkoordinaten
	 * (German)
	 * 
	 * @methodType interpretation
	 */
	double[] asSphericRepresentation();
	
	/**
	 * Returns a representation of the coordinate which can be used
	 * to construct a CartesianCoordinate
	 * 
	 * [0] should contain x, [1] should contain y, and [2] should contain z
	 * 
	 * For conversion formulas see: https://de.wikipedia.org/wiki/Kugelkoordinaten
	 * (German)
	 * 
	 * @methodType interpretation
	 */
	double[] asCartesianRepresentation();
}