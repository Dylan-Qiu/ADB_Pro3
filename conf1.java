import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class conf1{
	public static HashMap<ArrayList<String>, Double> itemSetSupport = new HashMap<ArrayList<String>, Double>();

	public static double min_conf = 0.05;
	public static void main(String[] args) throws IOException{
		for(ArrayList<String> list: itemSetSupport.keySet()) {
			if(list.size() == 2) {
				int flag1 = 0;
				if(flag1 == 0) {
				ArrayList<String> left = new ArrayList<String>();
				ArrayList<String> right = new ArrayList<String>();
				left.add(list.get(0));
				right.add(list.get(1));
				double combine_supp = itemSetSupport.get(list);
				double left_supp = itemSetSupport.get(left);
				printOrNot(left, right, left_supp, combine_supp);
				flag ++;
				} 
				if(flag1 ==1) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(0));
					right.add(list.get(1));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp);
					flag1 ++;
				}
			} 
			if(list.size() == 3) {

				int left_length = 1;
				if(left_length == 1) {
					int a = 0;
					if(a ==0) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(0));
					right.add(list.get(1));
					right.add(list.get(2));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp);
					a++;
					} 
					if(a == 1) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(1));
					right.add(list.get(0));
					right.add(list.get(2));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp);
					a++;
					}
					if(a == 2) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(2));
					right.add(list.get(0));
					right.add(list.get(1));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp);
					a++;
					left_length ++;
					}
				} 
				if(left_length == 2) {
					int flag = 0;
					if(flag == 0) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(0));
					left.add(list.get(1));
					right.add(list.get(2));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp);
					flag ++;
					} 
					if(flag == 1) {
						ArrayList<String> left = new ArrayList<String>();
						ArrayList<String> right = new ArrayList<String>();
						left.add(list.get(0));
						left.add(list.get(2));
						right.add(list.get(1));
						double combine_supp = itemSetSupport.get(list);
						double left_supp = itemSetSupport.get(left);
						printOrNot(left, right, left_supp, combine_supp);
						flag ++;
					}
					if(flag == 2) {
						ArrayList<String> left = new ArrayList<String>();
						ArrayList<String> right = new ArrayList<String>();
						left.add(list.get(1));
						left.add(list.get(2));
						right.add(list.get(0));
						double combine_supp = itemSetSupport.get(list);
						double left_supp = itemSetSupport.get(left);
						printOrNot(left, right, left_supp, combine_supp);
						flag ++;
					}
				}
			}
	}
}
	public static void printOrNot(ArrayList<String> left, ArrayList<String> right, Double left_supp, Double combine_supp) {
			double conf = combine_supp/left_supp;
			if(conf >= min_conf) {
				String l = "";
				String r = "";
				for(String s : left) {
					l += s + "\t";
				}
				for(String s1 : right) {
					r += r + "\t";
				}
			System.out.println("[" + l + "] => [" + r + "], " + conf);
			}
	}
}
		
