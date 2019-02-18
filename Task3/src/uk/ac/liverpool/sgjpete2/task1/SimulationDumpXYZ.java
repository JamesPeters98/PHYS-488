package uk.ac.liverpool.sgjpete2.task1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import uk.ac.liverpool.sgjpete2.Particle;

public class SimulationDumpXYZ {
	
    static Scanner keyboard = new Scanner(System.in);
	
	static double[] pos = new double[2];
	static double[] momentum = new double[2];
	static double dT;
	static double B;
	
	static double[][] txy = new double[1000][];
	
	public static void main(String[] args) throws IOException {
		//readInput();
		testInputs();
		for(int i = 0; i<1000; i++) {
			ElectronInMagneticField electron = new ElectronInMagneticField(pos, momentum, i*(dT/1000), B);
			txy[i] = new double[3];
			txy[i][0] = i*(dT/1000);
			txy[i][1] = electron.getFinalPos()[0];
			txy[i][2] = electron.getFinalPos()[1];
		}
		DumpXYZ("Test.csv", txy);
		//printInitialState(electron);
		//printFinalState(electron);
		//printAdditionalInfo(electron);
	}
	
	public static void readInput() {
		System.out.println("Enter the X and then Y position of the Electron:");
		pos[0] = keyboard.nextDouble();
		pos[1] = keyboard.nextDouble();
		System.out.println("Enter the X and then Y momentum of the Electron:");
		momentum[0] = keyboard.nextDouble();
		momentum[1] = keyboard.nextDouble();
		System.out.println("Enter the time interval at which to measure the Electron:");
		dT = keyboard.nextDouble();
		System.out.println("Enter the strength of the magnetic field the Electron is in:");
		B = keyboard.nextDouble();
	}
	
	static void testInputs() {
		pos[0] = 0;
		pos[1] = 0;
		momentum[0] = 707;
		momentum[1] = 707;
		//dT = 0.75*(3.6*(1e-11));
		dT = 7e-8;
		B = 1;
	}
	
	public static void printInitialState(ElectronInMagneticField electron) {
		System.out.println("Initial State:");
		System.out.println("------------------------------------");
		System.out.println("B: "+B+" (T)");
		System.out.println("dT: "+dT+" (s)");
		System.out.println("Initial Momentum: " + electron.getInitialAbsoluteMomentum()+" MeV");
	    System.out.println("Bending Radius: " + electron.getBendingRadius()+ " m");
		System.out.println("x: "+electron.getInitialPos()[0]+" y: "+electron.getInitialPos()[1]+" (m)");
		System.out.println("px: "+electron.getInitialMomentum()[0]+" py: "+electron.getInitialMomentum()[1]+" (MeV)");
	    System.out.println("------------------------------------");
	}
	
	public static void printFinalState(ElectronInMagneticField electron) {
		System.out.println("Final State:");
		System.out.println("------------------------------------");
		System.out.println("x: "+electron.getFinalPos()[0]+" y: "+electron.getFinalPos()[1]+" (m)");
		System.out.println("px: "+electron.getFinalMomentum()[0]+" py: "+electron.getFinalMomentum()[1]+" (MeV)");
	    System.out.println("------------------------------------");
	}
	
	public static void printAdditionalInfo(ElectronInMagneticField electron) {
		System.out.println("Additional Information:");
	    System.out.println("------------------------------------");
		System.out.println("Beta: "+electron.beta());
		System.out.println("LorentzFactor: "+electron.lorentzFactor());
		System.out.println("Final Absolute Momentum: "+electron.getFinalAbsoluteMomentum());
		System.out.println("Predicted Time of Revolution: "+(2*Math.PI*electron.getBendingRadius())/(electron.beta()*3e8));
	}
	
    static void DumpXYZ(String filename, double[][] txy) throws IOException
    {
	// write time and x,y,z coordinates to CSV file
        FileWriter file = new FileWriter(filename);     // this creates the file with the given name
        PrintWriter outputFile = new PrintWriter(file); // this sends the output to file1

        // now make a loop to write the contents of each bin to disk, one number at a time
        for (int n = 0; n < txy.length; n++) {
            double[] Y = txy[n];
            // comma separated values
            outputFile.println(Y[0] + "," + Y[1] + "," + Y[2]);
        }
        outputFile.close(); // close the output file
        return;
    }

}
