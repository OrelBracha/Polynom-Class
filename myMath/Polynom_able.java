package myMath;

import java.util.Iterator;

/**
 * This interface represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,
 * where: a_1, a_2 ... a_n are real numbers 
 * For formal definitions see: https://en.wikipedia.org/wiki/Polynomial
 * 
 * Such polygon has the following functionality:
 * 1. Init:
 * 1.1 Init(String)
 * 1.2 Init() // zero Polygon
 * 1.3 Polynom copy() // deep copy semantics
 * 
 * 2. Math:
 * 2.1 void add(Polygon p) // add p to this Polynon
 * 2.2 void subtract(Polygon p) // subtract p from this Polygon
 * 2.3 void multiply(Polygon p) // multiply this Polygon by p
 * 
 * 3. Utils
 * 3.1 isZero()
 * 3.2 Polynom derivative() // returns a new Polygon of the derivative ("NIGZERET").
 * 3.3 double f(x) // return this Polygon value at p(x) 
 * 3.4 boolean equals(Polygon p) // returns true iff for any x: this.f(x) == p.f(x)
 * 3.5 double root(double x0, double x1, double eps) //  returns f(x2) such that:
 *														(i) x0 smaller or equal than x2   and (ii) {f(x2) smaller than eps
 * 3.6 String toString() // returns a String such that it can be used for init an equal(s) Polygon
 *													
 * 
 * 
 * @author ben-moshe
 *
 */
public interface Polynom_able extends cont_function{
	/**
	 * Add p1 to this Polynom
	 * @param p1 - The polynom that will be used in order to do the add function
	 */
//	public void add(Polynom_able p1);
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom - the monom that will be added to this polynom		
	 */
	public void add(Monom m1);
	/**
	 * Subtract p1 from this Polynom
	 * @param p1 - The Polynom that will be used to make the add action
	 */
	public void substract(Polynom_able p1);
	/**
	 * Multiply this Polynom by p1
	 * @param p1 - The Polynom that will be used to make the substract action
	 */
	public void multiply(Polynom_able p1);
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 - The Polynom that will be used to make the multiply action
	 * @return true iff this pulynom represents the same function ans p1
	 */
	public boolean equals (Polynom_able p1);
	/**
	 * Test if this is the Zero Polynom
	 * @return a boolean answer true or false
	 */
	public boolean isZero();
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return a boolean answer true or false
	 */
//	public double root(double x0, double x1, double eps);
	/**
	 * create a deep copy of this Polynum
	 * @return the function only makes a copy of the Polynom
	 */
	public Polynom_able copy();
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return the function only makes a derivative of the Polynom
	 */
	public Polynom_able derivative();
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
//	public double area(double x0,double x1, double eps);
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return only builds new Iterator
	 */
	public Iterator<Monom> iteretor();
}
