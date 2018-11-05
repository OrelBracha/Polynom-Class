package myMath;
import java.util.ArrayList;
                     
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{
	
	
	/**
	 * 
	 * @param a - double that will be the coefficient of the Monom
	 *
	 * @param b - double that will be the coefficient of the Monom
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * This is a default constructor of the class Monom
	 */
	public Monom()
	{
		this.set_coefficient(0);
		this.set_power(0);
	}
	/**
	 * 
	 * @param ot - A copy constructor, receives Monom and changes this values
	 */
	public Monom(Monom ot) 
	{	
		this(ot.get_coefficient(), ot.get_power());
	}
	
	// ***************** add your code below **********************
	/**
	 * 
	 * @param x - a parameter received by the user 
	 * @return In this function we return the value of the Monom when X is the input that we get from the user
	 */
	public double f(double x) 
	{
      x = Math.pow( x , this._power )  ;
      x *= this._coefficient           ;
      return x                         ;
	}
	public double get_coefficient() {
		return _coefficient;
	}
	public int get_power() {
		return _power;
	}
	/**
	 * Derivative is the function responsible for calculate the value of the class's Monom 
	 * derivative. We are using math laws in order to get back the requested value.
	 */
	public void derivative()
	{
		
		Double der_coefficient  = return_Coefficient()  ; 
		int der_power ;
		if(this._power == 0)
		{
			der_power = 0 ;
			
		}
		der_power = return_Power(this._power) ;
		this._coefficient =der_coefficient ;
		this._power = der_power;
		
		
	}
	
	
	
	@Override
	/**
	 * Here we will print out for the user watching the console the Monom Received by him. 
	 * The user will be able to watch the Monom's printings after changes.
	 */
	public String toString() 
	{
		if(_coefficient==0)
		{
			return "" ;
		}
		
		if(_power==0)
		{
			if(_coefficient==0)
			{
			return "";
			}
			if(_coefficient==1)
			{
				return "" ;
			}
			if(_coefficient %1 == 0)
			{
				return (int) _coefficient + "" ;
			}
			return _coefficient+"";
		}
		
		if(_power==1)
		{
			if(_coefficient==1)
			{
				return "X" ;
			}
			if(_coefficient %1 == 0)
			{
				return (int) _coefficient + "X" ;
			}
			return  _coefficient + "X";
		}
		if(_coefficient %1 == 0)
		{
			return (int) _coefficient + "X^" + _power ;
		}
		return _coefficient + "X^" + _power  ;
	}
	/**
	 * 
	 * @param mon - The Monom called mon received by the user - will be used to multiplication with the class's Monom
	 * @return - The result of the multiplication done by math laws will be saved in a new Monom created in the function
	 *           called 'res'
	 */
	public Monom multiplication (Monom mon )
	{
		Monom res = new Monom() ;
		res._power =  this._power + mon._power             ;
		res._coefficient = this._coefficient  *   mon._coefficient         ;
		return res ;                                    
	}
	/**
	 * 
	 * @param mon - Monom mon received by the user will be added to the class's Monom, so that we add one to another in such as way that  
	 *              only if the powers are same - we are combining together the coefficients.
	 * @return - as well as other functions , here we also save the result of the adding action in another mono called 'mon_res'
	 */
	public Monom add(Monom mon)
	{
		if(!legalMonom(this , mon))   
		{
			System.out.println("The powers are different so  this is not a Monom adding!!! ");
		}
		
		Monom mon_res = this ;
		mon_res._coefficient = this._coefficient + mon._coefficient; 
		return mon_res;
	}
	/**
	 * 
	 * @param mon - same action will take place here with Monom Mon as in the add function
	 * 
	 * @return mon_res Monom will be sent back, with the result of the substruct action
	 */
	public Monom substruct(Monom mon)
	{
		if(!legalMonom(this , mon))   
		{
			System.out.println("The powers are different so  this is not a Monom adding!!! ");
		}
		
		Monom mon_res = this ;
		mon_res._coefficient = this._coefficient - mon._coefficient; 
		return mon_res;
	}
	//****************** Private Methods and Data *****************
	/**
	 * 
	 * @param mon1 - We are getting mon1 as the first Monom along with his power
	 * @param mon2 - We are getting mon2 as the second Monom along with his power
	 * @return - this boolean method is checking if the powers of those two monoms are equal. if yes, true. if no, false.
	 */
	private boolean legalMonom (Monom mon1 , Monom mon2)
	{
		if (mon1._power == mon2._power)   { return true  ;}
	    
		else                              { return false ;}
	}
	
	/**
	 * 
	 * @param power
	 * @return this method is used for derivative function, here we are getting a power and depending on the value,
	 * we return as a result new power after mathematical derivative action
	 */
	private int return_Power(int power)     
	{
		if      (power< 0)      {return -1 ;}
		else if (power == 0 )   {return  0 ;}
	
		power-- ;
		return power ;
	}
	/**
	 * this method is used for derivative function, here we are getting a coefficient which is the class Monom's coefficient and depending on the value,
	 * we return as a result new coefficient after mathematical derivative action
	 * @return
	 */
	private double return_Coefficient()
	{
		double der_coefficient  = this._coefficient;
		
		if(der_coefficient == 0) {return 0 ;} 
		
		der_coefficient *= this._power ; 
		return der_coefficient ;
		
		
	}
	/**
	 * 
	 * @param a - The variable used to be the setter of the Monom's coefficient
	 */
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	/**
	 * 
	 * @param p - The variable used to be the setter of the Monom's power
	 */
	private void set_power(int p) 
	{
		this._power = p;
	}
	
	private double _coefficient; // The basic variables of Monom's class, coefficient and power
	
	private int _power; // The basic variables of Monom's class, coefficient and power



/**
 * 
 * @param args - The main function of the class , includes different testers of the functions above
 */
	public static void main(String[] args) 
	{
		ArrayList<Monom> monom_list= new ArrayList() ;
		Monom mon1 = new Monom(3.2 , 4);
		Monom mon2 = new Monom(2.3 , 4);
		Monom mon3 = new Monom(5.1 , 2);
		monom_list.add(0, mon1);
		monom_list.add(1, mon2);
		monom_list.add(2, mon3);
//		System.out.println("x is : "+mon1.f(3));
		mon1.derivative();
		System.out.println(mon1);
	}
}














