//package minimum.mutations;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver {

	//main includes a bunch of ugly testing code
	public static void main(String[] args) {
		String test = "ATATGCGC";
		String mutatedTest = "ATTTGCGG"; //Diff should be one, bank size should be ATATGCGG
		ArrayList<String> testBank = new ArrayList<>();
		testBank.add("ATAAGCGC");
		testBank.add("ATATGCGG");
		testBank.add("ATTTGCGC");
		
		//initial hard coded test
		QuodMut testOne = new QuodMut(test, mutatedTest, testBank);
		System.out.println(testOne.getStart());
		System.out.println(testOne.getEnd());
		System.out.println(testOne.getBank());
		System.out.println("Number of Mutations: "+testOne.getNumMutations());
		System.out.println("Possible progression: "+testOne.getProgressionSeq());
		System.out.println("=============================================================================");
		
		//for eclipse, the compiles files are not in the same directory as the src files, which I learned the hard way
		MutReader wrangler = new MutReader(".\\TestOne.txt");
		//getGeneString throws a file not found exception
		try {
			wrangler.getGeneStrings();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		QuodMut testFile = new QuodMut(wrangler.getStart(), wrangler.getEnd(), wrangler.getBank());
		System.out.println(testFile.getStart());
		System.out.println(testFile.getEnd());
		System.out.println(testFile.getBank());
		System.out.println("Number of Mutations: "+testFile.getNumMutations());
		System.out.println("Possible progression: "+testFile.getProgressionSeq()); 
		System.out.println("=============================================================================");
		
		//This gene sequence should fail to mutate
		MutReader wrangler2 = new MutReader(".\\TestTwo.txt");
		//getGeneString throws a file not found exception
		try {
			wrangler2.getGeneStrings();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		QuodMut testFile2 = new QuodMut(wrangler2.getStart(), wrangler2.getEnd(), wrangler2.getBank());
		System.out.println(testFile2.getStart());
		System.out.println(testFile2.getEnd());
		System.out.println(testFile2.getBank());
		System.out.println("Number of Mutations: "+testFile2.getNumMutations());
		System.out.println("Possible progression: "+testFile2.getProgressionSeq());
		System.out.println("=============================================================================");
		
		
		//In this case, the gene cannot mutate from the start to a step within the end, but it can mutate to
		//the end value after one singular valid mutation, see NB of QuodMut-compareToBank()
		MutReader wrangler3 = new MutReader(".\\TestProgression.txt");
		//getGeneString throws a file not found exception
		try {
			wrangler3.getGeneStrings();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		QuodMut testProg = new QuodMut(wrangler3.getStart(), wrangler3.getEnd(), wrangler3.getBank());
		System.out.println(testProg.getStart());
		System.out.println(testProg.getEnd());
		System.out.println(testProg.getBank());
		System.out.println("Number of Mutations: "+testProg.getNumMutations());
		System.out.println("Possible progression: "+testProg.getProgressionSeq()); 
	}

}
