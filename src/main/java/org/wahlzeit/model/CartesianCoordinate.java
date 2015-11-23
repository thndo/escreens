package org.wahlzeit.model;

/**
 * Class containing a Coordinate represented as a Cartesian Coordinate
 *
 */
public class CartesianCoordinate extends AbstractCoordinate {
	private double x;
	private double y;
	private double z;

	public final static double EPSILON = 0.001;
	public final static double EARTH_RADIUS = 6371;

	/**
	 * @methodType constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertValidState();
	}

	/**
	 * @see AbstractCoordinate
	 * @methodType comparison
	 */
	protected double doGetDistance(Coordinate other) {
		CartesianCoordinate otherCoord = (CartesianCoordinate) other;
		double euclidianDistance = Math.sqrt(Math.pow(this.x - otherCoord.x, 2) 
				+ Math.pow(this.y - otherCoord.y, 2)
				+ Math.pow(this.z - otherCoord.z, 2));
		double omega = 2 * Math.asin(euclidianDistance / 2 / EARTH_RADIUS);
		return omega * EARTH_RADIUS;
	};

	/**
	 * Converts a coordinate into a CartesianCoordinate
	 * 
	 * @methodType conversion
	 * @param other
	 * @return
	 */
	public static CartesianCoordinate asCartesianCoordinate(Coordinate other) {
		assertNotNull(other);
		CartesianCoordinate retCoord; 
		if (other instanceof CartesianCoordinate) {
			retCoord = (CartesianCoordinate) other;
		} else {
			double[] otherRepresentation = other.asCartesianRepresentation();
			isCartesianRepresentationValid(otherRepresentation);
			retCoord = new CartesianCoordinate(otherRepresentation[0], 
					otherRepresentation[1], otherRepresentation[2]);
		}
		retCoord.assertValidState();
		return retCoord;
	}

	/**
	 * Checks whether a the given representation is a valid representation of a
	 * Cartesian coordinate
	 * 
	 * @methodType assertion
	 */
	protected static void isCartesianRepresentationValid(double[] representation) {
		if (representation == null || !(representation instanceof double[])
				|| ((double[]) representation).length != 3) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asSphericRepresentation() {
		assertValidState();
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		double latitude = Math.toDegrees(Math.acos(z / radius));
		double longitude = Math.toDegrees(Math.atan2(y, x));
		if (longitude < 0) {
			longitude = 360 + longitude;
		}
		
		return new double[] { latitude, longitude, radius };
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asCartesianRepresentation() {
		assertValidState();
		return new double[] { x, y, z };
	}

	/**
	 * @see AbstractCoordinate
	 * @methodType conversion
	 */
	protected Coordinate asOwnCoordinate(Coordinate other) {
		return CartesianCoordinate.asCartesianCoordinate(other);
	}
	
	/**
	 * @methodType getter
	 * @return the x
	 */
	public double getX() {
		assertValidState();
		return x;
	}

	/**
	 * @methodType getter
	 * @return the y
	 */
	public double getY() {
		assertValidState();
		return y;
	}

	/**
	 * @methodType getter
	 * @return the z
	 */
	public double getZ() {
		assertValidState();
		return z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		assertValidState();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		assertValidState();
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
		if (Math.abs(x - other.x) > EPSILON) {
			return false;
		}
		if (Math.abs(y - other.y) > EPSILON) {
			return false;
		}
		if (Math.abs(z - other.z) > EPSILON) {
			return false;
		}
		return true;
	}
	
	/**
	 * Asserts whether the coordinate's state is valid
	 * 
	 * @methodType assertion
	 * 
	 */
	private void assertValidState(){
		assertValidDouble(x);
		assertValidDouble(y);
		assertValidDouble(z);
	}
}
