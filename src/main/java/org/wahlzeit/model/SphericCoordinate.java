package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

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
	private final double latitude;

	/**
	 * Eastern longitude: 0 <= longitude <= 180 Western longitude: 180 <=
	 * longitude < 360
	 * 
	 * The 360th longitude is the same as the 0th longitude. It wraps around at
	 * the 360th longitude
	 */
	private final double longitude;

	/**
	 * 
	 */
	private final double radius;

	/**
	 * Map containing all initialized SphericCoordinate values
	 */
	private static Map<String, SphericCoordinate> instancedValues = new HashMap<>();

	public final static double EPSILON = 0.0000001;
	public final static double MAX_LATITUDE = 180;
	public final static double MIN_LATITUDE = 0;
	public final static double MAX_LONGITUDE = 359;
	public final static double MIN_LONGITUDE = 0;
	public final static double EARTH_RADIUS = 6371;

	private final static String LATITUDE_ARG_ERR_MSG = "Latitude values have" + " to be between 0 and 180.";
	private final static String LONGITUDE_ARG_ERR_MSG = "Longitude values have" + " to be between 0 and 359.";
	private final static String IDENTIFICATION_DELIMITER = "#";

	/**
	 * @MethodType constructor
	 */
	private SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		assertValidState();
	}

	/**
	 * Create a SphericCoordinate with given parameters and returns a value
	 * object representing that Coordinate
	 * 
	 * @MethodType Factory
	 */
	public static SphericCoordinate createSphericCoordinate(double latitude, double longitude, double radius) {
		String coordinateIdentifier = String.valueOf(latitude) + IDENTIFICATION_DELIMITER + String.valueOf(longitude)
				+ IDENTIFICATION_DELIMITER + String.valueOf(radius);
		if (instancedValues.containsKey(coordinateIdentifier)) {
			return instancedValues.get(coordinateIdentifier);
		} else {
			synchronized (instancedValues) {
				if (instancedValues.containsKey(coordinateIdentifier)) {
					return instancedValues.get(coordinateIdentifier);
				} else {
					SphericCoordinate retValue = new SphericCoordinate(latitude, longitude, radius);
					instancedValues.put(coordinateIdentifier, retValue);
					return retValue;
				}
			}
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
				+ Math.cos(ownLatRad) * Math.cos(otherLatRad) * Math.cos(otherLongRad - ownLongRad));
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLatitudinalDistance(SphericCoordinate other) {
		assertValidState();
		assertNotNull(other);
		double latDistance = Math.abs(this.latitude - other.getLatitude());
		assertValidState();
		return latDistance;
	}

	/**
	 * @methodType comparison
	 * @methodProperties
	 */
	public double getLongitudinalDistance(SphericCoordinate other) {
		assertValidState();
		assertNotNull(other);
		double distance = Math.abs(this.longitude - other.getLongitude());

		if (distance > 180) {
			distance = 360 - distance;
		}

		assertValidState();
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
		assertNotNull(other);
		double[] otherRepresentation = other.asSphericRepresentation();
		isSphericRepresentationValid(otherRepresentation);
		SphericCoordinate retCoord = createSphericCoordinate(otherRepresentation[0], otherRepresentation[1],
				otherRepresentation[2]);
		retCoord.assertValidState();
		return retCoord;
	}

	/**
	 * Conversion dummy to avoid unnecessary calculations
	 * 
	 * @methodType conversion
	 * @param other
	 * @return
	 */
	public static SphericCoordinate asSphericCoordinate(SphericCoordinate other) {
		assertNotNull(other);
		other.assertValidState();
		return (SphericCoordinate) other;
	}

	/**
	 * Checks whether the given representation is a valid representation of a
	 * Spheric coordinate
	 * 
	 * @methodType assertion
	 */
	protected static void isSphericRepresentationValid(double[] representation) {
		if (representation == null || representation.length != 3) {
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
		return new double[] { latitude, longitude, radius };
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public double[] asCartesianRepresentation() {
		assertValidState();
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
		assertValidState();
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
		assertValidState();
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SphericCoordinate)) {
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
		assertValidState();
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
		assertValidState();
		if (latitude <= 90) {
			return -(90 - latitude);
		} else {
			return latitude - 90;
		}
	}

	/**
	 * Asserts whether the coordinate's state is valid
	 * 
	 * @methodType assertion
	 */
	protected void assertValidState() {
		if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
			throw new IllegalStateException(LATITUDE_ARG_ERR_MSG);
		} else if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
			throw new IllegalStateException(LONGITUDE_ARG_ERR_MSG);
		} else if (radius < 0) {
			throw new IllegalStateException();
		}
		assertValidDouble(latitude);
		assertValidDouble(longitude);
		assertValidDouble(radius);
	}
}
