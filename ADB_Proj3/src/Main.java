import java.util.*;

public class Main{
	public static double min_supp = -0.1;
	public static double min_conf = -0.1;
	public static String filename;
	
	public static void main(String[] args) {
		//get input
		System.out.println("Please type in min_supp and min_conf, filename has been encoded:");
		System.out.println("Input like below: ");
		System.out.println("0.05 0.5");
		Scanner console = new Scanner(System.in);
		if(!console.hasNext()){
			System.out.println("No Input");
		}
	
		min_supp = console.nextDouble();
		min_conf = console.nextDouble();
		filename = "INTEGRATED-DATASET.csv";
		System.out.println("min_supp is " + min_supp);
		System.out.println("min_conf is " + min_conf);
		
		//transactions are a list of all transactions, a transaction is represented with a list
		List<List<String>> transactions = FileParser.parseCSV(filename);		
		Apriori.assignTransactions(transactions);
		
		Apriori.aprioir(min_supp, min_conf);

	}
}
	
	
		
