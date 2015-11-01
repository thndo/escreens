package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Coordinate
 * 
 *
 */
public class CoordinateTest {
	private final double delta = 1.0;
	
	private Coordinate testCoord;
	private Coordinate firstCoord;
	private Coordinate secondCoord;

	@Test
	public void constructorTest() {
		testCoord = new Coordinate(80.5, 200.5);
		assertEquals(80.5, testCoord.getLatitude(), delta);
		assertEquals(200.5, testCoord.getLongitude(), delta);
		
		testCoord = new Coordinate(Coordinate.MIN_LATITUDE, Coordinate.MIN_LONGITUDE);
		assertEquals(Coordinate.MIN_LATITUDE, testCoord.getLatitude(), delta);
		assertEquals(Coordinate.MIN_LONGITUDE, testCoord.getLongitude(), delta);
		
		testCoord = new Coordinate(Coordinate.MAX_LATITUDE, Coordinate.MAX_LONGITUDE);
		assertEquals(Coordinate.MAX_LATITUDE, testCoord.getLatitude(), delta);
		assertEquals(Coordinate.MAX_LONGITUDE, testCoord.getLongitude(), delta);
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
		double resultCom;
		
		firstCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		secondCoord = new Coordinate(20, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Normal test", 160.0, result, delta);
		
		resultCom = secondCoord.getLatitudinalDistance(firstCoord);
		assertEquals("Commutativity test.", result, resultCom, delta);
		
		firstCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		secondCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Zero result test.", 0.0, result, delta);
		
		firstCoord = new Coordinate(20, 0);
		secondCoord = new Coordinate(Coordinate.MAX_LATITUDE, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Negative result test.", 160.0, result, delta);
		
		firstCoord = new Coordinate(60, 0);
		secondCoord = new Coordinate(0, 0);
		result = firstCoord.getLatitudinalDistance(secondCoord);
		assertEquals("Subtract zero test.", firstCoord.getLatitude(), result, delta);
	}
	
	@Test
	public void getLongitudinalDistanceTest(){
		double result;
		double resultCom;
		
		firstCoord = new Coordinate(0, 240);
		secondCoord = new Coordinate(0, 120);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Normal test.", 120.0, result, delta);
		
		resultCom = secondCoord.getLongitudinalDistance(firstCoord);
		assertEquals("Commutativity test.", result, resultCom, delta);
		
		firstCoord = new Coordinate(0, 359);
		secondCoord = new Coordinate(0, 359);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Zero result.", 0.0, result, delta);
		
		firstCoord = new Coordinate(0, 20);
		secondCoord = new Coordinate(0, 180);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Negative result.", 160.0, result, delta);
		
		firstCoord = new Coordinate(0, 80);
		secondCoord = new Coordinate(0, 0);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Subtract zero.", firstCoord.getLongitude(), result, delta);
		
		firstCoord = new Coordinate(0, 350);
		secondCoord = new Coordinate(0, 30);
		result = firstCoord.getLongitudinalDistance(secondCoord);
		assertEquals("Distance larger than 180 without wrapping test.", 40, result, delta);
	}
	
	@Test
	public void getDistanceTest(){
		double expected;
		double result;
		
		firstCoord = new Coordinate(180, 350);
		secondCoord = new Coordinate(60, 220);
		expected = 13358;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Normal test.", expected, result, delta);
		
		expected = result;
		result = secondCoord.getDistance(firstCoord);
		assertEquals("Commutativity test.", expected, result, delta);
		
		firstCoord = new Coordinate(60, 220);
		secondCoord = new Coordinate(180, 350);
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Negative result test.", expected, result, delta);
		
		firstCoord = new Coordinate(180, Coordinate.MAX_LONGITUDE);
		secondCoord = new Coordinate(180, Coordinate.MAX_LONGITUDE);
		expected = 0;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Zero result test.", expected, result, delta);
		
		firstCoord = new Coordinate(40, 90);
		secondCoord = new Coordinate(0, 0);
		expected = 4452;
		result = firstCoord.getDistance(secondCoord);
		assertEquals("Distance to zero point test.", expected, result, delta);
	}
}
