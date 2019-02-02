package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Scanner;

class InputData
{
    static Scanner keyboard = new Scanner(System.in);
    static double mToFeet = 3.280839895; //metres to feet conversion

    public static void main (String [] args ) throws IOException
    {
	String name;     // a string is a sequence of characters
	double height; //height in m
	double heightFeet; //height in feet
	int feet; //feet component of height
	int inches; //inch component of height
	
	int age;
	
	System.out.println( "Please type in your name " );

	// the next line gets a string of characters from the keyboard
	name = keyboard.nextLine();  // note the L in nextLine()
	System.out.println("\nHello " + name);// this outputs the characters
	System.out.println("Please type in your height in metres ");

	// next line converts the input characters to a floating point variable
	height = keyboard.nextDouble();
	heightFeet = height*mToFeet; //convert to feet.
	feet = (int) Math.floor(heightFeet); //round decimal feet down to nearest integer.
	inches = (int) Math.round((heightFeet-feet)*12); //Calculates inches from decimal height and floored height.
	
	System.out.println("Thank you, your height is " + 100*height +  " cm or "+ feet +  "ft"+inches);
	System.out.println("Please type in your age in years");

	// next line converts the input characters to a integer variable
	age = keyboard.nextInt();
	
	int yearOfBirth = 2017-age;
	System.out.println("You were probably born in " + yearOfBirth);
    }
}