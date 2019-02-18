package uk.ac.liverpool.sgjpete2.task1;

public class ElectronInMagneticField {
	
	private double dTime; //seconds
	private double dAngle; //radians
	private double B; //magnetic field (T)
	private double bendingRadius; //(m)
	
	private double[] x0 = new double[2]; //initial position (m)
	private double[] p0 = new double[2]; //initial momentum Mev/c
	private double[] F = new double[2]; //Force unit vector
	private double[] P = new double[2];//Momentum unit vector
	
	private double[] x1 = new double[2]; //final position (m)
	private double[] p1 = new double[2]; //final momentum Mev/c
	
	final double q = 1.60217662e-19; // elementary charge (Coulombs)
	final double m = 9.10938356e-31; // electron mass (kg)
	final double m_MeV = 0.511; // electron mass (MeV)
	
	private int Q = -1;
		
	public ElectronInMagneticField(double[] pos, double[] momentum, double dT, double B) {
		x0 = pos;
		p0 = momentum;
		dTime = dT;
		this.B = B;
		
		setup();
		createUnitVectors();
		calculateFinalState();
	}
	
	public void createUnitVectors() {
		double angle = Math.atan2(p0[1], p0[0]);
		F[0] = -Q*Math.sin(angle);
		F[1] = Q*Math.cos(angle);
		
		P[0] = Math.cos(angle);
		P[1] = Math.sin(angle);
	}
	
	public void setup() {
		dAngle = ((q*B)/m)*dTime/lorentzFactor();
		bendingRadius = 0.001*getInitialAbsoluteMomentum()/(0.3*B);
	}
	
	public void calculateFinalState() {
		//Calculate final position
		x1[0] = x0[0] + (bendingRadius - bendingRadius*Math.cos(dAngle))*F[0] + (bendingRadius*Math.sin(dAngle))*P[0] ;
		x1[1] = x0[1] + (bendingRadius - bendingRadius*Math.cos(dAngle))*F[1] + (bendingRadius*Math.sin(dAngle))*P[1];
		
		//Calculate final momentum		
		p1[0] = (lorentzFactor()*m_MeV*beta())*(Math.sin(dAngle)*F[0]+Math.cos(dAngle)*P[0]);
		p1[1] = (lorentzFactor()*m_MeV*beta())*(Math.sin(dAngle)*F[1]+Math.cos(dAngle)*P[1]);
	}
	
	public double getInitialAbsoluteMomentum() {
		return Math.sqrt(p0[0]*p0[0]+p0[1]*p0[1]);
	}
	
	public double getFinalAbsoluteMomentum() {
		return Math.sqrt(p1[0]*p1[0]+p1[1]*p1[1]);
	}
	
	public double lorentzFactor() {
		return 1/Math.sqrt(1 - (beta()*beta()));
	}
	
	public double beta() {
		return getInitialAbsoluteMomentum()/energy();	
	}
	
	public double energy() {
		return Math.sqrt(m_MeV*m_MeV+getInitialAbsoluteMomentum()*getInitialAbsoluteMomentum());
	}

	public double getAngle() {
		return dAngle;
	}

	public double getBendingRadius() {
		return bendingRadius;
	}

	public double[] getInitialPos() {
		return x0;
	}

	public double[] getInitialMomentum() {
		return p0;
	}

	public double[] getFinalPos() {
		return x1;
	}

	public double[] getFinalMomentum() {
		return p1;
	}

}
