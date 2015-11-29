import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class FileParser {
	public static List<List<String>> parseCSV(String filename){
		List<List<String>> res = new LinkedList<List<String>>();
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			int i = 0;
			while ((line = br.readLine()) != null){
				res.add(new LinkedList<String>());
				String [] oneTransaction = line.split(",");
				for(int j = 0; j<oneTransaction.length; j++){
					oneTransaction[j] = oneTransaction[j].trim().toLowerCase();
				}
				for(int j = 0; j<oneTransaction.length; j++){
					if(!oneTransaction[j].equals("") && !oneTransaction[j].equals("\\s+")){				
						res.get(i).add(oneTransaction[j]);
					}
				}
				i++;
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
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
		return res;
	}
}
