package Source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Score {
	
	static File f;
	static String[] data; 
	static int[][] ana = new int[7][3];
	
	private static void increment(int n){
		if(Integer.parseInt(data[1]) > 0)
			ana[n][0]++;
		else if(Integer.parseInt(data[1]) < 0)
			ana[n][2]++;
		else 
			ana[n][1]++;		 			
	}
	
	public static int[][] fetchresult(){
		f  = new File("src/Source/result.csv");

		/*
		 * 	[x][0] = Positive
		 * 	[x][1] = Neutral
		 * 	[x][2] = Negative
		 *  
		 *  for 
		 *	x=1: NorthAmerica
		 * 	x=2: SouthAmerica
		 * 	x=3: Asia
		 * 	x=4: Australia
		 * 	x=5: Africa
		 * 	x=6: Europe
		 * 	x=7: Null
		 */
		
		
		BufferedReader mycsv;
		String cvsSplitBy = ",";
		String line = "";
		
		try {
			mycsv = new BufferedReader(new FileReader(f));
			while ((line = mycsv.readLine()) != null) {
				data = line.split(cvsSplitBy);
				if(data[3].equals("\"NorthAmerica\"")){
					increment(0);
				}
				else if(data[3].equals("\"SouthAmerica\"")){
					increment(1);
				}
				else if(data[3].equals("\"Asia\"")){
					increment(2);
				}
				else if(data[3].equals("\"Australia\"")){
					increment(3);
				}
				else if(data[3].equals("\"Africa\"")){
					increment(4);
				}
				else if(data[3].equals("\"Europe\"")){
					increment(5);
				}
				else if(data[3].equals("\"NULL\"")){
					increment(6);
				}
			}			
			mycsv.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return ana;
	}
	
	public static int[] getGlobal(int[][] n){
		int[] sum = new int[3];
		
		int[][] output = n;
		
		for(int i=0; i<7; i++){
			for(int j=0; j<3; j++){
				sum[j] = sum[j] + output[i][j];
			}								
		}		
		return sum;
	}
	
	
}
