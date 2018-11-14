<<<<<<< HEAD
package myMath;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Avihu oshri
 * @author Orel bracha
 *
 */





public class Polynom implements Polynom_able
{
	// ********** add your code below ***********
	private ArrayList<Monom> poly = new ArrayList<Monom>() ;
	Iterator<Monom> polyIt = poly.iterator()               ;
	public Polynom() 
	{
	}

	/**
	 * 
	 * @param str
	 * @param coefficient_s - this variable holds the String type of X's coefficient
	 * @param power_s       - this variable holds the String type of X's power
	 * @param coefficient_d - this variable holds the coefficient of X
	 * @param power_i       - this variable holds the power of X
	 * @param index_X       - the index of the closest 'X' sign
	 * @param index_P       - the index of the closest '^' sign
	 * @param index_S       - the index of the closest sign ('+' or '-' )
	 * @param xFound        - A boolean variable that says if there is 'x' or'X' in str
	 * @param powerFound    - A boolean variable that says if there is '^' in str
	 * @param mon
	 */
	public Polynom(String str) {
		String coefficient_s ; //this variable holds the String type of X's coefficient
		String power_s       ; //this variable holds the String type of X's power
		double coefficient_d ; //this variable holds the coefficient of X
		int power_i         ;  //this variable holds the power of X
		int index_X     = 0 ;  //the index of the closest 'X' sign
		int index_P     = 0 ;  //the index of the closest '^' sign
		boolean xFound     = false ;
		boolean powerFound = false ;
		Monom mon ;
		str=str.replaceAll(" ", "");

		while(str.length() != 0 )
		{
			int index_S;
			if(str.contains("X") || str.contains("x") ) 
			{
				xFound = true ; 
				if(str.contains("X"))   
				{index_X = str.indexOf('X');}
				if(str.contains("x"))      
				{index_X = str.indexOf('x');}
				if(str.contains("^"))
				{
					if(str.charAt(index_X+1) == '^')

					{
						powerFound = true ;
						index_P = str.indexOf("^");
					}
				}
			}
			if(xFound && !powerFound)  
			{
				if(str.charAt(0) == '+' && (str.charAt(1) == 'x' || str.charAt(1) == 'X'))
				{
					coefficient_s = str.substring(1, index_X) ;
					coefficient_d = 1 ;
					str = str.substring(index_P   , str.length()) ;
					mon = new Monom(coefficient_d , 1);
					poly.add(mon);
				}
				else if(str.charAt(0) == '-' && (str.charAt(1) == 'x' || str.charAt(1) == 'X'))
				{
					
					coefficient_d = -1 ;
					str = str.substring(index_X+1   , str.length()) ;
					mon = new Monom(coefficient_d , 1);
					poly.add(mon);
				}
				else
				{
				coefficient_s = str.substring(0, index_X) ;
				if(index_X == 0)   {coefficient_d= 1 ; }
				else               {coefficient_d = Double.parseDouble(coefficient_s);}
				mon = new Monom(coefficient_d , 1);
				str = str.substring(index_X + 1 );
				poly.add(mon);
				}
			}
			else if(xFound && powerFound)
			{
				if(index_X == 0)       {coefficient_d = 1 ;}
				else                   
				{
					if(str.charAt(0) == '+' && (str.charAt(1) == 'x' || str.charAt(1) == 'X'))
					{
						coefficient_s = str.substring(1, index_X) ;
						coefficient_d = 1 ;
					}
					else if(str.charAt(0) == '-' && (str.charAt(1) == 'x' || str.charAt(1) == 'X'))
					{
						coefficient_s = str.substring(1, index_X) ;
						coefficient_d = -1 ;
					}
					else
					{
						coefficient_s = str.substring(0, index_X) ;
						coefficient_d = Double.parseDouble(coefficient_s);
					}
				}
				str = str.substring(index_P   , str.length()) ; 
				index_P = 0 ;
				if(str.contains("+") && str.contains("-")) 
				{
					int index_plus = str.indexOf("+" ) ; //the index of the closest '+' sign
					int index_minus = str.indexOf("-") ;
					if(index_minus < index_plus )         { index_S = index_minus           ;}
					else                                  {  index_S = index_plus            ;}
				}
				else if(str.contains("+") && !str.contains("-")) 
				{
					int index_plus = str.indexOf("+" ) ; //the index of the closest '+' sign
					index_S = index_plus  ;                 
				}
				else if(!str.contains("+") && !str.contains("-"))
				{
					power_s = str.substring(index_P +1, str.length());
					power_i = Integer.parseInt(power_s);
					mon = new Monom (coefficient_d , power_i ) ;
					poly.add(mon);
					break ;
				}
				else  
				{
					index_S = str.indexOf("-" ) ; //the index of the closest '-' sign
				}
				power_s = str.substring(index_P +1, index_S);
				power_i = Integer.parseInt(power_s);
				str = str.substring(index_S);
				mon = new Monom(coefficient_d , power_i);
				poly.add(mon);
			}
			else if(!xFound && !powerFound && str.length()>0)
			{
				coefficient_d = Double.parseDouble(str);
				mon = new Monom(coefficient_d , 0);
				poly.add(mon);
				break ;
			}
			xFound = false ;
			powerFound = false ;

		}
	}
	/**  * Add p1 to this Polynom
	 * @param p1
	 * @param mon1 holds the next monom in the polynom array
	 * @param addIt iterator that points on this polynom 
	 */
	public void add(Polynom_able p1)
	{
		
		Iterator<Monom> addIt = p1.iteretor() ;
		Monom mon1;
		while(addIt.hasNext())
		{
			mon1=addIt.next();
			this.add(mon1);
		}
		 
	}

	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom that the function receive
	 * @param equal - represent a boolean flag that says if the power of this monom and m1 monom
	 * 
	 */
	public void add(Monom m1)
	{
		
		Iterator <Monom> addIt = poly.iterator();
		boolean equal = false                    ;  
		Monom mon                                ;
		while(addIt.hasNext())
		{
			mon = addIt.next();
			if(m1.get_power() == mon.get_power() )
			{ 
				equal = true ;
				mon.add(m1);
				break ;
			}
		}
		if(equal == false)
		{
			poly.add(m1);
		} 

	}


