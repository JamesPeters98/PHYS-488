package uk.ac.liverpool.sgjpete2.task1;

import java.util.Scanner;

public class Simulation {
	
    static Scanner keyboard = new Scanner(System.in);
	
	static double[] pos = new double[2];
	static double[] momentum = new double[2];
	static double dT;
	static double B;
	
	public static void main(String[] args) {
		readInput();
		//testInputs();
		ElectronInMagneticField electron = new ElectronInMagneticField(pos, momentum, dT, B);
		printInitialState(electron);
		printFinalState(electron);
		printAdditionalInfo(electron);
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
		momentum[0] = 10;
		momentum[1] = 0;
		dT = (7.000093569066937E-10)/8;
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
		System.out.println("Predicted Time of Revolution: "+(electron.lorentzFactor()*(2*Math.PI*electron.m)/(electron.q*B)));
		System.out.println("Predicted Time of Revolution (Electrons Frame): "+((2*Math.PI*electron.getBendingRadius())/(electron.beta()*3e8))/electron.lorentzFactor());
	}

}
