package myMath;

public class tester 
{
	public tester() {
	// TODO Auto-generated constructor stub
}
	
	public void addPolynomTester(Polynom this_p , Polynom_able p1 , String polycheck )
	{
		System.out.println("*****In addPolynomTester : ******");
		
		this_p.add(p1);
		
	}
	
	public void addMonomTester(Polynom this_p , Monom mon) 
	{
		System.out.println();
		System.out.println("*****In addMonomTester : ******");
		
		this_p.add(mon);
		System.out.println("The polynom's result after adding the monom " + mon.toString() +" is : "  + this_p.toString());
		
	}
	public void substract_P_Tester(Polynom this_p , Polynom_able p1 )
	{
		System.out.println();
		System.out.println("*****In substract_P_Tester******");
		System.out.print("The polynom's result after subscracting " + p1.toString() + " is : ");
		this_p.substract(p1);
	}
	public void multiply_P_Tester(Polynom this_p , Polynom_able p1)
	{
		System.out.println();
		System.out.println("*****In multiply_P_Tester******");
		System.out.print("The polynom's result after multyplying " + p1.toString() + " is : ");
		this_p.multiply(p1);
	}
	public void equals_Tester(Polynom this_p , Polynom_able p1)
	{
		boolean test ;
		System.out.println();
		System.out.println("*****In equals_Tester******");
		test = this_p.equals(p1);
		if(test == true)
			System.out.println("The two polynoms are equals!!!");
		else
			System.out.println("The two polynoms are NOT equals!!!");

	}
	
	public void isZero_Tester(Polynom this_p)
	{
		System.out.println();
		System.out.println("*****In isZero_Tester******");

		boolean test ;
		test = this_p.isZero();
		if(test == true)
			System.out.println("This polynom is the zero polynom");
		else
			System.out.println("This polynom is NOT the zero polynom");
	}
	public void area_Tester(Polynom this_p , double x0,double x1, double eps)
	{
		System.out.println();
		System.out.println("*****In area_Tester******");
		System.out.println(this_p.area(x0, x1, eps));
	}
	public void root_Tester(Polynom this_p  ,double x0, double x1, double eps)
	{
		System.out.println();
		System.out.println("*****In root_Tester******");
		System.out.println(this_p.root(x0, x1, eps));
	}
	
	public void copy_Tester(Polynom this_p )
	{
		System.out.println();
		System.out.println("*****In copy_Tester******");

		boolean isEqual = false ;
		Polynom_able copyP = new Polynom();
		copyP = this_p.copy() ;
		
		isEqual = copyP.equals(this_p);
		if(isEqual)
		System.out.println("copy_Tester ended without problems");
		else
		System.out.println("copy_Tester ended WITH problems");
	}
	public void derivative_Tester(Polynom this_p )
	{
		System.out.println();
		System.out.println("*****In derivative_Tester******");
		this_p.derivative().toString() ;
	}
	
	
	public void iteretor_Tester(Polynom this_p )
	{
		
	}
	
	public void toString_Tester(Polynom this_p )
	{
		
	}
}
