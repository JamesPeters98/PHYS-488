package uk.ac.liverpool.sgjpete2;

class MathExamples
{
    public static void main (String [] args)
    {
	// Find the square root of a number
	double r = 12.33;
	//R instead of r.
	double ans = Math.sqrt(r);
	System.out.println(" The square root of " + r + "  is = " + ans);

	double area = Math.PI*r*r;
	//Missing "
	System.out.println(" Area of circle with radius " + r + " is = " + area);

	/* The method Atan2(y,x) gives the angle of line from the 
	   origin to (x,y) in the correct quadrant */		
	double x = -1;
	double y = -2;
	//Missing semi colon
	double angle = Math.atan2(y, x);
	// Math.sqrt(x*x+y*y) would work as well
	double length = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

	// returns angle in RADIANS in the range -PI < angle <PI
	System.out.println(" Angle = " + angle + " rad, or "
			   + angle*180/Math.PI + " degrees");
	System.out.println(" Length = " + length);

	// convert back
	System.out.println(" (x,y) = ("
			   + (length*Math.sin(angle)) + ", "
			   + (length*Math.cos(angle)) + ")");
    }
}
