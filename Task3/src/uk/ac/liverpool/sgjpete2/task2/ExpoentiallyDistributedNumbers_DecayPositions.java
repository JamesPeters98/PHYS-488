package uk.ac.liverpool.sgjpete2.task2;

import java.io.IOException;
import java.util.Random;

import uk.ac.liverpool.sgjpete2.Histogram;

public class ExpoentiallyDistributedNumbers_DecayPositions {
	
	static double c = 3e8;
	static double gamma = 100;
	
	public static void main(String[] args) throws IOException {
		
		Random rand = new Random();
		double tau = 1e-12;
		double resolution1 = 0.01;
		double resolution2 = 0.05;
		
		// create an instance of the Class Histogram: 50 bins from 0.0 to 1.0
		// this is for the flat distribution
		Histogram histPerfect = new Histogram(80, -0.05, 0.2, "Decay Distances Perfect Detector");
		Histogram histMeasured = new Histogram(80, -0.05, 0.2,  "Decay Distances "+resolution1+"m Detector");
		Histogram histMeasured2 = new Histogram(80, -0.05, 0.2,  "Decay Distances "+resolution2+"m Detector");

		int trials = 100000;
		for (int i = 0; i < trials; i++) {
		    double r = rand.nextDouble();
		    double detectorSpread1 = resolution1*rand.nextGaussian();
		    double detectorSpread2 = resolution2*rand.nextGaussian();
		    double D = -tau*Math.log(r)*c*gamma;
		    histPerfect.fill(D);
		    histMeasured.fill(D+detectorSpread1);
		    histMeasured2.fill(D+detectorSpread2);
		}

		histPerfect.print();
		histPerfect.writeToDisk("outputs/task3/histogram_decaydistancesPerfect.csv");
		histMeasured.print();
		histMeasured.writeToDisk("outputs/task3/histogram_decaydistancesMeasured_"+resolution1+".csv");
		histMeasured2.print();
		histMeasured2.writeToDisk("outputs/task3/histogram_decaydistancesMeasured_"+resolution2+".csv");
	
	}

}
