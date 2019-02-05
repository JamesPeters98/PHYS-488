package uk.ac.liverpool.sgjpete2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

class SimpleLoops
{
    static Scanner keyboard = new Scanner(System.in);

    public static void main (String [] args ) throws IOException
    {
	// example 1 , "for" loop. 
	int maxn = 10;
	for (int n = 0; n < maxn; n++) { // n++ means add 1 to current value of n
	    System.out.println("When n = " + n + " then n^n = " + Math.pow(n,n) );
	}

	// example 2 , "while" loop
	// guessing game.. try to guess the number I'm thinking of
	Random randomGenerator = new Random();
	int answer = randomGenerator.nextInt(100); // generates an int between 0 and 99
	System.out.println("Hello.. try to guess the integer in the range 0 to 99 I'm thinking about");
	System.out.println("Type in your first guess");

	int yourGuess;
	int guesses = 0;
	
	//Have to ask for initial guess.
	yourGuess = keyboard.nextInt();

	while (yourGuess != answer) { // != means NOT EQUAL
	    System.out.print(" No, your guess = " + yourGuess + " is not correct, try ");
	    if (yourGuess < answer) {
		System.out.println("higher");
	    } else {
		System.out.println("lower");
	    }
	    guesses++;
	    System.out.println("Guesses: "+guesses);
	    
	    //Moved this line to bottom in so that the loop doesnt run if yourGuess is true.
	    yourGuess = keyboard.nextInt();
	}
	System.out.println("Yes "+ answer + " is correct, at last you did it!");
    }
}