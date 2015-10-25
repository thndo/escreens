package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Coordinate
 * 
 *
 */
public class CoordinateTest {
	private Coordinate testCoord;
	private Coordinate firstCoord;
	private Coordinate secondCoord;

	@Test
	public void constructorTest() {
		testCoord = new Coordinate(80.5, 200.5);
		assertEquals(80.5, testCoord.getLatitude(), 0.0);
		assertEquals(200.5, testCoord.getLongitude(), 0.0);
		
		testCoord = new Coordinate(Coordinate.MIN_LATITUDE, Coordinate.MIN_LONGITUDE);
		assertEquals(Coordinate.MIN_LATITUDE, testCoord.getLatitude(), 0.0);
		assertEquals(Coordinate.MIN_LONGITUDE, testCoord.getLongitude(), 0.0);
		
		testCoord = new Coordinate(Coordinate.MAX_LATITUDE, Coordinate.MAX_LONGITUDE);
		assertEquals(Coordinate.MAX_LATITUDE, testCoord.getLatitude(), 0.0);
		assertEquals(Coordinate.MAX_LONGITUDE, testCoord.getLongitude(), 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorLatitudeUnderTest() {
		testCoord = new Coordinate(-0.5, 5.5);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorLatitudeOverTest() {
		testCoord = new Coordinate(180.5, 5.5);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorLongitudeUnderTest() {
		testCoord = new Coordinate(5.5, -0.5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorLongitudeOverTest() {
		testCoord = new Coordinate(5.5, 359.5);
	}
	
	@Test
	public void getLatitudinalDistanceTest(){
		double result;
		
		// Normal test
		firstCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		secondCoord = new Coordinate(20, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals(160.0, result, 0.0);
		
		// Zero result
		firstCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		secondCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals(0.0, result, 0.0);
		
		// Negative result
		firstCoord = new Coordinate(20, 0);
		secondCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals(160.0, result, 0.0);
		
		// Subtract zero
		firstCoord = new Coordinate(60, 0);
		secondCoord = new Coordinate(0, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals(firstCoord.getLatitude(), result, 0.0);
	}
	
	@Test
	public void getLongitudinalDistanceTest(){
		double result;
		
		// Normal test
		firstCoord = new Coordinate(0, 240);
		secondCoord = new Coordinate(0, 120);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals(120.0, result, 0.0);
		
		// Zero result
		firstCoord = new Coordinate(0, 359);
		secondCoord = new Coordinate(0, 359);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals(0.0, result, 0.0);
		
		// Negative result
		firstCoord = new Coordinate(0, 20);
		secondCoord = new Coordinate(0, 180);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals(160.0, result, 0.0);
		
		// Subtract zero
		firstCoord = new Coordinate(0, 80);
		secondCoord = new Coordinate(0, 0);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals(firstCoord.getLongitude(), result, 0.0);
		
		// Distance larger than 180 without wrapping
		firstCoord = new Coordinate(0, 350);
		secondCoord = new Coordinate(0, 30);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals(40, result, 0.0);
	}
	
	@Test
	public void getDistanceTest(){
		Coordinate result;
		Coordinate expected;
		
		// Normal test
		firstCoord = new Coordinate(180, 350);
		secondCoord = new Coordinate(60, 220);
		expected = new Coordinate(120, 130);
		result = firstCoord.getDistance(secondCoord);
		assertEquals(expected, result);
		
		// Negative test
		firstCoord = new Coordinate(60, 220);
		secondCoord = new Coordinate(180, 350);
		expected = new Coordinate(120, 130);
		result = firstCoord.getDistance(secondCoord);
		assertEquals(expected, result);
		
		// Zero test
		firstCoord = new Coordinate(180, Coordinate.MAX_LONGITUDE);
		secondCoord = new Coordinate(180, Coordinate.MAX_LONGITUDE);
		expected = new Coordinate(0, 0);
		result = firstCoord.getDistance(secondCoord);
		assertEquals(expected, result);
		
		// Subtract zero
		firstCoord = new Coordinate(40, 90);
		secondCoord = new Coordinate(0, 0);
		expected = firstCoord;
		result = firstCoord.getDistance(secondCoord);
		assertEquals(expected, result);
	}
}
