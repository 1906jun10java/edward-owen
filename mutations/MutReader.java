//package minimum.mutations;

import java.io.*;
import java.util.ArrayList;

public class MutReader {
	private String start;
	private String end;
	private ArrayList<String> bank;
	private String fileName;
	
	public MutReader (String fileName) {
		bank  = new ArrayList<String>();
		this.fileName = fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	//courtesy of several recommendations, not too different from a scanner
	public boolean getGeneStrings() throws FileNotFoundException {
		//in case we types the file wrong, complain
		try {
		  FileInputStream fstream = new FileInputStream(fileName);
		  DataInputStream input = new DataInputStream(fstream);
		  //buffered reader does not take raw input
		  BufferedReader bReader = new BufferedReader(new InputStreamReader(input));
		  String strLine;
		  
		  while ((strLine = bReader.readLine()) != null)   {
			  // we are expecting a strict input format, see TestOne.txt for an example
			  parseLine(strLine);
			  
			  }
			  //Close the input stream
			  input.close();
		} catch (Exception e) {
			System.out.println("File Not Found: "+fileName);
			return false;
		}
		return true; //we have found that file and read it's contents
	}
	
	public void parseLine(String input){
		//with the given format, we can easily parse through our file to grab the respective values
		String[] parserArray = input.split(":");
		//System.out.println(parserArray[0] + " " + parserArray[1] +"  length:"+parserArray.length);
		//e.g. the value of our first line is START:ATATGCGC, parserArray[0] is
		//START and :. we know that parserArray[1] must be out value
		if (parserArray[0].equals("START")) {
			//System.out.println("Parser: "+parserArray[0]);
			this.start = parserArray[1];
		}
		if (parserArray[0].equals("END")) {
			//System.out.println("Parser: "+parserArray[0]);
			this.end = parserArray[1];
		}
		if (parserArray[0].equals("BANK")) {
			//System.out.println("Parser: "+parserArray[0]);
			for(int i = 1; i < parserArray.length; i++) {
				//System.out.println("val: "+parserArray[i]);
				bank.add(parserArray[i]);
			}
		}

	}
	
}
