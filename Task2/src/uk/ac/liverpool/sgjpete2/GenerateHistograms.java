package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Scanner;
import java.util.Random;

class GenerateHistograms
{
    static Random randGen = new Random();
    static Scanner keyboard = new Scanner(System.in);
    
    private static double addTwelve()
    {
	// add 12 uniformly-distributed random numbers
	double sum = 0.;
	for (int n = 0 ; n < 12; n++) {
	    sum = sum + randGen.nextDouble();
	}
	return (sum);
    }
    
    public static void main (String [] args ) throws IOException
    {       
	// create an instance of the Class Histogram: 50 bins from 0.0 to 1.0
	// this is for the flat distribution
	Histogram hist = new Histogram(50, 0, 1, "Uniform");

	System.out.println( "Input the number of random numbers to generate");
	int trials = keyboard.nextInt();
	for (int i = 0; i < trials; i++) {
	    double value = randGen.nextDouble();
	    hist.fill(value);
	}

	hist.print();
	hist.writeToDisk("uniform.csv");
	
	
	//second histogram.
	Histogram hist2 = new Histogram(50, 0, 12.0, "Normally Distributed");
	for (int i = 0; i < trials; i++) {
	    double value = addTwelve();
	    hist2.fill(value);
	}
	
	hist2.print();
	hist2.writeToDisk("normalDist.csv");
	
	
    }
}
