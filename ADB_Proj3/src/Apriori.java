import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class Apriori {
	//all the transactions
	private static List<List<String>> allTransactions;
	//current item sets
	private static List<List<String>> itemSets = new ArrayList<List<String>>();
	//itemSetSupport stores different item sets and its corresponding support
	//these item sets have support higher than min_support
	private static HashMap<List<String>, Double> itemSetSupport = new HashMap<List<String>, Double>();
	
	//Assign parse results to allTransactions
	public static void assignTransactions(List<List<String>> transactions){
		allTransactions = new ArrayList<List<String>>(transactions);
	}
	
	//create item sets with only one item in it
	public static void createSizeOneItemSets(List<List<String>> allTransactions){
		HashMap<String, List<String>> uniqueItemSet = new HashMap<String, List<String>>();
		for(List<String> oneTransaction: allTransactions){
			for(int i = 0; i < oneTransaction.size(); i++){
				List<String> oneItem = new ArrayList<String>();
				oneItem.add(oneTransaction.get(i));
				uniqueItemSet.put(oneTransaction.get(i).toString(), oneItem);
			}
		}
		itemSets = new ArrayList<List<String>>(uniqueItemSet.values());
	}
	
	//This is the point where Main.java calls
	public static void aprioir(double min_supp, double min_conf){
		createSizeOneItemSets(allTransactions);
		int itemSetSize=1;
		
		while(itemSets.size()>0){
			calFreqItemSets(min_supp);
			
			if(itemSets.size() != 0){
				System.out.println("Got frequent itemSet with size: " + itemSetSize);
				createNewFromOld();
			}
			itemSetSize++;
		}
		
		Iterator<List<String>> it = itemSetSupport.keySet().iterator();
		while(it.hasNext()){
			List<String> key = it.next();
			System.out.println(key+", " + itemSetSupport.get(key)*100 + "%");
			
		}
		System.out.println();
		System.out.println();
		AssociationRule.calConf(itemSetSupport, min_conf);
	}
	
	//calculate frequencies of each item set in current item sets
	public static void calFreqItemSets(double min_supp){
		HashMap<List<String>, Integer> itemSetFreq = new HashMap<List<String>, Integer>();
		int transactionsNum = allTransactions.size();
		for(List<String> itemSet: itemSets){
			for(List<String> transaction: allTransactions){
				if(isSubList(itemSet, transaction)){
					if(!itemSetFreq.containsKey(itemSet)){
						itemSetFreq.put(itemSet, 1);
//						System.out.println("Add one entry to itemSetFreq!" + itemSet);
					}else{
						itemSetFreq.put(itemSet, itemSetFreq.get(itemSet)+1);
//						System.out.println("--------Increase frquency" + itemSet + ": " + itemSetFreq.get(itemSet));
					}
				}
			}
		}
		
		//item sets have support greater than min_supp are put in candidates 
		List<List<String>> candidates = new ArrayList<List<String>>();
		Iterator<List<String>> it = itemSetFreq.keySet().iterator();
		while(it.hasNext()){
			List<String> key = it.next();
			double supp = itemSetFreq.get(key)/(double)transactionsNum;
			if(supp >= min_supp){
				candidates.add(key);
				itemSetSupport.put(key, supp);
				
			}
//			System.out.println("Key is: " + key + "; Value is: " + itemSetFreq.get(key));
		}
		//assign candidates to itemSets
		itemSets = candidates;
		System.out.println(candidates);
			
	}
	
	//determine whether smallList is a sub-list of bigList
	private static boolean isSubList(List<String> smallList, List<String> bigList){
		if(smallList.size() > bigList.size()){
			System.out.println("Error in isSubList");
			return false;
		}
		HashSet<String> bigListSet = new HashSet<String>();
		for(String s : bigList){
			bigListSet.add(s);
		}
		for(String s : smallList){
			if(!bigListSet.contains(s)){
				return false;
			}
		}
		return true;
	}
	
	//generate new item sets having size equals to current item sets size plus one
	private static void createNewFromOld(){
		HashMap<String, List<String>> newCandidateSets = new HashMap<String, List<String>>();
		for(int i = 0; i<itemSets.size(); i++){
			for(int j = i+1; j<itemSets.size(); j++){
				List<String> X = itemSets.get(i);
				List<String> Y = itemSets.get(j);
				if(X.size() != Y.size()){
					System.out.println("Error in createNewFromOld");
					return;
				}
				
				List<String> candidate = new ArrayList<String>();
				for(int k = 0; k<X.size(); k++){
					candidate.add(X.get(k));
				}
				
				int diffNum = 0;
				for(int n = 0; n<Y.size(); n++){
					boolean match = false;
					for(int m = 0; m<X.size(); m++){
						if(Y.get(n).equals(X.get(m))){
							match = true;
							break;
						}
					}
					if(!match){
						diffNum++;
						candidate.add(Y.get(n));
					}
				}
				
				if(diffNum == 1){
					Collections.sort(candidate);
					String str = "";
					for(String strInList: candidate){
						str = str + strInList;
					}
					newCandidateSets.put(str, candidate);
				}
				
 			}
		}
		itemSets = new ArrayList<List<String>>(newCandidateSets.values());
//		System.out.println("*******Create New itemSets!");
	}
	
}
