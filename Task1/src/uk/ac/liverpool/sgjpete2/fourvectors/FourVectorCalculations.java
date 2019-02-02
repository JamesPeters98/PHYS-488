package uk.ac.liverpool.sgjpete2.fourvectors;

import java.util.Scanner;

public class FourVectorCalculations {
	
    static Scanner keyboard = new Scanner(System.in);
	
    public static void main (String [] args)
    {
    	//Read first particle and print its details.
    	FourVector particle = readFourVector(1);
		printVectorDetails(particle);
		
    	//Read second particle and print its details.
    	FourVector particle2 = readFourVector(2);		
		printVectorDetails(particle2);
		
    	//Calculate third particle and print its details.
		FourVector particle3 = addFourVectors(particle,particle2);
		printVectorDetails(particle3);
    }
    
    //Asks user to input data for FourVector and returns it.
    static FourVector readFourVector(int particleNumber){
    	System.out.println("Enter the Energy and components of Momentum separately for particle "+particleNumber+ " (E,px,py,pz)");
		FourVector particle = new FourVector();
		particle.E = keyboard.nextDouble();
		particle.px = keyboard.nextDouble();
		particle.py = keyboard.nextDouble();
		particle.pz = keyboard.nextDouble();
		return particle;
    }
    
    //Prints out details about the given FourVector.
    static void printVectorDetails(FourVector particle){
    	System.out.println("Particle with fourvector (E,px,py,pz) = ("
				   + particle.E + ", "
				   + particle.px + ", "
				   + particle.py + ", "
				   + particle.pz + ") GeV.");
	
		System.out.println("Momentum = " + particle.momentum() + " GeV");
		System.out.println("Mass = " + particle.mass() + " GeV");
		System.out.println("--------------------------");
    }
    
    //Adds two FourVectors together and returns the resulting FourVector.
    static FourVector addFourVectors(FourVector particle1, FourVector particle2){
    	FourVector vector = new FourVector();
    	
    	vector.E = particle1.E+particle2.E;
    	vector.px = particle1.px+particle2.px;
    	vector.py = particle1.py+particle2.py;
    	vector.pz = particle1.pz+particle2.pz;
    	
    	return vector;
    }

}
