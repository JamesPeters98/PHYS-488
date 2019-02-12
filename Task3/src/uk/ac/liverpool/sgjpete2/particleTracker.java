package uk.ac.liverpool.sgjpete2;

/*
Contains Euler and Runge-Kutta 4th order integrator methods
 */
public class particleTracker

{
    private double T;
    private double dt;
    private int steps;
    private String RKmethod;
    private Particle input;
    private Particle output;
    //Constants
    static final double c = 2.998e8; // speed of light in m/s

    public particleTracker(Particle particle, String method, double Tmax, int N)
    {
        input=particle;
        RKmethod=method;
        T=Tmax;
        steps=N;
        dt=Tmax/steps;            
    }

    public Particle track()
    {
        output = new Particle(input.getType(),input.getPosition(),input.getMomentum(),steps);           
        for(int n=0;n<steps;n++){
            propogateParticle();
        }
        return output;
    }
    
    public double[] Bfield(double X)
    {
        double [] field=new double[3];
        //complete this for Task 2.1
        return field;
    }
    
    public void propogateParticle()
    {
        
        double[] Yi=output.getLastState(); //previous state of particle (t,x,y,z,px,py,pz)
        double[] Yf=new double[7];
        double q =input.getq();
        double[] Efield={0,0,0}; // V/m
        double[] Bfield={0,1,0}; // T
        
        if(RKmethod=="RK4"){
            /*double Etot=input.getLastE();
            
            double[] k1 = {c*Yi[3]/Etot,c*Yi[4]/Etot,c*Yi[5]/Etot};
            double[] l1 = {q*(Efield[0]+k1[1]*Bfield[2]-k1[2]*Bfield[1]),
                                q*(Efield[1]+k1[2]*Bfield[0]-k1[0]*Bfield[2]),
                                    q*(Efield[2]+k1[0]*Bfield[1]-k1[1]*Bfield[0])};
            
            double[] Y1={Yi[0]+dT/2*k1[0],Yi[1]+dT/2*k1[1],Yi[2]+dT/2*k1[2],Yi[3]+dT/2*l1[0],Yi[4]+dT/2*l1[1],Yi[5]+dT/2*l1[2]};
            Etot=Math.sqrt(particle.E0^2+Y1[3]^2+Y1[4]^2+Y1[5]^2);
            Efield= world.getE(Y1[0],Y1[1],Y1[2]);
            Bfield= world.getB(Y1[0],Y1[1],Y1[2]);
            double[] k2 = {c*Y1[3]/Etot,c*Y1[4]/Etot,c*Y1[5]/Etot};
            double[] l2 = {q*(Efield[0]+k2[1]*Bfield[2]-k2[2]*Bfield[1]),
                                q*(Efield[1]+k2[2]*Bfield[0]-k2[0]*Bfield[2]),
                                    q*(Efield[2]+k2[0]*Bfield[1]-k2[1]*Bfield[0])};
                                    
            double[] Y2={Yi[0]+dT/2*k2[0],Yi[1]+dT/2*k2[1],Yi[2]+dT/2*k2[2],Yi[3]+dT/2*l2[0],Yi[4]+dT/2*l2[1],Yi[5]+dT/2*l2[2]};
            Etot=Math.sqrt(particle.E0^2+Y2[3]^2+Y2[4]^2+Y2[5]^2);
            Efield= world.getE(Y2[0],Y2[1],Y2[2]);
            Bfield= world.getB(Y2[0],Y2[1],Y2[2]);
            double[] k3 = {c*Y2[3]/Etot,c*Y2[4]/Etot,c*Y2[5]/Etot};
            double[] l3 = {q*(Efield[0]+k3[1]*Bfield[2]-k3[2]*Bfield[1]),
                                q*(Efield[1]+k3[2]*Bfield[0]-k3[0]*Bfield[2]),
                                    q*(Efield[2]+k3[0]*Bfield[1]-k3[1]*Bfield[0])};
            
            double[] Y3={Yi[0]+dT*k3[0],Yi[1]+dT*k3[1],Yi[2]+dT*k3[2],Yi[3]+dT*l3[0],Yi[4]+dT*l3[1],Yi[5]+dT*l3[2]};
            Etot=Math.sqrt(particle.E0^2+Y3[3]^2+Y3[4]^2+Y3[5]^2);
            Efield= world.getE(Y3[0],Y3[1],Y3[2]);
            Bfield= world.getB(Y3[0],Y3[1],Y3[2]);
            double[] k3 = {c*Y3[3]/Etot,c*Y3[4]/Etot,c*Y3[5]/Etot};
            double[] l3 = {q*(Efield[0]+k3[1]*Bfield[2]-k3[2]*Bfield[1]),
                                q*(Efield[1]+k3[2]*Bfield[0]-k3[0]*Bfield[2]),
                                    q*(Efield[2]+k3[0]*Bfield[1]-k3[1]*Bfield[0])};
                                    
            double[] Yf=new double[6];
            for(int i=0;i<3;i++){
                Yf[i]=Yi[i]+dT/6*(k1[i]+2*k2[i]+2*k3[i]+k4[i]);
                Yf[i+3]=Yi[i+3]+dT/6*(l1[i]+2*l2[i]+2*l3[i]+l4[i]);
            }*/
                                    
        }
        else if(RKmethod=="Euler"){
            double Etot=input.getLastE();
            double[] k1 = {c*Yi[4]/Etot,c*Yi[5]/Etot,c*Yi[6]/Etot};
            
            double[] l1 = {1e6/c*(Efield[0]+k1[1]*Bfield[2]-k1[2]*Bfield[1]),
                                1e6/c*(Efield[1]+k1[2]*Bfield[0]-k1[0]*Bfield[2]),
                                    1e6/c*(Efield[2]+k1[0]*Bfield[1]-k1[1]*Bfield[0])};
                                    
            
            Yf[0]=Yi[0]+dt;
            for(int i=1;i<4;i++){
                Yf[i]=Yi[i]+dt*k1[i-1];
                Yf[i+3]=Yi[i+3]+dt*l1[i-1];
            }
            
        }
        output.updateParticle(Yf);    
            
        }                 
        
    }
