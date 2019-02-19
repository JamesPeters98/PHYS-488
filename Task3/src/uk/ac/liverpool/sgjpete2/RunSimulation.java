package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Arrays;

class RunSimulation
{
    //This code allows for a single particle to be tracked through electromagnetic fields.
    // It currently allows for Euler's method to be applied only. 
    
    public static void main (String [] args ) throws IOException
    {        
        task1();
        //task2_1();
    }
    
    static void task1() throws IOException {
    	
    	//create a single initial particle to track
        double[] x0 = {0.,0.,0.,0.}; // position (m)
        double[] p0 = {10.,0.,0.}; // Momentum (MeV/c)
        Particle p = new Particle("electron", x0, p0, 0);
        
        int low = 1, high = 5;
        int range = 1 + high - low;
        double eulerXY[][] = new double[range][];
        double rk4XY[][] = new double[range][];
    	
    	for(int i = low; i <= high; i++) {
	        // create an instance of the particle tracker
	        // usage: particleTracker(particle, time to track (s), number of time steps)
    		int pow = (int) Math.pow(10, i);
	        particleTracker pt = new particleTracker(p, (7e-10)/8, pow);
	        
	        // run the simulation and get the particle tracking data
	        Particle pEuler = pt.trackEuler();
	        Particle pRK4 = pt.trackRK4();
	        
	        eulerXY[i-low] = new double[] {pow,pEuler.getLastState()[1],pEuler.getLastState()[2]};
	        rk4XY[i-low] = new double[] {pow,pRK4.getLastState()[1],pRK4.getLastState()[2]};
	        
	        // save tracking data to disk
	        DumpXYZ("outputs/task1/output_Euler_"+pow+".csv", pEuler);
	        DumpXYZ("outputs/task1/output_RK4_"+pow+".csv", pRK4);
    	}
    	
    	System.out.println(Arrays.toString(eulerXY[0]));
    	
        // save tracking data to disk
        DumpXYZ("outputs/task1/output_Euler_FinalStates.csv", eulerXY);
        DumpXYZ("outputs/task1/output_RK4_FinalStates.csv", rk4XY);
    }
    
    static void task2_1() throws IOException {
    	//create a single initial particle to track
        double[] x0 = {0.,0.,0.,0.}; // position (m)
        double[] p0 = {10.,0.,0.}; // Momentum (MeV/c)
        Particle p = new Particle("electron", x0, p0, 0);
    	
    	// create an instance of the particle tracker
        // usage: particleTracker(particle, time to track (s), number of time steps)
        particleTracker pt = new particleTracker(p, (5e-9), 100000);
        pt.setBfieldLimit(0.2, 0.3);
        
        // run the simulation and get the particle tracking data
        Particle p1 = pt.trackRK4();
        
        // save tracking data to disk
        DumpXYZ("outputs/task2.1/output_task2.1.csv", p1);
    }

    static void DumpXYZ(String filename, Particle p) throws IOException
    {
	// write time and x,y,z coordinates to CSV file
        FileWriter file = new FileWriter(filename);     // this creates the file with the given name
        PrintWriter outputFile = new PrintWriter(file); // this sends the output to file1

        // now make a loop to write the contents of each bin to disk, one number at a time
        for (int n = 0; n < p.getSteps(); n++) {
            double[] Y = p.getState(n);
            // comma separated values
            outputFile.println(Y[0] + "," + Y[1] + "," + Y[2] + "," + Y[3]);
        }
        outputFile.close(); // close the output file
        return;
    }
    
    static void DumpXYZ(String filename, double[][] a) throws IOException
    {
	// write time and x,y,z coordinates to CSV file
        FileWriter file = new FileWriter(filename);     // this creates the file with the given name
        PrintWriter outputFile = new PrintWriter(file); // this sends the output to file1

        // now make a loop to write the contents of each bin to disk, one number at a time
        for (int n = 0; n < a.length; n++) {
            // comma separated values
            outputFile.println(a[n][0] + "," + a[n][1] + "," + a[n][2]);        }
        outputFile.close(); // close the output file
        return;
    }
    
}
