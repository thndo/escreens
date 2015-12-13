package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * @see Coordinate
	 * @methodType comparison
	 */
	@Override
	public double getDistance(Coordinate other) {
		assertNotNull(other);
		assertValidState();
		Coordinate otherCoord = asOwnCoordinate(other);
		double distance = doGetDistance(otherCoord);
		assertValidResult(distance);
		return distance;
	}

	/**
	 * Real implementation of getDistance
	 * 
	 * @methodType comparison
	 * 
	 * @param other
	 * @return
	 */
	protected abstract double doGetDistance(Coordinate other);

	/**
	 * @see Coordinate
	 * @methodType comparison
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		assertNotNull(other);
		assertValidState();
		Coordinate otherCoord = asOwnCoordinate(other);
		return equals(otherCoord);
	}

	/**
	 * It may return an invalid representation of a SphericCoordinate WARNING:
	 * Validity is not asserted
	 * 
	 * An additional Representation class was deemed unnecessary (time
	 * cost/class bloat)
	 * 
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public abstract double[] asSphericRepresentation();

	/**
	 * It may return an invalid representation of a CartesianCoordinate WARNING:
	 * Validity is not asserted
	 * 
	 * An additional Representation class was deemed unnecessary (time
	 * cost/class bloat)
	 * 
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public abstract double[] asCartesianRepresentation();

	/**
	 * Checks whether the given coordinate is a valid Coordinate
	 * 
	 * @methodType assertion
	 * @methodProperties primitive
	 */
	protected static void assertNotNull(Coordinate toTest) {
		if (toTest == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks whether the given value is not a valid double
	 * 
	 * NaN is considered not valid Infinity is considered not valid
	 * 
	 * @methodType assertion
	 */
	protected static void assertValidDouble(double toTest) {
		if (Double.isNaN(toTest)) {
			throw new IllegalStateException();
		} else if (Double.isInfinite(toTest)) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Checks whether the given value is a valid result
	 * 
	 * @methodType assertion
	 */
	protected void assertValidResult(double toTest) {
		assertValidDouble(toTest);
		if (toTest < 0) {
			throw new IllegalStateException();
		}
	}

	/**
	 * @methodType conversion
	 */
	protected abstract Coordinate asOwnCoordinate(Coordinate other);

	/**
	 * @methodType assertion
	 */
	protected abstract void assertValidState();
}
