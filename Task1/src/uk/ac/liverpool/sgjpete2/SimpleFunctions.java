package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Scanner;

class SimpleFunctions
{
    static Scanner keyboard = new Scanner(System.in);
    
    static double average(double x, double y)
    {
	double sum;
	sum = x + y;
	return (sum / 2);
    }
    
    static double difference(double x, double y){
        return Math.abs(x - y); //Returns absolute value of difference between x and y.
    }

    // Example of program with simple methods method
    public static void main (String [] args ) throws IOException
    {     
	System.out.println("Please type in the first number    "); 
	double first = keyboard.nextDouble();
	System.out.println("Please type in the second number   "); 
	double second = keyboard.nextDouble();

	double ans = average(first, second);
	System.out.println("The average of these two numbers = " + ans);
	double diff = difference(first,second);
	System.out.println("The difference of these two numbers = " + diff);
    }
}
