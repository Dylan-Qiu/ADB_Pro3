import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Conf {
	public static void calConf(HashMap<List<String>, Double> itemSetSupport, double min_conf){
		for(List<String> list: itemSetSupport.keySet()) {
			if(list.size() == 2) {
				int flag1 = 0;
				if(flag1 == 0) {
				List<String> left = new ArrayList<String>();
				List<String> right = new ArrayList<String>();
				left.add(list.get(0));
				right.add(list.get(1));
				double combine_supp = itemSetSupport.get(list);
				double left_supp = itemSetSupport.get(left);
				printOrNot(left, right, left_supp, combine_supp, min_conf);
				flag1++;
				} 
				if(flag1 ==1) {
					ArrayList<String> left = new ArrayList<String>();
					ArrayList<String> right = new ArrayList<String>();
					left.add(list.get(0));
					right.add(list.get(1));
					double combine_supp = itemSetSupport.get(list);
					double left_supp = itemSetSupport.get(left);
					printOrNot(left, right, left_supp, combine_supp, min_conf);
					flag1++;
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
					printOrNot(left, right, left_supp, combine_supp, min_conf);
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
					printOrNot(left, right, left_supp, combine_supp, min_conf);
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
					printOrNot(left, right, left_supp, combine_supp, min_conf);
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
					printOrNot(left, right, left_supp, combine_supp, min_conf);
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
						printOrNot(left, right, left_supp, combine_supp, min_conf);
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
						printOrNot(left, right, left_supp, combine_supp, min_conf);
						flag ++;
					}
				}
			}
	 	}
	}
	
	public static void printOrNot(List<String> left, List<String> right, Double left_supp, Double combine_supp, double min_conf) {
		double conf = combine_supp/left_supp;
		if(conf >= min_conf) {
			String l = "";
			String r = "";
			for(String s : left) {
				l += s + " ";
			}
			l = l.substring(0, l.length()-1);
			for(String s1 : right) {
				r += s1+ " ";
			}
			r = r.substring(0, r.length()-1);
		System.out.println("[" + l + "] => [" + r + "], " + conf);
		}
}

}
