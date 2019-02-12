import java.io.*;

class Particle
{
    // a simple class to store properties of a particle:
    // * momentum four-vector: E, px, py, pz (defines also mass) -- all in MeV
    // * position four-vector: time, x, y, z -- time in s, space in cm
    // * electric charge (in units of elementary charge)
    // The class mostly allows to store and retrieve these values
    //
    // It also provides a few functions for basic calculations:
    // * conversions of coordinates (GetTheta, GetPhi, GetThetaPos, GetPhiPos ...)
    // * calculate lab-frame distance between two vectors (GetDistance)
    // * relativistic kinematics (GetBeta, GetGamma)
    // * straight-line propagation by a given time-step (Propagate)
    // * energy loss by a defined amount (ReduceEnergy)
    // * application of small rotation to momentum to simulate multiple scattering (ApplySmallRotation)
    
    //Constants
    static final double c = 2.998e8; // speed of light in m/s
    static final double qe =1.60217662e-19; // elementary charge (Coulombs)
    //Particle specific constants
    private String ptype; //type of particle
    private double E0; //rest mass (MeV/c^2)
    private double charge; //particle charge (Coulombs)
    //Particle start parameters
    private double [][] position;
    private double [][] momentum;
    private int step; //how many steps the partcle has made
    
    
    public Particle(String particleType, double[] position0, double[] momentum0)
    {
        position=new double[1][4];
        momentum=new double[1][3];
        step=0;
        position[0] = position0;
        momentum[0] = momentum0;
        ptype=particleType;
        type(particleType);        
       }
       
    public Particle(String particleType, double [] position0, double [] momentum0,int maxSteps)
    {
        position=new double[maxSteps+1][4];
        momentum=new double[maxSteps+1][3];
        step=0;
        position[0]=position0;
        momentum[0]=momentum0;
        ptype=particleType;
        type(particleType);                   
    }  
       
    public void type(String particleType)
    {
        
        switch (particleType){
            case "electron":
                E0=0.51099895;
                charge=-qe;
                break;
            case "proton":
                E0=938.27231;
                charge=qe;
                break;
            }
    }
        
    public void updateParticle(double[] state)
    {
        step=step+1;
        for(int i=0;i<4;i++){
            position[step][i]=state[i];
        }
        for(int i=0;i<3;i++){
            momentum[step][i]=state[i+4];
        }
    }
    
    public double[] getState(int i)
       {
           double[] Y=new double[7];
           for(int j=0;j<4;j++){
            Y[j]=position[i][j];
            }
           for(int j=0;j<3;j++){
            Y[j+4]=momentum[i][j];
            }
           return Y;
       }
       
    public double[] getLastState()
       {
           double[] Y=new double[7];
           for(int j=0;j<4;j++){
            Y[j]=position[step][j];
            }
           for(int j=0;j<3;j++){
            Y[j+4]=momentum[step][j];
            }
           return Y;
       } 
    
       
    public double getMomentum(int i)
       {
           double p = 0.;
           for (int j = 0; j < 3; j++) {
               p += momentum[i][j]*momentum[i][j];
            }
           p = Math.sqrt(p);
           return p;
       }    
    public int getSteps(){return step;}
    public double[] getPosition(){return position[0];}
    public double[] getMomentum(){return momentum[0];}
    public String getType(){return ptype;}
    public double getLastE(){return Math.sqrt(E0*E0+momentum[step][0]*momentum[step][0]+momentum[step][1]*momentum[step][1]+momentum[step][2]*momentum[step][2]);}
    public double getLastMomentum(){return getMomentum(step);}
    public double getq(){return charge;}
    public double getE0(){return E0;}

}
    