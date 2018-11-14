package myMath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonomJunit 
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	
	
	
	@Test
	public void fMonomTest()
	{
		Monom mon1 = new Monom(3,2) ;	
		double actual = mon1.f(5) ;
		double expected = 3*Math.pow(5, 2) ;
		assertTrue(actual==expected);
	}
	
	@Test
	public void gettersTest()
	{
		Monom mon = new Monom(3,2) ;	
		double actualCoefficient = mon.get_coefficient() ;
		double expectedCoefficient = 3 ;
		double actualPower = mon.get_power() ;
		double expectedPower = 2;
		
		assertTrue("The actual and expected coefficients are NOT equal!!", actualCoefficient == expectedCoefficient);
		assertTrue("The actual and expected powers are NOT equal!!", actualPower == expectedPower);
	}
	
	
	@Test
	public void derivativeTest()
	{
		Monom mon = new Monom(3,5) ;
		mon.derivative();
		System.out.println(mon.toString());
		double actualCoefficient = mon.get_coefficient() ;
		double expectedCoefficient = 15 ; 
		double actualPower =mon.get_power();
		double expectedPower = 4 ;
		assertTrue("The actual coefficient after the drivative function is NOT equal to the expected coefficient!!", actualCoefficient == expectedCoefficient);
		assertTrue("The actual power after the drivative function is NOT equal to the expected power!!!!", actualPower == expectedPower);
	}
	@Test
	public void toStringTest()
	{
		Monom mon = new Monom(5,9) ;
		String actualString   = mon.toString() ;
		System.out.println("********in toString*******");
		System.out.println(mon.toString());
		String expectedString ="5X^9" ; //type 'x' as a capital letter
		
		assertTrue("toString function error ",actualString.equals(expectedString));
	}
	
	@Test
	public void multiplicationTest()
	{
	    Monom mon = new Monom(5.7,12);
	    Monom mon1 = new Monom (2,3);
	    Monom res = new Monom ();
	    res = mon.multiplication(mon1);
	    
	    System.out.println(res.toString());
	    double expectedCo = 11.4;
	    double expectedPow = 15;
		assertTrue("The actual coefficient after the multiplication function is NOT equal to the expected coefficient!!", res.get_coefficient() == expectedCo);
		assertTrue("The actual power after the multiplication function is NOT equal to the expected power!!!!", res.get_power() == expectedPow);
  
	}
	
	@Test
	public void addMonomTest() 
	{
		Tester mTest = new Tester() ;
		Monom mon1 = new Monom(3,2) ; 
		Monom mon2 = new Monom(9,2) ; 
		Monom ans = new Monom(12,2) ; 

		mTest.addMonomTester(mon1,mon2);
		assertTrue("the monoms are NOT equal!!!", mon1.get_coefficient() == ans.get_coefficient() || mon1.get_power() == ans.get_power());
	}


	@Test
	public void substructTest()
	{
		System.out.println("********in multiplication*******");
		Monom mon1 = new Monom(3,2) ; 
		Monom mon2 = new Monom(9,2) ; 
		Monom res =  new Monom();
		Monom expected = new Monom(-6,2) ;
		 res = mon1.substruct(mon2)  ;
		System.out.println(res.toString());
		 
		assertTrue("The expected Monom is NOT equal to the substruct of the two monoms", expected.get_coefficient()==res.get_coefficient()||expected.get_power()==res.get_power());
	}
}
