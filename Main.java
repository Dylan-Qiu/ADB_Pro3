import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main{
	public static double min_supp = -0.1;
	public static double min_conf = -0.1;
	public static String filename;
	
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Wrong input! Please redo that again");
			System.exit(1);
		}
		min_supp = Double.parseDouble(args[1]);
		min_conf = Double.parseDouble(args[2]);
		filename = args[0];
		parseCSV(filename);
		//supportAndConf(min_supp, min_conf, filename);
	}
		public static void parseCSV(String filename) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		FileWriter fileWriter = null;
		HashMap<String, HashSet<String>> keep = new HashMap<String, HashSet<String>>();
		
		try {
			
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				for(int i = 0 ; i < line.length(); i++) {
					if(i != line.length() -1) {
						if(line.charAt(i) == ',' && line.charAt(i+1) == ',') { 
							line = line.substring(0, i+1) + "NULL" + line.substring(i+1);
						} 
					}
					if(i == (line.length() -1) && line.charAt(i) == ',') {
						line += "NULL Grade";
					}
					}	
				String[] result = line.split(cvsSplitBy);
					
						System.out.println(result[0] + result[6] + result[7]);

				
				}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		}
	/*public static void supportAndConf(double min_supp, double min_conf, String filename) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		FileWriter fileWriter = null;
		int lineCount = 0;
		HashMap<String, Integer> wordCount = new HashMap<String,Integer>();
		try {

			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				lineCount ++;
				String[] result = line.split(cvsSplitBy);
				for(int i = 0; i < result.length; i++) {
					if(!wordCount.containsKey(result[i])) {
						wordCount.put(result[i], 1);
					} else {
						int count = wordCount.get(result[i]);
						wordCount.put(result[i], count + 1);
					}
						
				}
		
			}
			for(String type: wordCount.keySet()) {
				int count = wordCount.get(type);
				double frequency = (double)count/(double)lineCount;
				System.out.println("[" + type + "], " + frequency);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  }
	  */

	}
