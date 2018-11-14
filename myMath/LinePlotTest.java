package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame 
{
	
	double maxValue , minValue ;
	final double EPSYLON = 0.01 ;
    Polynom gPoly = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5") ;
    Polynom dgPoly= new Polynom(gPoly.derivative().toString())   ;
    
    public LinePlotTest() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);

        DataTable data = new DataTable(Double.class, Double.class);

    
      gPoly.returnToOriginal("0.2x^4-1.5x^3+3.0x^2-x-5");
        for (double x = -2.0; x <= 6.0; x+=EPSYLON) 
        {
            double y = gPoly.f(x);
            data.add(x, y)       ;
            		
        }
        DataTable der = new DataTable(Double.class, Double.class);

        XYPlot plot = new XYPlot(data,der);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
        getContentPane().add(new InteractivePanel(plot));
        maxValue  = gPoly.f(-2) ;
        minValue = gPoly.f(-2) ;;
        for (double x = -2.0; x <= 6.0; x+=EPSYLON) 
        {
        	 

        	 if(dgPoly.f(x-EPSYLON) < 0 && dgPoly.f(x+EPSYLON) > 0)
        		if(gPoly.f(x+EPSYLON)>gPoly.f(x))
        		{
        		System.out.println("MIN POINT ---->("+x+","+gPoly.f(x)+")");
        		der.add(x, gPoly.f(x));
        		}
        	
        	 if(dgPoly.f(x-EPSYLON) > 0 && dgPoly.f(x+EPSYLON) < 0)
        	{
//        		maxValue = Math.max(maxValue, gPoly.f(x));
        		if(gPoly.f(x-EPSYLON) < gPoly.f(x))
        		{
        		System.out.println("MAX POINT ---->("+x+","+gPoly.f(x)+")");        		
        		der.add(x, gPoly.f(x));
        		}
        		
        	}
        }
        Color color2 = new Color(1.0f, 0.0f, 0.0f);
        plot.getPointRenderers(der).get(0).setColor(color2);
    }
    
    
    private double area(double x0,double x1, double eps)
	{ 
		double left = x0 ;
		double sum  = 0  ;
		double  n   =(int)((x1-x0)*(gPoly.f(x1)-gPoly.f(x0)))/eps;
		double dx   =(x1-x0)/n;
		
		for( int i=0 ; i<n ; i++ )
		{
			if(gPoly.f(left) <= 0)
			sum += gPoly.f(left)*dx ;
			left += dx        ;
		}
		return Math.abs(sum) ; // Area is always a positive value
	}

    public static void main(String[] args) 
    {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
       System.out.println( "area is :" + frame.area(-2, 6, 0.01));
    }
}