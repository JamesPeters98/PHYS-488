package uk.ac.liverpool.sgjpete2;

import java.io.*;

class RunSimulation
{
    //This code allows for a single particle to be tracked through electromagnetic fields.
    // It currently allows for Euler's method to be applied only. 
    
    public static void main (String [] args ) throws IOException
    {
        //create a single initial particle to track
        double[] x0 = {0.,0.,0.,0.}; // position (m)
        double[] p0 = {10.,0.,0.}; // Momentum (MeV/c)
        Particle p = new Particle("electron", x0, p0, 0);
        
        
        for(int i = 1; i <= 3; i++) {
	        // create an instance of the particle tracker
	        // usage: particleTracker(particle, time to track (s), number of time steps)
	        particleTracker pt = new particleTracker(p, 10*(7e-10), (int) Math.pow(10, i));
	        
	        // run the simulation and get the particle tracking data
	        Particle p1 = pt.track();
	        
	        // save tracking data to disk
	        DumpXYZ("output_"+i+"_RK4.csv", p1);
        }
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
    
}