	/**
	 * Subtract p1 from this Polynom using flipSign (private function)
	 * @param p1 - Polynom_able variable type that the function reciving  
	 * @param subIt - iterator that points on this polynom
	 */

	public void substract(Polynom_able p1)
	{
		Polynom_able pCopy = new Polynom() ;
		pCopy = p1.copy() ;
//		Iterator<Monom> subIt = p1.iteretor() ;

		Iterator<Monom> subIt = pCopy.iteretor() ;
		Monom mon ;
		flipSign(pCopy);
//		flipSign(p1);
		while(subIt.hasNext())
		{
			mon = subIt.next();
			this.add(mon);
		}
	}

	
	
	/** * this function flip the p1 polynom signs
	 * 
	 * @param p1   - Polynom_able variable type that the function reciving 
	 * @param fsIt - iterator that points on p1 polynom
	 * @param nmon - represent a negative monom
	 */
	private void flipSign(Polynom_able p1)
	{  
		Iterator<Monom> fsIt = p1.iteretor() ;
		Monom nmon = new Monom(-1,0) ;
		while(fsIt.hasNext())
		{
			fsIt.next().multiplication(nmon) ;   
		}
	}

	
	
	
	/**
 		Multiply this Polynom by p1
  		@param p1        The polynom that the function receive 
  		@param p1It      iterator variable that points on p1's arrayList
  		@param multIt    iterator variable that points on this arrayList
  		@param copyPoly  a variable that holds the same Monoms like this arrayList
  		@param copyIt    iterator variable that points on copyPoly's arrayList
  		@param copyMon   a variable that holds a Monom from this arrayList
  		@param p1Mon     a variable that holds a Monom from p1 arrayList
	 **/
	public void multiply(Polynom_able p1)
	{
		/*
   Multiply this Polynom by p1
    @param p1  */
		
		Iterator<Monom> p1It   = p1.iteretor() ;
		Iterator<Monom> multIt = poly.iterator() ;
		Polynom_able copyPoly  = new Polynom(this.toString())   ;
		Iterator<Monom> copyIt = copyPoly.iteretor() ;
		Monom copyMon ;
		Monom p1Mon   ;
		
		while(multIt.hasNext())
		{
			poly.remove(multIt.next());
			multIt = poly.iterator() ;
		}
		while(copyIt.hasNext())
		{
			copyMon = copyIt.next() ;
			while(p1It.hasNext())
			{
				p1Mon = new Monom(p1It.next()) ;
				this.add(p1Mon.multiplication(copyMon));
			}
			p1It = p1.iteretor() ;
		}
		
	}


	
	
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 - Polynom_able variable type that the function reciving 
	 * @param p1It - iterator that points on p1 polynom
	 * @param equalsIt - iterator that points on this polynom
	 * @param mon_p1   - monom from p1 polynom
	 * @param mon_this - monom from this polynom
	 * @param co_this  - coefficien of this polynom
	 * @param co_p1    - coefficien of p1 polynom
	 * @param po_this  - power of this polynom
	 * @param po_p1    - power of p1 polynom
	 * @return true iff this polynom represents the same function ans p1
	 * 
	 */
	public boolean equals (Polynom_able p1)
	{
		Iterator <Monom> p1It     = p1.iteretor();
		Iterator <Monom> equalsIt = poly.iterator();
		Monom mon_this , mon_p1 ;
		double co_this;
		double co_p1  ;
		int po_this   ;
		int po_p1     ;

		int p1_size       = 0 ;
		int this_size = 0 ;

		if(p1.isZero() && this.isZero())   {return true;  }

		while(p1It.hasNext())
		{
			p1_size++ ;
			p1It.next() ;

		}
		while(equalsIt.hasNext())
		{
			this_size++     ; 
			equalsIt.next() ;
		}

		if(p1_size != this_size)          {return false ;}

		p1It     = p1.iteretor();
		equalsIt = poly.iterator();

		while(equalsIt.hasNext())
		{mon_this = equalsIt.next() ;
		mon_p1    = p1It.next()     ;

		co_this = mon_this.get_coefficient() ;
		co_p1   = mon_p1.get_coefficient()   ;
		po_this = mon_this.get_power()       ;
		po_p1   = mon_p1.get_power()         ;

		if(co_this != co_p1 )         {return false ; }
		if(po_this != po_p1)          {return false ; }	   
		}
		return true ;
	}




