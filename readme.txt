COMS 6111 Project 3
a.
Yiyang Qiu yq2183
YaZhuo Nan  yn2287
b. list of all the files that you are submitting
	Main.java
	Apriori.java
	AssociationRule.java
	FileParser.java
	INTEGRATED-DATASET.csv
	ReadMe.txt
	example-run.txt
	DOHMH_New_York_City_Restaurant_Inspection_Results.csv

c.
A detailed description explaining: 
(a) which NYC Open Data data set(s) you used to generate the INTEGRATED-DATASET file; 
	We used the "DOHMH_New_York_City_Restaurant_Inspection_Results.csv" file from the NYC Open Data website.
	https://data.cityofnewyork.us/Health/DOHMH-New-York-City-Restaurant-Inspection-Results/xx67-kt59
	The data contains serveral columns that are not used in  our program analysis. 
	The final integrated csv file contains "DBA", "BORO", "ZIPCODE", "CUSINE DESCRIPTION", "INSPECTION", "CRITICAL FLAG", "SCORE", "GRADE" columns. DBA indicates restarurants' name, BORO is the location of the restaurants.  
	

(b) what (high-level) procedure you used to map the original NYC Open Data data set(s) into your INTEGRATED-DATASET file; 
We delete several useless columns to increase the procession of our prgramming procedure. For instance, "Camis" and "phone" represent the business ID and phone number, which cannot show special relation with location and cusine description. details related to violation are also deleted from the table. In addition, the table contains 400,000 rows that is too big to run the entire table through program. We delete the last 310,000 rows to show persuasive result.  

(c) what makes your choice of INTEGRATED-DATASET file interesting (in other words, justify your choice of NYC Open Data data set(s)). The explanation should be detailed enough to allow us to recreate your INTEGRATED-DATASET file exactly from scratch from the NYC Open Data site. 
Data Set we choose is "DOHMH New York City Restaurant Inspection Results".
This dataset is large enough to provide information about the restaurants in different locations in the NewYork city, which provides persuasive conclusion for the relationship between different wordsets. For instance, the word "brooklyn" is really strongly related to "critical", which means the restaurants located in brooklyn are critical. "American" cusine is strongly correlated with "manhattan", which means the location manhattan mainly has american style's restaurants. 

d. 
A clear description of how to run your program (note that your project must compile/run under Linux in your CS account)
$ javac *.java
$ java Main
>Please type in min_supp and min_conf, filename has been encoded:
>Input like below: 
>0.05 0.5
We then type the support constant and confidence constant(both min threshold) to get the result.
It should take a while to obtain final result.

e.
A clear description of the internal design of your project; in particular, if you decided to implement variation(s) of the original a-priori algorithm (see above), you must explain precisely what variation(s) you have implemented and why
	
	Program starts from main function in Main.java. It first reads input and then parse file through FileParser.java.
	FileParser.java parse the INTEGRATED-DATASET file and return all transactions in a list of lists. Each transaction is a list of string within result.
	Assign parsed results to allTransacitons in Apriori.java. Then call aprioir function in Apriori.java. It generates basic itemsets with each size one itemset through createSizeOneItemSets function. As long as itemSets is not empty, keep looping on itemsets. In the loop, first update itemsets with calFrquItemSets function, which only remains itemset with support higher than min_supp, storing them in itemSetSupport map. Then use new itemset to generate new itemSets with greater size candidate sets and filter them with calFreqItemSets again. Keep this loop until itemSets is empty.With the final itemSetSupport map, whose key is arrayList<String> contains the list of itemSets(1, 2, 3 wordList), and its value is the support constant of each list, we then write "AssociationRule.java" file to obtain confidence constant of each combination of itemSets by the form "left => right". The confidence calculated by
		the support number of both left and right lists divided by the support constant of the left list.
 	If the itemSets' support constant and confidence number are both larger than the min threshold, we keep the results to the final output. 
 
f. 
The command line specification of an interesting sample run (i.e., a min_sup, min_conf combination that produces interesting results). Briefly explain why the results are interesting.
The interesting result we get through running:
1. 
[american,manhattan] => [critical] (Conf: 53.7424664636122%,Supp: 9.793917921464423%)
[italian] => [manhattan] (Conf: 64.02461538461537%,Supp: 6.143489813994686%)
American and Italian cusine appear most frequently in the area of manhattan. The confidence number is relatively high compared with 
other sets of results. It may indicate the most majority of people live in "manhattan" area are americans and italians.
2. 
[queens] => [critical] (Conf: 54.78741790528863%,Supp: 11.231178033658104%)
[manhattan] => [critical] (Conf: 55.07253361607788%,Supp: 26.990256864481843%)
[brooklyn] => [critical] (Conf: 54.4681399394369%,Supp: 9.984056687333924%)
The restaurants in queens, brooklyn and manhattans are strongly correlated to "critical" related to violation of restaurtant. It indicates that the food in these locations have some critcal health issues. 





