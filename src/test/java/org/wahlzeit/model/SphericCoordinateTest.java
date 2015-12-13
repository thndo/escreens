package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Coordinate
 * 
 */
public class SphericCoordinateTest {
	private final double delta = 1.0;

	private SphericCoordinate testCoord;
	private SphericCoordinate firstCoord;
	private SphericCoordinate secondCoord;

	@Test
	public void constructorTest() {
		testCoord = SphericCoordinate.createSphericCoordinate(80.5, 200.5, SphericCoordinate.EARTH_RADIUS);
		assertEquals(80.5, testCoord.getLatitude(), delta);
		assertEquals(200.5, testCoord.getLongitude(), delta);

		testCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MIN_LATITUDE,
				SphericCoordinate.MIN_LONGITUDE, SphericCoordinate.EARTH_RADIUS);
		assertEquals(SphericCoordinate.MIN_LATITUDE, testCoord.getLatitude(), delta);
		assertEquals(SphericCoordinate.MIN_LONGITUDE, testCoord.getLongitude(), delta);

		testCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MAX_LATITUDE,
				SphericCoordinate.MAX_LONGITUDE, SphericCoordinate.EARTH_RADIUS);
		assertEquals(SphericCoordinate.MAX_LATITUDE, testCoord.getLatitude(), delta);
		assertEquals(SphericCoordinate.MAX_LONGITUDE, testCoord.getLongitude(), delta);
	}

	@Test(expected = IllegalStateException.class)
	public void constructorLatitudeUnderTest() {
		testCoord = SphericCoordinate.createSphericCoordinate(-0.5, 5.5, SphericCoordinate.EARTH_RADIUS);
	}

	@Test(expected = IllegalStateException.class)
	public void constructorLatitudeOverTest() {
		testCoord = SphericCoordinate.createSphericCoordinate(180.5, 5.5, SphericCoordinate.EARTH_RADIUS);
	}

	@Test(expected = IllegalStateException.class)
	public void constructorLongitudeUnderTest() {
		testCoord = SphericCoordinate.createSphericCoordinate(5.5, -0.5, SphericCoordinate.EARTH_RADIUS);
	}

	@Test(expected = IllegalStateException.class)
	public void constructorLongitudeOverTest() {
		testCoord = SphericCoordinate.createSphericCoordinate(5.5, 359.5, SphericCoordinate.EARTH_RADIUS);
	}

	@Test
	public void getLatitudinalDistanceTest() {
		double result;
		double resultCom;

		firstCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MAX_LATITUDE, 0,
				SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(20, 0, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Normal test", 160.0, result, delta);

		resultCom = secondCoord.getLatitudinalDistance(firstCoord);
		assertEquals("Commutativity test.", result, resultCom, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MAX_LATITUDE, 0,
				SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MAX_LATITUDE, 0,
				SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Zero result test.", 0.0, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(20, 0, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(SphericCoordinate.MAX_LATITUDE, 0,
				SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Negative result test.", 160.0, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(60, 0, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Subtract zero test.", firstCoord.getLatitude(), result, delta);
	}

	@Test
	public void getLongitudinalDistanceTest() {
		double result;
		double resultCom;

		firstCoord = SphericCoordinate.createSphericCoordinate(0, 240, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 120, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Normal test.", 120.0, result, delta);

		resultCom = secondCoord.getLongitudinalDistance(firstCoord);
		assertEquals("Commutativity test.", result, resultCom, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(0, 359, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 359, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Zero result.", 0.0, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(0, 20, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 180, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Negative result.", 160.0, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(0, 80, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Subtract zero.", firstCoord.getLongitude(), result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(0, 350, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 30, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Distance larger than 180 without wrapping test.", 40, result, delta);
	}

	@Test
	public void getDistanceTest() {
		double expected;
		double result;

		firstCoord = SphericCoordinate.createSphericCoordinate(180, 350, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(60, 220, SphericCoordinate.EARTH_RADIUS);
		expected = 13343;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Normal test.", expected, result, delta);

		expected = result;
		result = secondCoord.getDistance(firstCoord);
		assertEquals("Commutativity test.", expected, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(60, 220, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(180, 350, SphericCoordinate.EARTH_RADIUS);
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Negative result test.", expected, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(180, SphericCoordinate.MAX_LONGITUDE,
				SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(180, SphericCoordinate.MAX_LONGITUDE,
				SphericCoordinate.EARTH_RADIUS);
		expected = 0;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Zero result test.", expected, result, delta);

		firstCoord = SphericCoordinate.createSphericCoordinate(40, 90, SphericCoordinate.EARTH_RADIUS);
		secondCoord = SphericCoordinate.createSphericCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS);
		expected = 4447;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Distance to zero point test.", expected, result, delta);
	}
}
