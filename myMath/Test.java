package myMath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Test {

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@org.junit.Test
	public void test() {
		Polynom poly1 = new Polynom("2x+x^3")   ;
		System.out.println(poly1.toString());
		Polynom ans = new Polynom("4x+x^3") ;
		Monom mon = new Monom(2,1)   ;

		
		Tester test = new Tester();
		test.addMonomTester(poly1, mon);
		assertEquals(ans, poly1);
		
		
		fail("Not yet implemented");
		
		
	}

}
