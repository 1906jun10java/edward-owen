//package com.revature.weeka;
import java.util.Scanner;

public class FizzBuzz {

	// the max num we will take in is 10 for now
	private static int[] delimiters = new int[10];
	private static String[] messages = new String[10];
	private static int validVal = 0; //how many non-zero ints do we have?
	private static int min, max;

	public static void main(String[] args) {
		//FizzBuzzBasic();
		getUserInput();
	}

	public static void FizzBuzzBasic() {
		delimiters[0] = 3;
		delimiters[1] = 5;
		validVal = 2;
		messages[0] = "fizz";
		messages[1] = "buzz";
		fizzBuzzAdvanced(1, 100, delimiters, messages);
	}

	public static void getUserInput() {
		Scanner scan = new Scanner(System.in);
		int counter = 0;

		System.out.println("Type Min");
		min = getInt(scan);
		System.out.println("Type Max");
		max = getInt(scan); 
		while( max < min) {
			//only print error message if it's larger.
			System.out.println("Max can't be less than min!");
			max = getInt(scan);
		}
		System.out.println("Please type up to 10 integers to factorificate. Type 0 when finished.");
		//evil while loop
		while(counter < delimiters.length) {
			delimiters[counter] = getInt(scan);
			//this just keeps getting uglier
			if(delimiters[counter] == 0) {
				System.out.println("All set for numbers.");
				break; 
			}
			counter++;
			System.out.println("Number count is now: "+counter);
		}
		//When using nextInt(), the '\n' is not read, so we take the next line (seen by the user as "0\n")
		//and throw it out.
		scan.nextLine(); 
		validVal = counter;
		counter = 0;
		System.out.println("Now type some values/words you would like to be printed in lieu of the numbers.");
		//this should force the user to type something so we don't get any empty array parts
		while(counter < validVal) {
			messages[counter] = getString(scan);
			counter++;
		}
		System.out.println("Thank you.");
		scan.close();
		//maybe move the fields here and pass them around instead
		fizzBuzzAdvanced(min, max, delimiters, messages);
	}

	// checks for an int
	public static int getInt(Scanner scan) {
		int result;
		while (true) {
			if (!scan.hasNextInt()) {
				System.out.println("That's not a number!");
				scan.next();
			} else {
				result = scan.nextInt();
				break;
			}
		}
		return result;
	}

	//same as above but for strings
	public static String getString(Scanner scan) {
		String result;
		while (true) {
			if (!scan.hasNextLine()) {
				scan.hasNext();
			} else {
				result = scan.nextLine();
				break;
			}
		}
		return result;
	}
	
	//We will be looping over the whole delimiter array to check these values
	public static boolean printList(int divisor) {
		boolean isDivisible = false;
		for(int index = 0; index < validVal; index++) {
			//System.out.println("printList() index="+index+" val = "+delimiters[index]+"  isdiv: "+isDivisible);
			if(divisor % delimiters[index] == 0) {
				isDivisible = true;
				System.out.print(messages[index]);
			}
		}
		if(isDivisible) {
			System.out.println(); //if the number was divisible we print a new line for neatness
		}
		return isDivisible;
	}
	
	public static void fizzBuzzAdvanced(int m, int n, int[] numbers, String[] terms) {
		//big loop for all our numbers
		for(int i = m; i < n; i++) {
			//If the number is not divisible, we just print out the number, printing
			//the replacing strings is handled within printList which we check everytime.
			//bonus: not as ugly as it could be
			if(!printList(i)) {
				System.out.println(i);
			}
		}
	}

}
