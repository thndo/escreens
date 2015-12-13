package org.wahlzeit.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Coordinates
 * 
 */
public class CoordinateTest {
	private Coordinate berlin;
	private Coordinate tokio;
	private Coordinate losangeles;
	private Coordinate berlinCart;
	private Coordinate berlinSphere;
	private Coordinate tokioCart;
	private Coordinate tokioSphere;
	private Coordinate losangelesCart;
	private Coordinate losangelesSphere;
	
	@Before
	public void setUp(){
		berlin = SphericCoordinate.createSphericCoordinate(142, 347, SphericCoordinate.EARTH_RADIUS);
		tokio = SphericCoordinate.createSphericCoordinate(125, 221, SphericCoordinate.EARTH_RADIUS);
		losangeles = SphericCoordinate.createSphericCoordinate(125, 118, SphericCoordinate.EARTH_RADIUS);
		berlinCart = CartesianCoordinate.asCartesianCoordinate(berlin);
		tokioCart = CartesianCoordinate.asCartesianCoordinate(tokio);
		losangelesCart = CartesianCoordinate.asCartesianCoordinate(losangeles);
		berlinSphere = SphericCoordinate.asSphericCoordinate(berlinCart);
		losangelesSphere = SphericCoordinate.asSphericCoordinate(losangelesCart);
		tokioSphere = SphericCoordinate.asSphericCoordinate(tokioCart);
	}

	@Test
	public void isEqualTest(){
		assertTrue(berlinCart.isEqual(berlin));
		assertTrue(berlinSphere.isEqual(berlinCart));
		assertTrue(berlinSphere.isEqual(berlin));
		
		assertTrue(tokioCart.isEqual(tokio));
		assertTrue(tokioSphere.isEqual(tokioCart));
		assertTrue(tokioSphere.isEqual(tokio));
		
		assertTrue(losangelesCart.isEqual(losangeles));
		assertTrue(losangelesSphere.isEqual(losangelesCart));
		assertTrue(losangelesSphere.isEqual(losangeles));
	}
	
	@Test
	public void getDistanceTest(){
		double tokioBerlin = tokio.getDistance(berlin);
		assertEquals("Normal test", 9012.4, tokioBerlin, 0.1);
		assertEquals("Commutativity", tokioBerlin, berlinCart.getDistance(tokio), 0.1);
		assertEquals("Zero distance", 0.0, tokioSphere.getDistance(tokioCart), 0.1);
		assertEquals("Another normal test", 8867.1, tokioCart.getDistance(losangelesCart), 0.1);
	}
}
