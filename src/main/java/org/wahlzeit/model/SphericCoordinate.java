package org.wahlzeit.model;

/**
 * Class containing a position expressed as latitude and longitude
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

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

	/**
	 * 
	 */
	private double radius;

	public final static double EPSILON = 0.001;
	public final static double MAX_LATITUDE = 180;
	public final static double MIN_LATITUDE = 0;
	public final static double MAX_LONGITUDE = 359;
	public final static double MIN_LONGITUDE = 0;
	public final static double EARTH_RADIUS = 6371;

	private final static String LATITUDE_ARG_ERR_MSG = "Latitude values have" + " to be between 0 and 180.";
	private final static String LONGITUDE_ARG_ERR_MSG = "Longitude values have" + " to be between 0 and 359.";

	/**
	 * @MethodType constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
			throw new IllegalArgumentException(LATITUDE_ARG_ERR_MSG);
		} else if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
			throw new IllegalArgumentException(LONGITUDE_ARG_ERR_MSG);
		} else {
			this.latitude = latitude;
			this.longitude = longitude;
			this.radius = radius;
		}
	}

	/**
	 * @see AbstractCoordinate
	 * @methodType comparison
	 */
	protected double doGetDistance(Coordinate other) {
		SphericCoordinate otherCoord = (SphericCoordinate) other;
		double ownLatRad = Math.toRadians(asCommonLatitude());
		double ownLongRad = Math.toRadians(asCommonLongitude());
		double otherLatRad = Math.toRadians(otherCoord.asCommonLatitude());
		double otherLongRad = Math.toRadians(otherCoord.asCommonLongitude());
		return EARTH_RADIUS * Math.acos(Math.sin(ownLatRad) * Math.sin(otherLatRad)
				+ Math.cos(ownLatRad) * Math.cos(otherLatRad) 
				* Math.cos(otherLongRad - ownLongRad));
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLatitudinalDistance(SphericCoordinate other) {
		isValid(other);
		return Math.abs(this.latitude - other.getLatitude());
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLongitudinalDistance(SphericCoordinate other) {
		isValid(other);
		double distance = Math.abs(this.longitude - other.getLongitude());

		if (distance > 180) {
			distance = 360 - distance;
		}

		return distance;
	}

	/**
	 * Converts a coordinate into a SphericCoordinate
	 * 
	 * @methodType conversion
	 * @param other
	 * @return
	 */
	public static SphericCoordinate asSphericCoordinate(Coordinate other) {
		isValid(other);
		if (other instanceof SphericCoordinate) {
			return (SphericCoordinate) other;
		} else {
			double[] otherRepresentation = other.asSphericRepresentation();
			isSphericRepresentationValid(otherRepresentation);
			return new SphericCoordinate(otherRepresentation[0], otherRepresentation[1], otherRepresentation[2]);
		}
	}

	/**
	 * Checks whether the given representation is a valid representation of a
	 * Spheric coordinate
	 * 
	 * @methodType assertion
	 */
	protected static void isSphericRepresentationValid(double[] representation) {
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
		return new double[] { latitude, longitude, radius };
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asCartesianRepresentation() {
		double x = radius * Math.sin(Math.toRadians(latitude)) * Math.cos(Math.toRadians(longitude));
		double y = radius * Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(longitude));
		double z = radius * Math.cos(Math.toRadians(latitude));
		return new double[] { x, y, z };
	}

	/**
	 * @see AbstractCoordinate
	 * @methodType conversion
	 */
	protected Coordinate asOwnCoordinate(Coordinate other) {
		return SphericCoordinate.asSphericCoordinate(other);
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
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * @methodType comparison
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			System.out.println("Spheric same");
			return true;
		}
		if (obj == null) {
			System.out.println("Spheric null");
			return false;
		}
		if (!(obj instanceof SphericCoordinate)) {
			System.out.println("Spheric not Spheric");
			return false;
		}
		SphericCoordinate other = (SphericCoordinate) obj;
		if (Math.abs(latitude - other.latitude) > EPSILON) {
			return false;
		}
		if (Math.abs(longitude - other.longitude) > EPSILON) {
			return false;
		}
		if (Math.abs(radius - other.radius) > EPSILON) {
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
	public double asCommonLongitude() {
		if (longitude <= 180) {
			return longitude;
		} else {
			return longitude - 360;
		}
	}

	/**
	 * @methodType conversion
	 * @methodProperties primitive
	 */
	public double asCommonLatitude() {
		if (latitude <= 90) {
			return -(90 - latitude);
		} else {
			return latitude - 90;
		}
	}
}
