//package minimum.mutations;

import java.util.ArrayList;
import java.util.Arrays;

public class QuodMut {
	private String start;
	private String end;
	private int numMut;
	private ArrayList<String> bank;
	private ArrayList<String> progressionSeq;
	
	//we don't want an empty constructor.
	public QuodMut(String start, String end, ArrayList<String> bank) {
		super();
		this.start = start;
		this.end = end;
		this.bank = bank;
		//not an input, but we have to instantiate this for later
		progressionSeq = new ArrayList<>(); 
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public ArrayList<String> getBank() {
		return bank;
	}
	public void setBank(ArrayList<String> bank) {
		this.bank = bank;
	}
	public int getNumMut() {
		return numMut;
	}
	public void setNumMut(int numMut) {
		this.numMut = numMut;
	}
	public ArrayList<String> getProgressionSeq(){
		return this.progressionSeq;
	}
	//Our base public function, returns an integer according to the challenge 0 for no change
	// -1 for no valid change, and whatever for num changes that results in new gene sequence
	public int getNumMutations() {
		if(getBank().size() == 0) {
			//We have no valid mutations
			System.out.println("Bank is empty!");
			return -1;
		}
		int rawChanges = getDiff(this.start, this.end);
		ArrayList<String> progression = new ArrayList<>();
		setNumMut(rawChanges);
		//just gonna nip this special case in the bud here
		if(getNumMut() == 0) {
			return 0;
		}
		//we will compare progression to the bank of valid mutations
		progression = buildProgression();
		//now set rawChanges after comparing progression to bank
		rawChanges = compareToBank(progression);
		
		return rawChanges;
	}
	
	//Gets the raw number of changes, this number is used to build a possible mutation progression
	//which we will later compare to the gene bank to check if such changes are valid.
	private int getDiff(String base, String comp) {
		char[] baseArr = base.toCharArray();
		char[] compArr = comp.toCharArray();
		int numChanges = 0;
		
		if(baseArr.length != 8) {
			System.out.println("Gene sequence is of invalid length!");
		}
		if(baseArr.length != compArr.length) {
			System.out.println("Gene Sequence in bank was invalid.");
			return -1;
		}
		for(int i = 0; i < baseArr.length; i++) {
			if(baseArr[i] != compArr[i]) {
				numChanges++;
			}
		}
		return numChanges;
	}
	
	//Builds and returns an arrayList with a potential gene mutation progression
	private ArrayList<String> buildProgression() {
		char[] baseArr = this.start.toCharArray();
		//mutatedArr is used for the possible sequence, see NB in compareToBank()
		char[] mutatedArr = Arrays.copyOf(baseArr, baseArr.length); 
		char[] compArr = this.end.toCharArray();
		//Our potential progression sequence
		ArrayList<String> returnMe = new ArrayList<>();
		//declared here instead of in the loop
		String builtString = new String();
		//used for our logic, swap is used to change the base string back to it's original value
		//when comparing our potential progression list to our bank
		char swap;
		int changeCount = 0;
		int index = 0;
		//If any ONE character is changed, we add that changed String into the progression list
		while(changeCount < getNumMut()) {
			
			if(baseArr[index] != compArr[index]) {
				//System.out.println("  index: "+index+" char:"+baseArr[index]);
				//storing our base value
				swap = baseArr[index];
				//character value was different, we want to add the base string with JUST that change
				baseArr[index] = compArr[index];
				//We want to keep and store changes; It's an array so we need to make sure it's not just a reference
				mutatedArr[index] = compArr[index];
				//Adds to progressSeq (which will list a possible sequence of changes, just for fun
				progressionSeq.add(String.copyValueOf(mutatedArr));
				//I suppose we don't REALLY need a String here
				builtString = String.copyValueOf(baseArr);
				returnMe.add(builtString);
				//original string is now restored to be compared with future ONE character changes
				baseArr[index] = swap;
				//each time we change a gene we get closer to num changes, when we hit that count, we must be finished
				changeCount++; 
				
			}
			index++;
		}
		return returnMe;
	}
	
	//here we compare the passed in String progression, which contains one char changes only, to our
	//bank list
	//NB: It is possible that a progressive mutation is permitted, e.g. (using 4 )
	//AAAA to ATTA BANK {AATA, ATTA} in this example AAAA -> ATAA is invalid but AATA->ATTA is valid
	//Because of this, a comparison to the potential progression list was also added
	//see TestProg.txt for an example of this
	private int compareToBank(ArrayList<String> compareMe) {
		int changes = 0;
		for(String s : compareMe) {
			System.out.println("s: "+s);
			if(getBank().contains(s)) {
				changes++;
			//So if the bank does not contain one of our oneChange strings, the potential sequence MAY
			//contain that value, (sequence if a list of gene changes that keep prior gene states)
			//but while the possible gene change might be within bank, it doesn't mean that our
			//starting gene ever changed in that way. See TestTwo.txt for an example of this
			//where only one possible mutation (the end) exists within the bank. A gene
			//would have to change twice (a possibility within the potential sequence that the gene start
			//cannot reach
			//Well nevermind, that messed it up (see TestProg.txt for a case where it SHOULD have successfully
			// mutated but did not given the following uncommented code).
			} else if(checkProgressionBank() /*&& getProgressionSeq().contains(s)*/){
				changes++;
			} else {
				//our progression is not a valid gene change
				System.out.println("Change not within Bank.");
				return -1;
			}
		}
		return changes;
	}
	
	//We will return true if there is a hit, and false otherwise
	private boolean checkProgressionBank() {
		for (String potentialChange : getProgressionSeq()) {
			if (getBank().contains(potentialChange)) {
				return true;
			}
		}
		return false;
	}
}
