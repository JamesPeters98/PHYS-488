package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Scanner;

class SimpleArray
{
    static Scanner keyboard = new Scanner(System.in);
    
    public static void main (String [] args ) throws IOException {
		// Program to store integer numbers in an array
		System.out.println("How many numbers do you want to store?");
		int numberToStore = keyboard.nextInt();
		
		if(numberToStore > 0){
			double [] myStore = new double [numberToStore]; 
		
			// create an array called 'myStore' in memory with this number of elements
			// remember: the array index start at ZERO, not 1.
			for (int n = 0; n < numberToStore; n++) {
			    System.out.println( "Please type in the " + (n+1) + " number    " ); 
			    myStore[n] = keyboard.nextInt();
			}
			System.out.println("\n\nAll finished... please check this list");
			for (int n = 0; n < numberToStore; n++) {
			    System.out.println( n + "  location,  value stored = " + myStore[n] );
			}
			
			System.out.println("\n\nAverage of all numbers: ");
			double sum = 0;
			for (int n = 0; n < numberToStore; n++) {
			    sum += myStore[n];
			}
			double average = sum/numberToStore;
			System.out.println(average);
		} else {
			System.out.println("No numbers to store... how pointless!");
		}
		
		
    }
}
