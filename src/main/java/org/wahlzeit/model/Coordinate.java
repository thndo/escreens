package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * Class containing a position expressed as latitude and longitude
 *
 */

public class Coordinate extends DataObject {

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

	private final static String LATITUDE_ARG_ERR_MSG = "Latitude values have" +
			" to be between 0 and 180.";
	private final static String LONGITUDE_ARG_ERR_MSG = "Longitude values have" 
			+ " to be between 0 and 359.";

	/**
	 * @MethodType constructor
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

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getDistance(Coordinate other) {
		isValid(other);
		
		double ownLatRad = Math.toRadians(asCommonLatitude());
		double ownLongRad = Math.toRadians(asCommonLongitude());
		double otherLatRad = Math.toRadians(other.asCommonLatitude());
		double otherLongRad = Math.toRadians(other.asCommonLongitude());
		return 6378.388
				* Math.acos(Math.sin(ownLatRad) * Math.sin(otherLatRad) 
						+ Math.cos(ownLatRad) * Math.cos(otherLatRad) * 
						Math.cos(otherLongRad - ownLongRad));
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLatitudinalDistance(Coordinate other) {
		isValid(other);
		return Math.abs(this.latitude - other.getLatitude());
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLongitudinalDistance(Coordinate other) {
		isValid(other);
		double distance = Math.abs(this.longitude - other.getLongitude());

		if (distance > 180) {
			distance = 360 - distance;
		}

		return distance;
	}

	/**
	 * @methodType conversion
	 * 
	 *             (non-Javadoc)
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

	/**
	 * @methodType comparison
	 * 
	 *             (non-Javadoc)
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
		if (Double.doubleToLongBits(latitude) != 
				Double.doubleToLongBits(other.latitude)) {
			return false;
		}
		if (Double.doubleToLongBits(longitude) != 
				Double.doubleToLongBits(other.longitude)) {
			return false;
		}
		return true;
	}

	/**
	 * @methodType get
	 * @methodProperties primitive
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @methodType get
	 * @methodProperties primitive
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @methodType conversion
	 * @methodProperties primitive 
	 */
	public double asCommonLongitude(){
		if(longitude <= 180){
			return longitude;
		} else {
			return longitude - 360;
		}
	}
	
	/**
	 * @methodType conversion
	 * @methodProperties primitive 
	 */
	public double asCommonLatitude(){
		if(latitude <= 90){
			return -(90 - latitude);
		} else {
			return latitude - 90;
		}
	}
	
	/**
	 * @methodType assertion
	 * @methodProperties primitive
	 */
	protected void isValid(Coordinate toTest){
		if(toTest == null){
			throw new IllegalArgumentException();
		}
	}
	
}
