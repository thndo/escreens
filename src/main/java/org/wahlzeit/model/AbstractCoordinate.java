package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * @see Coordinate
	 * @methodType comparison
	 */
	@Override
	public double getDistance(Coordinate other) {
		isValid(other);
		Coordinate otherCoord = asOwnCoordinate(other);
		return doGetDistance(otherCoord);
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
		isValid(other);
		Coordinate otherCoord = asOwnCoordinate(other);
		return equals(otherCoord);
	}

	/**
	 * @see Coordinate
	 * @methodType interpretation
	 */
	@Override
	public abstract double[] asSphericRepresentation();

	/**
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
	protected static void isValid(Coordinate toTest) {
		if (toTest == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @methodType conversion
	 */
	protected abstract Coordinate asOwnCoordinate(Coordinate other);

}