	/**
	 * Test if this is the Zero Polynom
	 * @param check - boolean variable 
	 * @return true/false if the polynom is zero or not
	 */
	public  boolean isZero()
	{
		boolean check = true ;
		Iterator<Monom> izIt = poly.iterator();

		while(izIt.hasNext())
		{
			if(izIt.next().get_coefficient()!=0)       {return false;}
		}
		return check ;
	}

	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @param x0 - first value on the X axis
	 * @param x1 - second value on the X axis
	 * @param eps small value for accuracy level 
	 * @param res
	 * @return the approximated area above the X-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0,double x1, double eps)
	{ 
		double left = x0 ;
		double sum  = 0  ;
		double  n   =(int)((x1-x0)*(f(x1)-f(x0)))/eps;
		double dx   =(x1-x0)/n;
		
		for( int i=0 ; i<n ; i++ )
		{
			sum += f(left)*dx ;
			left += dx        ;
		}
		return Math.abs(sum) ; // Area is always a positive value

	}
	
	private static int binlog( int bits ) // returns 0 for bits=0
	{
		int log = 0;
		if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
		if( bits >= 256 ) { bits >>>= 8; log += 8; }
		if( bits >= 16  ) { bits >>>= 4; log += 4; }
		if( bits >= 4   ) { bits >>>= 2; log += 2; }
		return log + ( bits >>> 1 );
	}
	/**
	 * Compute a value x' (x0 <= x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * * (i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param smaller_val starting point
	 * @param bigger_val end point
	 * @param eps step (positive) value
	 * @return
	 */
	public double root(double x0, double x1, double eps) 
	{
		double bigger_val  = Math.max( x0, x1 )       ;                //find the bigger  value between x0 to x1
		double smaller_val = Math.min( x0, x1 )       ;                //find the smaller value between x0 to x1
		double middle     = ( bigger_val + smaller_val) /2 ;           //initializing the variable "middle"
		double n=(binlog((int)((bigger_val-smaller_val)/eps))+1);
		if(this.f(x0)*this.f(x1) > 0)
		{
			System.out.println("f("+x0+")*f("+x1+")have to be a negetive number to use root function!!!");
			return -1 ;
		}
		if(f(middle)==0){return middle;}
		for(int i=0;i<n;i++)
		{
			middle=(bigger_val+smaller_val)/2;
			
			if(f(middle) > 0)
			{
				if(f(bigger_val) > 0)        {bigger_val=middle;}
				
				else                         {smaller_val=middle;}
				
			}
			if(f(middle)<0)
			{
				if(f(bigger_val)>0)          {smaller_val=middle;}
				
				else                         {bigger_val=middle; }
			}

			}
		return middle;

		}


	

	/**
	 * create a deep copy of this Polynum
	 * @return 
	 */
	public Polynom_able copy()
	{
		Polynom copy = new Polynom()      ;

		Iterator  <Monom> firstIt   =  poly.iterator()      ;
		Monom mon = new Monom()                  ;
		while( firstIt.hasNext() )
		{
			mon = new Monom(firstIt.next()) ;
			copy.add(mon)  ; 
		}
		return copy ;
	}




	/**
	 *Compute a new Polynom which is the derivative of this Polynom
	 *@param poly_c - holds a copy of this polynom
	 *@param der_poly - the returned variable that holds the derivated polynom
	 *@param polyIt = points on the poly_c polynom
	 *@param monDer - holds the derivated monom
	 *@return the derivated polynom
	 */
	public Polynom_able derivative()
	{
		Polynom_able der_poly =new Polynom(this.toString())      ;
		
		Iterator<Monom> derIt  = der_poly.iteretor();
		Iterator<Monom> polyIt = poly.iterator();

		Monom monDer = new Monom() ;

		while(polyIt.hasNext())
		{
			poly.remove(polyIt.next());
			polyIt = poly.iterator();
		}
		while(derIt.hasNext())
		{
			monDer = derIt.next();

			this.add(Pderivative(monDer));
		}
		return this ;
	}
	/**
	 * 
	 * This function return a derivated monom using the function derivated in Monom class
	 * @param monDer - holds the derivated monom
	 * @return a derivated monom
	 */
	private Monom Pderivative(Monom monDer) 
	{
		monDer.derivative();
		return monDer ;
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
		sign = signIt.next().get_coefficient() ;
		
		if(tsIt.hasNext() && sign > 0)           {str += tsIt.next().toString()  ;}
		
		else                                     {signIt  = poly.iterator()      ;}
	
		while(tsIt.hasNext() && signIt.hasNext())
		{	
			sign = signIt.next().get_coefficient() ;
			if(sign > 0 )
				str += "+";
			str += tsIt.next().toString()  ;
		}
		return str ;
	}
	public double f(double x) 
	{
		Iterator<Monom> fIt = poly.iterator();
		double poly_res = 0 ;
		while(fIt.hasNext())
		{
			poly_res += fIt.next().f(x);
		}
		return poly_res ;
	}

