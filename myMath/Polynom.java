package myMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able
{

	// ********** add your code below ***********
	
	private	ArrayList<Monom> poly = new ArrayList<Monom>() ;
	Iterator<Monom> polyIt = poly.iterator()               ;
	
	
	
	 
	public Polynom()
	{
		
	}
	
	public void add(Polynom_able p1)
	{
         
		/**	 * Add p1 to this Polynom
		 * @param p1
		 */
		
		Iterator <Monom> iteretor_p1 = p1.iteretor() ; 
		Monom mon1;
		while(iteretor_p1.hasNext())
		{
	     mon1=iteretor_p1.next();
	     p1.add(mon1);
		}
		System.out.println(p1);	
	}
	
	
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	
	public void add(Monom m1)
	{
		

		Iterator <Monom> addIt = poly.iterator();
		
		boolean isThere = false                 ;		
		Monom mon                               ;
		
			while(addIt.hasNext())
			{
				mon = addIt.next();
				if(m1.get_power() == mon.get_power() )
				{	
					isThere = true ;
					mon.add(m1);
					break ;
			    }
								
			}
					
			if(isThere == false)
			{
				poly.add(m1);
			}
	 }
	
	
	
	
	public void substract(Polynom_able p1)
	{
		/**
	 * Subtract p1 from this Polynom
	 * @param p1
	 */
		Iterator<Monom> subIt = poly.iterator() ;
		
		flipSign(p1);
		
		while(subIt.hasNext())
		{
			p1.add(subIt.next());
		}
		
		System.out.println(p1.toString() );
	}
	
	
	private void flipSign(Polynom_able p1)
	{		
		Iterator<Monom> fsIt = p1.iteretor() ;
		Monom nmon = new Monom(-1,0) ;

		while(fsIt.hasNext())
		{
			 fsIt.next().multiplication(nmon) ;			
		}
		
	 }
	
	public void multiply(Polynom_able p1)
	{
		/*
	  Multiply this Polynom by p1
		  @param p1  */
	 
        Iterator<Monom>p1It = p1.iteretor();
		Iterator<Monom> multIt = poly.iterator(); ;
		
		Polynom tmpPoly = new Polynom() ;
		Monom p1Mon    = new Monom()    ;
		Monom res = new Monom()         ;
		Monom constMon = new Monom()    ;
        while(p1It.hasNext())
		{
        	constMon = p1It.next() ;
        	p1Mon = constMon       ;
        	multIt = poly.iterator() ;
        	while(multIt.hasNext())
			{
				res = p1Mon.multiplication(multIt.next()) ;
				tmpPoly.add(res);
				
				
			}
			
		}
       System.out.println(tmpPoly.toString());
	}
	
	
//	public boolean equals (Polynom_able p1)
	{
		/**
		 * Test if this Polynom is logically equals to p1.
		 * @param p1
		 * @return true iff this pulynom represents the same function ans p1
		 */
		
		
		//Iterator <Monom> P1It = p1.iteretor();
		Monom_Comperator tmp = new Monom_Comperator() ;
		
	
	
	
	}
	
	public boolean isZero()
	{
		/**
		 * Test if this is the Zero Polynom
		 * @return
		 */
		boolean check = true ;
		Iterator<Monom> izIt = poly.iterator();
		while(izIt.hasNext())
		{
			if(izIt.next().get_coefficient()!=0)
			{
				return false;
			}
		}
		return check ;
	}
	
//	public double root(double x0, double x1, double eps)
//	{
//		/**
//		 * Compute a value x' (x0 <= x'<=x1) for with |f(x')| < eps
//		 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
//		 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
//		 * @param x0 starting point
//		 * @param x1 end point
//		 * @param eps step (positive) value
//		 * @return
//		 */
//	}
//	
	
	/**
	 * create a deep copy of this Polynum
	 * @return 
	 */
	
	public Polynom_able copy()
	{
		Polynom copy = new Polynom()      ;
		
		
		
		Iterator  <Monom> firstIt   =  poly.iterator()      ;
		
		Monom mon                   ;
		while( firstIt.hasNext() )
		{
			mon = firstIt.next() ;
			
			copy.add(mon)  ;	
			           
		}
		
		return copy ;
	}
	
	
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return
	 */
	
	public Polynom_able derivative()
	{
		Polynom p = this ;
		p = Pderivative(p);
		return p ;
	}
	
	
	private Polynom Pderivative(Polynom p)	
	{
		Iterator<Monom> derIt = p.iteretor();
		while(derIt.hasNext())
		{
			derIt.next().derivative();
		}
		return p ;
	}
	
			 public double area(double x0,double x1, double eps)
			{
				/**
				 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
				 * see: https://en.wikipedia.org/wiki/Riemann_integral
				 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
				 */
			
				if(x0==x1)
				{
					return 0;
				}
				double minNum = Math.min(x0, x1);
				double maxNum = Math.max(x0, x1);
				double deltaX = Math.abs(maxNum - minNum)   ;
		
				double res = 0 ;
				for(double i = minNum ; i <= maxNum ; i+=eps )
				{
					if(this.f(i)<=0)
					{
						
					}
					else
					{
						res += this.f(i)*deltaX ;
					
					}
					
				}
			
				return res;
			}
			
		
		

	 double f(double x)
	{
		double result=0;
		Iterator<Monom>itF= poly.iterator();
		while(itF.hasNext())
		{
			result+=itF.next().f(x);
		}
		return result;
	}
	public Iterator <Monom> iteretor()	
	{
		Iterator<Monom> it = poly.iterator() ; 
		
		return it ;
	}
		
@Override
	public String toString() 
	{
	String str = "" ;
	double sign  ;
	
	Iterator <Monom> tsIt  = poly.iterator()  ;
	Iterator <Monom> signIt  = poly.iterator()  ;

	while(tsIt.hasNext())
	{
		sign = signIt.next().get_coefficient() ;
		if(sign > 0 )
			str += "+";
		str += tsIt.next().toString()  ;
	}
		return str ;
	}

public int compare(Monom mon1 , Monom mon2)
{
	if(mon1.get_power() > mon2.get_power())      {return  1; }
	
	if(mon1.get_power() < mon2.get_power())		 {return -1; }
	
	return  0; 
}

	/* **************************************   MAIN ********************** */
	public static void main(String[] args) 
	{
		Monom mon1 = new Monom(2,1);
		Monom mon2 = new Monom(-3.2,4);
		Monom mon3 = new Monom(-2,0);
		Monom mon4 = new Monom(7,8);
//		Monom mon5 = new Monom(9,10);
//		Monom mon6 = new Monom(6,10);
//		Monom mon7 = new Monom(8,12);
		
		
		Polynom poly1 = new Polynom() ;
		Polynom poly2 = new Polynom() ;
		
		poly1.add(mon1); 		
		poly1.add(mon3);
		poly2.add(mon2); 		 
		poly2.add(mon4);
//		poly1.add(mon5);
//		poly2.add(mon6);
//		poly2.add(mon7);
//		
		
		System.out.println(poly1.toString());
		System.out.println(poly2.toString());

System.out.println("**************************");
		System.out.println("is Zero function : " + poly1.isZero());
		System.out.println("**************************");

		System.out.println("mult : ");
		poly1.multiply(poly2);
		
		System.out.println("*********************************");

	System.out.println(poly1.toString());
	System.out.println();
	System.out.println(poly1.area(0,4,0.01));
		System.out.println("**************derivative");
		System.out.println(poly1.derivative());
		System.out.println(poly1.toString());
		
		
	}
}
