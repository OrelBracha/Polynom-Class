package myMath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomJunit {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void StringToPolynomTest() 
	{
		Polynom p1= new Polynom("2x^2+3x^4+3x+2");
		String str ="2X^2+3X^4+3X+2";
		assertTrue("There is a failure in the StringToPolynom constructor", p1.toString().equals(str));
	
	}
	@Test
	public void addTester()
	{
		Polynom p1= new Polynom("2x^2+3x^4+3x+2");
		Polynom p2= new Polynom("4x^7+23x^4+9x+2");
		
		p1.add(p2);
		assertTrue("Error in add function", p1.toString().equals("2X^2+26X^4+12X+4+4X^7"));
	}
	
	@Test
	public void substructTester()
	{
		Polynom p1= new Polynom("2x^2+4x^5");
		Polynom p2= new Polynom("4x^2+2x^5");		
		p1.substract(p2);
		assertTrue("Error in substruct function", p1.toString().equals("-2X^2+2X^5"));
		
	}
	
	@Test
	public void multiplyTester()
	{
		Polynom p1= new Polynom("2x^2+3x");
		Polynom p2= new Polynom("2x^5+5x");
		p1.multiply(p2);
		assertTrue("Error in multyply function", p1.toString().equals("4X^7+10X^3+6X^6+15X^2"));
	}
	
	@Test
	public void equalsTester()
	{
		Polynom p1= new Polynom("2x^2+3x");
		Polynom p2= new Polynom("2x^2+3x");
		assertTrue("Error in equal function", p1.equals(p2));
	}

	
	@Test
	public  void isZeroTester()
	{
		Polynom poly = new Polynom("0x") ;
		assertTrue("Error in isZero function", poly.isZero());
	}
	
	@Test
	public void areaTester()
	{
		Polynom poly2 = new Polynom("-x^2+4") ;
		assertTrue("" , poly2.area(-4, 0, 0.001) == 5.333833335978293 );	
	}
	
	@Test
	public void rootTester()
	{
		Polynom poly2 = new Polynom("x^2-10") ;
		assertTrue("Error in root function", poly2.root(-4 , 0 , 0.001) == -3.1630859375);
		
	}
	
	@Test
	public void copyTester()
	{
		Polynom p1= new Polynom("2x^2+3x");
		Polynom_able copy ;
		copy = p1.copy() ;
		assertTrue("Error in copy function", p1.equals(copy));
	}
	
	@Test
	public void derivativeTester()
	{
		Polynom poly = new Polynom("2x^6+3x^2+x^4") ;
		poly.derivative() ;
		System.out.println(poly.toString());
		assertTrue("Error in derivative function", poly.toString().equals("12X^5+6X+4X^3"));
	}
	
}
