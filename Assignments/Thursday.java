//package com.revature.strings;

public class Thursday {

	public static void main(String[] args) {
		String mega = "This is a great and mighty String.";
		String and = "and";
		String nand = "nand";
		System.out.println(isSubstring(mega, and));
		//System.out.println(isSubstring(mega, nand));
		
		//System.out.println(pyraCalc(4));
		//System.out.println(pyraCalc(6));
		//System.out.println(pyraCalcButBetter(100));
		
		int num = pyraCalc2(3);
		System.out.println(num);
	}
	

	public static boolean isSubstring(String base, String sub) {
		char[] baseArr = base.toCharArray();
		char[] subArr = sub.toCharArray();
		int index = 0;
		boolean isMatch = true;
		// We will be iterating through the first containing string (no checks for size)
		for(int i = 0; i < baseArr.length; i++) {
			//This makes it clear what we are doing
			//System.out.println(i+":base "+baseArr[i]+"  "+index+":sub "+subArr[index]);
			//If there is a mismatch, reset the index of the substring to 0 and
			//set isMatch to false (isMatch is the bool with stores whether or not we found a substring
			if(baseArr[i] != subArr[index]) {
				isMatch = false;
				index = 0;
			} else { //if they are equal, compare the next char in both the base string and the substring
				isMatch = true;
				index++;
			}
			//if the index is the same as the length of the substring, we're finished, break out
			if(index == subArr.length) {
				break;
			}
		}
		//If we've broken out of the loop (preventing isMatch from being reset to false)
		//than we have a substring
		return isMatch;
	}
	
	public static int pyraCalc(int layers) {
		//Only one block per layer
		if(layers == 1) {
			return 1;
		}
		//Very similar to the factorial problem, only adding instead of multiplying
		//(also n(n+1)/2
		return layers + pyraCalc(layers-1);
	}
	
	//want to visualize what's going on...
	public static int pyraCalc2(int layers) {
		System.out.println("["+layers+"]");
		if(layers == 1) {
			return 1;
		}
		for(int i = layers; i > 0; i--) {
			System.out.println("[]");
		}
		return layers += pyraCalc(layers-1);
	}
	
	private static int pyraCalcButBetter(int i) {
		return (i*(i+1))/2;
	}
	
}
