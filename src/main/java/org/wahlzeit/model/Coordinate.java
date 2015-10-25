package org.wahlzeit.model;

/**
 * Class containing a position expressed as latitude and longitude
 *
 */

public class Coordinate {

	/**
	 * Northern latitude: 0 <= latitude <= 90 Southern latitude: 90 <= latitude
	 * <= 180 The 90th latitude is the zero latitude in the traditional sense.s
	 * 
	 */
	private double latitude;

	/**
	 * Eastern longitude: 0 <= longitude <= 180 Western longitude: 180 <=
	 * longitude < 360
	 * 
	 * The 360th longitude is the same as the 0th longitude. It wraps around at
	 * the 360th longitude
	 */
	private double longitude;

	public final static double MAX_LATITUDE = 180;
	public final static double MIN_LATITUDE = 0;
	public final static double MAX_LONGITUDE = 359;
	public final static double MIN_LONGITUDE = 0;

	private final static String LATITUDE_ARG_ERR_MSG = "Latitude values have"
			+ " to be between 0 and 180.";
	private final static String LONGITUDE_ARG_ERR_MSG = "Longitude values have"
			+ " to be between 0 and 359.";

	/*
	 * @MethodType Constructor
	 */
	public Coordinate(double latitude, double longitude) {
		if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
			throw new IllegalArgumentException(LATITUDE_ARG_ERR_MSG);
		} else if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
			throw new IllegalArgumentException(LONGITUDE_ARG_ERR_MSG);
		} else {
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}

	/*
	 * 
	 */
	public Coordinate getDistance(Coordinate other) {
		double longitudinalDistance = getLongitudinalDistance(other);
		double latitutudinalDistance = getLatitudinalDistance(other);
		return new Coordinate(latitutudinalDistance, longitudinalDistance);
	}

	/*
	 * 
	 */
	public double getLatitudinalDistance(Coordinate other) {
		return Math.abs(this.latitude - other.getLatitude());
	}

	/*
	 * 
	 */
	public double getLongitudinalDistance(Coordinate other) {
		double distance = Math.abs(this.longitude - other.getLongitude());

		if (distance > 180) {
			distance = 360 - distance;
		}

		return distance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
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
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) {
			return false;
		}
		return true;
	}

	/*
	 * 
	 */
	public double getLongitude() {
		return longitude;
	}

	/*
	 * 
	 */
	public double getLatitude() {
		return latitude;
	}
}
