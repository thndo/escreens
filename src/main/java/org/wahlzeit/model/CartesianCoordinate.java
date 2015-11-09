package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class CartesianCoordinate extends DataObject implements Coordinate {
	private double x;
	private double y;
	private double z;
	
	public final static double EARTH_RADIUS = 6371;
	/**
	 * @methodType constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public CartesianCoordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @see Coordinate
	 * @methodType comparison
	 */
	@Override
	public double getDistance(Coordinate other) {
		CartesianCoordinate otherCoord;
		if(other instanceof CartesianCoordinate){
			otherCoord = (CartesianCoordinate)other;
		} else {
			otherCoord = CartesianCoordinate.asCartesianCoordinate(other);
		}
		double euclidianDistance = Math.sqrt(Math.pow(this.x - otherCoord.x, 2)
				+ Math.pow(this.y - otherCoord.y, 2) 
				+ Math.pow(this.z - otherCoord.z, 2)); 
		double omega = 2 * Math.asin(euclidianDistance / 2 / EARTH_RADIUS);
		return omega * EARTH_RADIUS;
	}
	
	/**
	 * @see Coordinate
	 * @methodType conversion
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		if(other instanceof CartesianCoordinate){
			return equals(other);
		} else {
			CartesianCoordinate otherCoord = 
					CartesianCoordinate.asCartesianCoordinate(other);
			return equals(otherCoord);
		}
	}
	
	/**
	 * Converts a coordinate into a CartesianCoordinate
	 * 
	 * @methodType conversion
	 * @param other
	 * @return
	 */
	public static CartesianCoordinate asCartesianCoordinate(Coordinate other) {
		isValid(other);
		if (other instanceof CartesianCoordinate) {
			return (CartesianCoordinate) other;
		} else {
			double[] otherRepresentation = other.asCartesianRepresentation();
			isCartesianRepresentationValid(otherRepresentation);
			return new CartesianCoordinate(otherRepresentation[0], 
					otherRepresentation[1], otherRepresentation[2]);
		}
	}

	
	/**
	 * @methodType assertion
	 * @methodProperties primitive
	 */
	protected static void isValid(Coordinate toTest) {
		if (toTest == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks whether a CartesianRepresentation is valid
	 * 
	 * @methodType assertion
	 */
	protected static void isCartesianRepresentationValid(double[] representation) {
		if (representation == null || !(representation instanceof double[]) ||
				((double[])representation).length != 3) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asSphericRepresentation() {
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) 
				+ Math.pow(z, 2));
		double latitude = Math.toDegrees(Math.acos(z / radius));
		double longitude = Math.toDegrees(Math.atan2(y, x));
		if(longitude < 0){
			longitude = 360 + longitude;
		}
		
		return new double[]{latitude, 
				longitude, radius};
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asCartesianRepresentation() {
		return new double[]{x, y, z};
	}
	
	
	/**
	 * @methodType getter
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodType getter
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodType getter
	 * @return the z
	 */
	public double getZ() {
		return z;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CartesianCoordinate)) {
			return false;
		}
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) {
			return false;
		}
		return true;
	}
}
