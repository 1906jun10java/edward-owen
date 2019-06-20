//package com.revature.strings;

public class FunWithStrings {

	public static void main(String[] args) {
		String testString = "There Once Was a Man from...";
		String palindrome = "rotator";
		
		System.out.println(testString);
		String reversed = reverseString(testString);
		System.out.println(reversed);
		
		System.out.println(isPalindrome(palindrome));
		System.out.println(isPalindrome("I am a string literal"));
		System.out.println(removeSpaces("This is a sTRING with    Man...y s. . .pa. c.es."));
		
		//System.out.println(factorialize(5));
		
		String test = "abcde";
		System.out.println(recursiveReverse(test));
		
		//added thurs
		String testMe = "racecar";
		System.out.println(isPalindrome(testMe, 0, testMe.length()));
		//
	}
	
	public static int factorialize(int n) {
		if(n == 0 || n == 1) {
			return 1;
		}
		return n * factorialize(n-1);
	}
	
	//removes spaces and periods. may want to learn regex to do this easily
	public static String removeSpaces(String s) {
		StringBuilder sb = new StringBuilder();
		String returnMe;
		char[] stringArray = s.toCharArray();
		for(char c: stringArray) {
			if(c != ' ' && c != '.') {
				sb.append(c);
			}
		}
		returnMe = sb.toString();
		return returnMe;
	}
	
	//Reverses a string
	public static String reverseString(String s) {
		StringBuilder sb = new StringBuilder();
		String returnMe = null; // = null recommend by eclipse
		char[] reverseMe = s.toCharArray();
		//Used to have a swapper but that was ugly, and since I needed a stringbuilder anyway...
		for(int i = reverseMe.length-1; i >= 0; i--) {
			sb.append(reverseMe[i]);
		}
		returnMe = sb.toString();
		return returnMe;
	}
	
	//Much easier than with stringbuilder
	public static String recursiveReverse(String s) {
		String temp;
		if(s.length() == 1) {
			return s;
		}
		//temp grabs each char at the end as a substring
		temp = s.substring(s.length()-1);
		//System.out.println(temp);
		//and the next string is shrunk by one, slowly grabbing the last char (as a substring)
		//and adding it to temp until it is finished
		return temp += recursiveReverse(s.substring(0, s.length()-1));
		
	}
	
	//We can add a method to remove spaces and then compare these to eachother
	public static boolean isPalindrome(String s) {
		String reverse = reverseString(s);
		if(reverse.toLowerCase().equals(s.toLowerCase())) {
			return true;
		}else {
			return false;
		}
	}
	
	//Added thurs
	public static boolean isPalindrome(String s, int a, int b) {
		//same letter
		if(a == b) {
			return true;
		}
		//if the first and last chars are not the same, it isn't a palindrome
		if(!s.substring(a, a+1).equals(s.substring(b-1, b))) {
			return false;
		}
		if(s.length() > 2) {
			//cut off the first and last char
			return (isPalindrome(s.substring(a+1, s.length()-1)));
		}
		return true;
	}
	//
}
