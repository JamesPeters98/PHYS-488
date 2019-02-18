package uk.ac.liverpool.sgjpete2;

public class Particle
{
    // a simple class to store properties of a particle
    
    //Particle specific constants
    private String ptype; //type of particle
    private double m0; //rest mass (MeV/c^2)
    private int charge; //particle charge (in units of elementary charge)
    //Particle start parameters
    private double [][] position;
    private double [][] momentum;
    private int step; //how many steps the partcle has made
    
    
    public Particle(String particleType, double [] position0, double [] momentum0,int maxSteps)
    {
        position = new double[maxSteps+1][4];
        momentum = new double[maxSteps+1][3];
        step = 0;
        position[0] = position0;
        momentum[0] = momentum0;
        ptype = particleType;
	if (ptype == "electron") {
	    m0=0.51099895;
	    charge =-1;
	} else if (ptype == "proton") {
	    m0=938.27231;
	    charge = 1;
	}
    }
        
    public void updateParticle(double[] state)
    {
        step = step+1;
        for(int i=0; i<4; i++){
            position[step][i] = state[i];
        }
        for(int i=0; i<3; i++){
            momentum[step][i] = state[i+4];
        }
    }
    
    public double[] getState(int i)
    {
	double[] Y=new double[7];
	for(int j=0; j<4; j++){
            Y[j] = position[i][j];
	}
	for(int j=0;j<3;j++){
            Y[j+4] = momentum[i][j];
	}
	return Y;
    }
       
    public double[] getLastState()
    {
	double[] Y = new double[7];
	for(int j=0; j<4; j++){
            Y[j] = position[step][j];
	}
	for(int j=0;j<3;j++){
            Y[j+4] = momentum[step][j];
	}
	return Y;
    } 
    
    
    public double getMomentum(int step)
    {
	double p = 0.;
	for (int j = 0; j < 3; j++) {
	    p += momentum[step][j]*momentum[step][j];
	}
	p = Math.sqrt(p);
	return p;
    }    

    public int getSteps() {return step;}
    public double[] getPosition() {return position[0];}
    public double[] getMomentum() {return momentum[0];}
    public String getType() {return ptype;}
    public double getLastE()
    {
	return Math.sqrt(m0*m0
			 +momentum[step][0]*momentum[step][0]
			 +momentum[step][1]*momentum[step][1]
			 +momentum[step][2]*momentum[step][2]);
    }

    public double getLastMomentum(){return getMomentum(step);}

    public int getCharge() {return charge;}

    public double getMass() {return m0;}

}
    