public void returnToOriginal(String str)
{
	 Polynom original = new Polynom(str) ;	
	 Iterator<Monom> orIt = original.iteretor() ;
	 Iterator<Monom> oldIt = poly.iterator() ;
	 Monom mon = new Monom(0,5);
	 while(oldIt.hasNext())
	 {
	 poly.remove(oldIt.next());
	 oldIt = poly.iterator() ;
	 }
	 this.add(original);
	
}
	/*************************** MAIN ***********************/
	public static void main(String[] args) 
	{
		String str1 , str2 ;
		str1 = "0x+2"   ;
		str2 = "x^3+3x" ;
		Polynom poly1 = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5") ;
		Polynom poly2 = new Polynom("-x^2+4") ;
		System.out.println(poly1.toString());
		System.out.println("ROOT : "+ poly1.root(0, 6, 0.01));
		poly1.returnToOriginal("0.2x^4-1.5x^3+3.0x^2-x-5");
		System.out.println("ORIGINAL : "+poly1.toString());
//		Monom mon = new Monom(2,3);
//		System.out.println(poly1.toString());     
//		System.out.println(poly1.equals(poly2));
//		Tester test = new Tester( );
		
//		test.isZero_Tester(poly1)              ;
//
//		System.out.println(poly1.area(0, 4, 0.01));
//
//		test.addPolynomTester(poly1, poly2, "x^3+5x-2");
//		System.out.println("after add : " + poly1.toString());
//		poly1.returnToOriginal(str1);
//		System.out.println("after returnToOriginal : " + poly1.toString());
////		poly1.returnToOriginal(str, poly);
////		test.multiply_P_Tester(poly1, poly2) ;
////		test.addMonomTester(poly1, mon);
//		test.substract_P_Tester(poly1, poly2); 
////		test.equals_Tester(poly1, poly2)     ;
////		test.copy_Tester(poly1);
////		test.derivative_Tester(poly1);
////		System.out.println("ROOT");
////		test.area_Tester(poly1, 0, 2, 0.01);
////		test.root_Tester(poly1,0 ,2 ,0.001 );
////		poly1.area(0, 4, 0.001);

	}
