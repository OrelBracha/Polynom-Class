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
	
	
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom()
	{
		this.set_coefficient(0);
		this.set_power(0);
	}
	public Monom(Monom ot) 
	{	
		this(ot.get_coefficient(), ot.get_power());
	}
	
	// ***************** add your code below **********************
	
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
	public Monom multiplication (Monom mon )
	{
		Monom res = new Monom() ;
		res._power =  this._power + mon._power             ;
		res._coefficient = this._coefficient  *   mon._coefficient         ;
		return res ;                                    
	}
	
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
	
	private boolean legalMonom (Monom mon1 , Monom mon2)
	{
		if (mon1._power == mon2._power)   { return true  ;}
	    
		else                              { return false ;}
	}
	
	
	private int return_Power(int power)     
	{
		if      (power< 0)      {return -1 ;}
		else if (power == 0 )   {return  0 ;}
	
		power-- ;
		return power ;
	}
	
	private double return_Coefficient()
	{
		double der_coefficient  = this._coefficient;
		
		if(der_coefficient == 0) {return 0 ;} 
		
		der_coefficient *= this._power ; 
		return der_coefficient ;
		
		
	}
	
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	
	private void set_power(int p) 
	{
		this._power = p;
	}
	
	private double _coefficient; 
	
	private int _power; 




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









