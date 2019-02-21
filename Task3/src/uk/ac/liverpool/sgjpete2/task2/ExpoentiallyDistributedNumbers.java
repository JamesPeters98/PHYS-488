package uk.ac.liverpool.sgjpete2.task2;

import java.io.IOException;
import java.util.Random;

import uk.ac.liverpool.sgjpete2.Histogram;

public class ExpoentiallyDistributedNumbers {
	
	public static void main(String[] args) throws IOException {
		
		Random rand = new Random();
		int tau = 10;
		
		// create an instance of the Class Histogram: 50 bins from 0.0 to 1.0
		// this is for the flat distribution
		Histogram hist = new Histogram(50, 0, 100, "Exponentially Distributed Random Numbers");

		int trials = 10000;
		for (int i = 0; i < trials; i++) {
		    double value = rand.nextDouble();
		    double D = -tau*Math.log(value);
		    hist.fill(D);
		}

		hist.print();
		hist.writeToDisk("outputs/task3/histogram_"+tau+".csv");
	
	}

}