=======
package myMath;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Avihu oshri
 * @author Orel bracha
 *
 */





public class Polynom implements Polynom_able
{
	// ********** add your code below ***********
	private ArrayList<Monom> poly = new ArrayList<Monom>() ;
	Iterator<Monom> polyIt = poly.iterator()               ;
	public Polynom() 
	{
	}

	/**
	 * 
	 * @param str - The String that we receive here
	 * @param coefficient_s - this variable holds the String type of X's coefficient
	 * @param power_s       - this variable holds the String type of X's power
	 * @param coefficient_d - this variable holds the coefficient of X
	 * @param power_i       - this variable holds the power of X
	 * @param index_X       - the index of the closest 'X' sign
	 * @param index_P       - the index of the closest '^' sign
	 * @param index_S       - the index of the closest sign ('+' or '-' )
	 * @param xFound        - A boolean variable that says if there is 'x' or'X' in str
	 * @param powerFound    - A boolean variable that says if there is '^' in str
	 * @param mon - Used as a monom which gets new values
	 */
	public Polynom(String str) {
		String coefficient_s ; //this variable holds the String type of X's coefficient
		String power_s       ; //this variable holds the String type of X's power
		double coefficient_d ; //this variable holds the coefficient of X
		int power_i         ;  //this variable holds the power of X
		int index_X     = 0 ;  //the index of the closest 'X' sign
		int index_P     = 0 ;  //the index of the closest '^' sign
		boolean xFound     = false ;
		boolean powerFound = false ;
		Monom mon ;
		str=str.replaceAll(" ", "");

		while(str.length() != 0 )
		{
			int index_S;
			if(str.contains("X") || str.contains("x") ) 
			{
				xFound = true ; 
				if(str.contains("X"))        {index_X = str.indexOf("X");}
				if(str.contains("x"))        {index_X = str.indexOf("x");}
				if(str.contains("^"))
				{
					if(str.charAt(index_X+1) == '^')

					{
						powerFound = true ;
						index_P = str.indexOf("^");
					}
				}
			}
			if(xFound && !powerFound)  
			{
				coefficient_s = str.substring(0, index_X) ;
				if(index_X == 0)   {coefficient_d= 1 ; }
				else               {coefficient_d = Double.parseDouble(coefficient_s);}
				mon = new Monom(coefficient_d , 1);
				str = str.substring(index_X + 1 );
				poly.add(mon);
			}
			else if(xFound && powerFound)
			{
				if(index_X == 0)       {coefficient_d = 1 ;}
				else                   
				{
					coefficient_s = str.substring(0, index_X) ;
					coefficient_d = Double.parseDouble(coefficient_s);
				}
				str = str.substring(index_P   , str.length()) ; 
				index_P = 0 ;
				if(str.contains("+") && str.contains("-")) 
				{
					int index_plus = str.indexOf("+" ) ; //the index of the closest '+' sign
					int index_minus = str.indexOf("-") ;
					if(index_minus < index_plus )         { index_S = index_minus           ;}
					else                                  {  index_S = index_plus            ;}
				}
				else if(str.contains("+") && !str.contains("-")) 
				{
					int index_plus = str.indexOf("+" ) ; //the index of the closest '+' sign
					index_S = index_plus  ;                 
				}
				else if(!str.contains("+") && !str.contains("-"))
				{
					power_s = str.substring(index_P +1, str.length());
					power_i = Integer.parseInt(power_s);
					mon = new Monom (coefficient_d , power_i ) ;
					poly.add(mon);
					break ;
				}
				else  
				{
					index_S = str.indexOf("-" ) ; //the index of the closest '-' sign
				}
				power_s = str.substring(index_P +1, index_S);
				power_i = Integer.parseInt(power_s);
				str = str.substring(index_S);
				mon = new Monom(coefficient_d , power_i);
				poly.add(mon);
			}
			else if(!xFound && !powerFound && str.length()>0)
			{
				coefficient_d = Double.parseDouble(str);
				mon = new Monom(coefficient_d , 0);
				poly.add(mon);
				break ;
			}
			xFound = false ;
			powerFound = false ;

		}
	}
	/**  * Add p1 to this Polynom
	 * @param p1 - The Polynom that wil be used in order to do the add function
	 * @param mon1 holds the next monom in the polynom array
	 * @param addIt iterator that points on this polynom 
	 */
	public void add(Polynom_able p1)
	{
		Polynom_able pCopy = new Polynom();
		pCopy = this.copy() ;
		Iterator<Monom> addIt = p1.iteretor() ;
		Monom mon1;
		while(addIt.hasNext())
		{
			mon1=addIt.next();
			pCopy.add(mon1);
		}
		System.out.println(pCopy); 
	}

	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom that the function receive
	 * @param equal - represent a boolean flag that says if the power of this monom and m1 monom
	 * 
	 */
	public void add(Monom m1)
	{
		Polynom_able pCopy = new Polynom();
		pCopy = this.copy() ;
		Iterator <Monom> addIt = pCopy.iteretor();
		boolean equal = false                    ;  
		Monom mon                                ;
		while(addIt.hasNext())
		{
			mon = addIt.next();
			if(m1.get_power() == mon.get_power() )
			{ 
				equal = true ;
				mon.add(m1);
				break ;
			}
		}
		if(equal == false)
		{
			poly.add(m1);
		} 

	}


