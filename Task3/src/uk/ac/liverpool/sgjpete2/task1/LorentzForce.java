package uk.ac.liverpool.sgjpete2.task1;

public class LorentzForce {
	
	static double dTime; //seconds
	static double dAngle; //radians
	static double B; //magnetic field (T)
	static double bendingRadius; //(m)
	
	static double[] x0 = new double[2]; //initial position (m)
	static double[] p0 = new double[2]; //initial momentum Mev/c
	static double[] F = new double[2]; //Force unit vector
	static double[] P = new double[2];//Momentum unit vector
	
	static double[] x1 = new double[2]; //final position (m)
	static double[] p1 = new double[2]; //final momentum Mev/c
	
	static final double q =1.60217662e-19; // elementary charge (Coulombs)
	static final double m =9.10938356e-31; // electron mass (kg)
	static final double m_MeV = 0.511; // electron mass (MeV)
	static final double meV = 5.34428595e-22; //1 Mev to m kg/s
	static final double c = 3e8;
	
	static int Q = -1;
	
	public static void main(String[] args) {
		 getInputs();
		 calculateInitialState();
	     
	     
	     createUnitVectors();
	     System.out.println("Force Unit Vector: x:"+F[0]+", y:"+ F[1]);
	     System.out.println("Momentum Unit Vector: x:"+P[0]+", y:"+ P[1]);
	     
	     System.out.println("Lorentz Factor: "+lorentzFactor());
	     System.out.println("Energy: "+energy());
	     System.out.println("Beta: "+beta());
	     System.out.println("Velocity: "+beta()*c);
	     
	     calculateFinalState();
	}
	
	//TODO Add user input.
	public static void getInputs() {
		 x0[0] = 0;
		 x0[1] = 0;
		 
	     p0[0] = 0.707;
	     p0[1] = 0.707;
		 
	     dTime = 0.5*(6.98e-8);
	     B = 1;
	}
	
	public static void createUnitVectors() {
		double angle = Math.atan2(p0[1], p0[0]);
		F[0] = -Q*Math.sin(angle);
		F[1] = Q*Math.cos(angle);
		
		P[0] = Math.cos(angle);
		P[1] = Math.sin(angle);
	}
	
	public static void calculateInitialState() {
		System.out.println("Initial Conditions:");
		System.out.println("x: "+x0[0]+" y: "+x0[1]+" (m)");
		System.out.println("px: "+p0[0]+" py: "+p0[1]+" (MeV)");
		System.out.println("B: "+B);
		System.out.println("dT: "+dTime);
		System.out.println("------------------------------------");
		
		dAngle = ((q*B)/m)*dTime;
		System.out.println("Change in Angle: "+dAngle);
	    //bendingRadius = meV*getAbsoluteMomentum()/(q*B);
		bendingRadius = 0.001*getAbsoluteMomentum()/(0.3*B);
	    System.out.println("Initial P: " + getAbsoluteMomentum()+" MeV");
	    System.out.println("Bending Radius: " + bendingRadius+ " m");
	}
	
	public static void calculateFinalState() {
		System.out.println("------------------------");
		//Calculate final position
		x1[0] = (bendingRadius - bendingRadius*Math.cos(dAngle))*F[0] + (bendingRadius*Math.sin(dAngle))*P[0];
		x1[1] = (bendingRadius - bendingRadius*Math.cos(dAngle))*F[1] + (bendingRadius*Math.sin(dAngle))*P[1];
		System.out.println("Final pos: x: "+(x0[0]+x1[0])+" y: "+(x0[0]+x1[1]));
		
		//Calculate final momentum		
		p1[0] = (lorentzFactor()*m_MeV*beta())*(Math.sin(dAngle)*F[0]+Math.cos(dAngle)*P[0]);
		p1[1] = (lorentzFactor()*m_MeV*beta())*(Math.sin(dAngle)*F[1]+Math.cos(dAngle)*P[1]);
		System.out.println("Final momentum: x: "+(p1[0])+" y: "+(p1[1]));
		
	}
	
	public static double getAbsoluteMomentum() {
		return Math.sqrt(p0[0]*p0[0]+p0[1]*p0[1]);
	}
	
	public static double lorentzFactor() {
		return 1/Math.sqrt(1 - (beta()*beta()));
	}
	
	public static double beta() {
		return getAbsoluteMomentum()/energy();	
	}
	
	public static double energy() {
		return Math.sqrt(m_MeV*m_MeV+getAbsoluteMomentum()*getAbsoluteMomentum());
	}

}
