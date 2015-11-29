import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class AssociationRule {
	public static void calConf(HashMap<List<String>, Double> itemSetSupport, double min_conf){
		HashMap<String, List<Double>> rulesConf = new HashMap<String, List<Double>>();
		List<List<String>> itemset1 = new ArrayList<List<String>>(itemSetSupport.keySet());
		List<List<String>> itemset2 = new ArrayList<List<String>>(itemSetSupport.keySet());
		for(List<String> ls1: itemset1){
			for(List<String> ls2: itemset2){
				if(isIdentical(ls1, ls2)){
					continue;
				}
				List<String> candidate = new ArrayList<String>(ls1);
				candidate.addAll(ls2);
				Collections.sort(candidate);
				if(itemSetSupport.containsKey(candidate)){
					double conf = itemSetSupport.get(candidate)/itemSetSupport.get(ls1);
					if(conf >=  min_conf){
						String str = "[";
						for(String l:ls1){
							str = str + l + ",";
						}
						str = str.substring(0, str.length()-1);
						str = str + "] => [";
						for(String r:ls2){
							str = str + r + ",";
						}
						str = str.substring(0, str.length()-1);
						str = str + "]";
						
						List<Double> value = new ArrayList<Double>();
						value.add(conf);
						value.add(itemSetSupport.get(candidate));
						rulesConf.put(str, value);
					}
				}
			}
		}
		Iterator<String> it = rulesConf.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+" (Conf: " + rulesConf.get(key).get(0)*100 + "%,Supp: " + rulesConf.get(key).get(1)*100 + "%)");		
		}
	}
	
	private static boolean isIdentical(List<String> l1, List<String> l2){
		if(l1.size() != l2.size()){
			return false;
		}
			
		int i = 0;
		while(i<l1.size()){
			if(!l1.get(i).equals(l2.get(i))){
				return false;
			}
			i++;
		}
		return true;
	}
}