	/**
	 * Subtract p1 from this Polynom using flipSign (private function)
	 * @param p1 - Polynom_able variable type that the function reciving  
	 * @param subIt - iterator that points on this polynom
	 */

	public void substract(Polynom_able p1)
	{
		Polynom_able pCopy = new Polynom() ;
		pCopy = p1.copy() ;
		Iterator<Monom> subIt = pCopy.iteretor() ;
		Monom mon ;
		flipSign(pCopy);
		while(subIt.hasNext())
		{
			mon = subIt.next();
			pCopy.add(mon);
		}
		System.out.println(pCopy.toString() );
	}

	
	
	/** * this function flip the p1 polynom signs
	 * 
	 * @param p1   - Polynom_able variable type that the function reciving 
	 * @param fsIt - iterator that points on p1 polynom
	 * @param nmon - represent a negative monom
	 */
	private void flipSign(Polynom_able p1)
	{  
		Iterator<Monom> fsIt = p1.iteretor() ;
		Monom nmon = new Monom(-1,0) ;
		while(fsIt.hasNext())
		{
			fsIt.next().multiplication(nmon) ;   
		}
	}

	
	
	
	/**
 		Multiply this Polynom by p1
  		@param p1  - the polynom that will be used for multiplying
	 **/
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


	
	
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 - Polynom_able variable type that the function reciving 
	 * @param p1It - iterator that points on p1 polynom
	 * @param equalsIt - iterator that points on this polynom
	 * @param mon_p1   - monom from p1 polynom
	 * @param mon_this - monom from this polynom
	 * @param co_this  - coefficien of this polynom
	 * @param co_p1    - coefficien of p1 polynom
	 * @param po_this  - power of this polynom
	 * @param po_p1    - power of p1 polynom
	 * @return true iff this polynom represents the same function ans p1
	 * 
	 */
	public boolean equals (Polynom_able p1)
	{
		Iterator <Monom> p1It     = p1.iteretor();
		Iterator <Monom> equalsIt = poly.iterator();
		Monom mon_this , mon_p1 ;
		double co_this;
		double co_p1  ;
		int po_this   ;
		int po_p1     ;

		int p1_size       = 0 ;
		int this_size = 0 ;

		if(p1.isZero() && this.isZero())   {return true;  }

		while(p1It.hasNext())
		{
			p1_size++ ;
			p1It.next() ;

		}
		while(equalsIt.hasNext())
		{
			this_size++     ; 
			equalsIt.next() ;
		}

		if(p1_size != this_size)          {return false ;}

		p1It     = p1.iteretor();
		equalsIt = poly.iterator();

		while(equalsIt.hasNext())
		{mon_this = equalsIt.next() ;
		mon_p1    = p1It.next()     ;

		co_this = mon_this.get_coefficient() ;
		co_p1   = mon_p1.get_coefficient()   ;
		po_this = mon_this.get_power()       ;
		po_p1   = mon_p1.get_power()         ;

		if(co_this != co_p1 )         {return false ; }
		if(po_this != po_p1)          {return false ; }	   
		}
		return true ;
	}




