package uk.ac.liverpool.sgjpete2;

//Contains Euler integrator method at ths point in time

public class particleTracker {
	
	 private double dt;
	 private int steps;
	 private Particle input;
	 private Particle output;
	 
	 private double xLow = 0.2, xHigh = 0.3; //(m)
	 private boolean useCustomBField = false;
	
	 //Constants
	 static final double c = 3e8; // speed of light in m/s
	
	 public particleTracker(Particle particle, double Tmax, int N) {
	     input = particle;
	     steps = N+1;    // do one more step to arrive at final position
	     dt = Tmax/N;    // deltaT to arrive at final time
	 }
	
	 public Particle trackEuler() {
	     output = new Particle(input.getType(), input.getPosition(), input.getMomentum(), steps);
	     for(int n=0; n<steps; n++){
	    	 propogateParticle();      // use this for Euler integrator
	     }
	     return output;
	 }
	 
	 
	 public Particle trackRK4() {
		 output = new Particle(input.getType(), input.getPosition(), input.getMomentum(), steps);
		 for(int n=0; n<steps; n++){
			 propogateParticleRK4(); // use this for Runge-Kutta 4th order integrator
		 }
		 return output;
	 }
	 
	 public void setBfieldLimit(double xLow, double xHigh) {
		 this.xLow = xLow;
		 this.xHigh = xHigh;
		 this.useCustomBField = true;
	 }
	 
	 public double[] Bfield(double x, double y, double z) {
		 // define B-field in Tesla at position x, y, z
	     double [] myBfield = new double[3];
	     
	     if((!useCustomBField)||((x>=xLow)&&(x<=xHigh))) {
		     // a constant uniform field
		     myBfield[0] = 0.;
		     myBfield[1] = 0.;
		     myBfield[2] = 1.;
	     } else {
	    	 myBfield[0] = 0.;
		     myBfield[1] = 0.;
		     myBfield[2] = 0.;
	     }
	
	     return myBfield;
	 }
	 
	 public double[] Efield(double x, double y, double z) {
		// define E-field in V/m at position x, y, z
		
	     double [] myEfield = new double[3];
	
		// no E field
		myEfield[0] = 0.;
		myEfield[1] = 0.;
		myEfield[2] = 0.;
	
	     return myEfield;
	 }
	 
	 public void propogateParticle() {
	     double[] Yi = output.getLastState(); //previous state of particle (t,x,y,z,px,py,pz)
	     double q = input.getCharge();
		
		// get the E and B fields at coordinates x, y, z
		double[] myEfield = Efield(Yi[1], Yi[2], Yi[3]);
		double[] myBfield = Bfield(Yi[1], Yi[2], Yi[3]);
		
		double Etot = input.getLastE();
		
		// these are the three componens of the relativistic speed v=c*p/E
		double[] v1 = {c*Yi[4]/Etot,
			       c*Yi[5]/Etot,
			       c*Yi[6]/Etot};
	
		// this is the vectorial Lorentz force: q*E + q*(v x B)
		double[] l1 = {q*c*1e-6*(myEfield[0]+v1[1]*myBfield[2]-v1[2]*myBfield[1]),
			       q*c*1e-6*(myEfield[1]+v1[2]*myBfield[0]-v1[0]*myBfield[2]),
			       q*c*1e-6*(myEfield[2]+v1[0]*myBfield[1]-v1[1]*myBfield[0])};
	
		// make an Euler step and store result to Yf
	     double[] Yf = new double[7];
		// advance time by dt
		Yf[0] = Yi[0]+dt;
		for(int i=1; i<4; i++){
		    // move in space by dt*v
		    Yf[i] = Yi[i] + dt*v1[i-1];
		    // update the momentum according to Lorentz force
		    Yf[i+3] = Yi[i+3] + dt*l1[i-1];
	     }
		// store the new position and momentum
	     output.updateParticle(Yf);    
	 }                 
	
	 public void propogateParticleRK4() {
		double[] Yi = output.getLastState(); //previous state of particle (t,x,y,z,px,py,pz)
	     double q = input.getCharge();
	     double[] a = {0,0.5,0.5,1};
	     double[] b = {1,2,2,1};
	     double[] Yf = new double[7];
	     double[] k1 = new double[7];
	     double[] l1 = new double[7];
	     
		for(int i =0; i < 4; i++) {
		    double[] Yii=new double[7];
		    for(int j=1; j<4; j++){
			// move in space by dt*v
			Yii[j] = Yi[j] + a[i]*dt*l1[j-1];
			// update the momentum according to Lorentz force
			Yii[j+3] = Yi[j+3] + a[i]*dt*l1[j-1];
	         }
	     
		    // get the E and B fields at coordinates x, y, z
		    double[] myEfield = Efield(Yii[1], Yii[2], Yii[3]);
		    double[] myBfield = Bfield(Yii[1], Yii[2], Yii[3]);
		    //calculate total energy of particle
		    double E0 = input.getMass();
		    double Etot = input.getLastE();
		    
		    // these are the three componens of the relativistic speed v=c*p/E
		    k1[0] = c*Yii[4]/Etot;
		    k1[1] = c*Yii[5]/Etot;
		    k1[2] = c*Yii[6]/Etot;
		    l1[0] = q*c*1e-6*(myEfield[0]+k1[1]*myBfield[2]-k1[2]*myBfield[1]);
		    l1[1] = q*c*1e-6*(myEfield[1]+k1[2]*myBfield[0]-k1[0]*myBfield[2]);
		    l1[2] = q*c*1e-6*(myEfield[2]+k1[0]*myBfield[1]-k1[1]*myBfield[0]);
		    for(int j=1; j<4; j++){
			// move in space by dt*v
			Yf[j] = Yf[j] + b[i]*dt*k1[j-1]/6;
			// update the momentum according to Lorentz force
			Yf[j+3] = Yf[j+3] + b[i]*dt*l1[j-1]/6;
		    }
		    
		}
		//add change of state to initial state
		Yf[0]=Yi[0]+dt;
		
		for(int i=0;i<7;i++){
		    Yf[i]=Yi[i]+Yf[i];
		}
	 // store the new position and momentum
	 output.updateParticle(Yf);              
	
	     
	 }
	 
}