	/**
	 * Test if this is the Zero Polynom
	 * @param check - boolean variable 
	 * @return true/false if the polynom is zero or not
	 */
	public  boolean isZero()
	{
		boolean check = true ;
		Iterator<Monom> izIt = poly.iterator();

		while(izIt.hasNext())
		{
			if(izIt.next().get_coefficient()!=0)       {return false;}
		}
		return check ;
	}

	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @param x0 - first value on the X axis
	 * @param x1 - second value on the X axis
	 * @param eps small value for accuracy level 
	 * @param res
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0,double x1, double eps)
	{ 
		double left=x0;
		double sum=0;
		double  n=(int)((x1-x0)*(f(x1)-f(x0)))/eps;
		double dx=(x1-x0)/n;
		for(int i=0;i<n;i++){
			sum+=f(left)*dx;
			left=+dx;
		}
		return 	Math.abs(sum) ;

	}
	 static int binlog( int bits ) // returns 0 for bits=0
	{
		int log = 0;
		if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
		if( bits >= 256 ) { bits >>>= 8; log += 8; }
		if( bits >= 16  ) { bits >>>= 4; log += 4; }
		if( bits >= 4   ) { bits >>>= 2; log += 2; }
		return log + ( bits >>> 1 );
	}
	/**
	 * Compute a value x' (x0 <= x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * * (i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param smaller_val starting point
	 * @param bigger_val end point
	 * @param eps step (positive) value
	 * @return
	 */
	public double root(double x0, double x1, double eps) 
	{
		double bigger_val  = Math.max( x0, x1 )       ;                //find the bigger  value between x0 to x1
		double smaller_val = Math.min( x0, x1 )       ;                //find the smaller value between x0 to x1
		double middle     = ( bigger_val + smaller_val) /2 ;           //initializing the variable "middle"
		double n=(binlog((int)((bigger_val-smaller_val)/eps))+1);
		if(this.f(x0)*this.f(x1) > 0)
		{
			System.out.println("f("+x0+")*f("+x1+")have to be a negetive number to use root function!!!");
			return -1 ;
		}
		if(f(middle)==0){return middle;}
		for(int i=0;i<n;i++)
		{middle=(bigger_val+smaller_val)/2;
			if(f(middle)>0)
			{
				if(f(bigger_val)>0)
				{
					bigger_val=middle;
				
				}
					else 
					{
						smaller_val=middle;
						
					}
				
				}
			if(f(middle)<0){
				if(f(bigger_val)>0){
					smaller_val=middle;
				
				}
				else{bigger_val=middle;
			}
			}

			}
		return middle;

		}


	

	/**
	 * create a deep copy of this Polynum
	 * @return 
	 */
	public Polynom_able copy()
	{
		Polynom copy = new Polynom()      ;

		Iterator  <Monom> firstIt   =  poly.iterator()      ;
		Monom mon = new Monom()                  ;
		while( firstIt.hasNext() )
		{
			mon = new Monom(firstIt.next()) ;
			copy.add(mon)  ; 
		}
		return copy ;
	}




	/**
	 *Compute a new Polynom which is the derivative of this Polynom
	 *@param poly_c - holds a copy of this polynom
	 *@param der_poly - the returned variable that holds the derivated polynom
	 *@param polyIt = points on the poly_c polynom
	 *@param monDer - holds the derivated monom
	 *@return the derivated polynom
	 */
	public Polynom_able derivative()
	{
		Polynom_able poly_c =new Polynom()        ;
		Polynom_able der_poly =new Polynom()      ;
		poly_c=this.copy()                        ;
		Iterator<Monom> polyIt = poly_c.iteretor();

		Monom monDer = new Monom() ;

		while(polyIt.hasNext())
		{
			monDer = polyIt.next();

			der_poly.add(Pderivative(monDer));
		}
		this.polyIt = poly.iterator() ;
		System.out.println("The derivative of " + this.toString() + " is : " + der_poly.toString());
		return der_poly ;
	}
	/**
	 * 
	 * This function return a derivated monom using the function derivated in Monom class
	 * @param monDer - holds the derivated monom
	 * @return a derivated monom
	 */
	private Monom Pderivative(Monom monDer) 
	{
		monDer.derivative();
		return monDer ;
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
	private double f(double x) 
	{
		Iterator<Monom> fIt = poly.iterator();
		double poly_res = 0 ;
		while(fIt.hasNext())
		{
			poly_res += fIt.next().f(x);
		}
		return poly_res ;
	}


	/* **************************************   MAIN ********************** */
	public static void main(String[] args) 
	{
		Polynom poly1 = new Polynom("2x-2") ;
		Polynom poly2 = new Polynom("x^3+3x") ;
		Monom mon = new Monom(2,3);
		System.out.println(poly1.equals(poly2));
		tester test = new tester( );
		test.isZero_Tester(poly1)              ;

		

		test.addPolynomTester(poly1, poly2, "2x^3+6x");
		test.multiply_P_Tester(poly1, poly2) ;
		test.addMonomTester(poly1, mon);
		test.substract_P_Tester(poly1, poly2); 
		test.equals_Tester(poly1, poly2)     ;
		test.copy_Tester(poly1);
		test.derivative_Tester(poly1);
		System.out.println("ROOT");
		test.area_Tester(poly1, 0, 2, 0.01);
		test.root_Tester(poly1,0 ,2 ,0.001 );
		poly1.area(0, 4, 0.001);

	}
>>>>>>> 6c1e20595eab8433721e1c88ebfb4a6a9fa58875
}