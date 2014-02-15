package Source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FeatureCheck {
	
	public static void feature(){
		
		BufferedReader mycsv;
		String cvsSplitBy = ",";
		String line = "";
		String[] data;
		File f = new File("E:/Workspace/TwitterStreamPlot/src/Source/FeatureResult.csv");
		File f1 = new File("src/Source/FResult.csv");
		
		try {			
			if(f1.exists()){
				System.out.println("Deleting Existing Output File..");
				f1.delete();
				f1.createNewFile();
			}
			
			FileOutputStream out = new FileOutputStream(f1, true);			
			mycsv = new BufferedReader(new FileReader(f));
			while ((line = mycsv.readLine()) != null) {
				data = line.split(cvsSplitBy);
				if(!(data[3].equals("0"))){
					String tweet = data[1] + ",";
					String content = tweet + data[2] + "\n";
					byte[] b = content.getBytes();
					out.write(b);
				}
			}
			mycsv.close();
			System.out.println("Deleting FeatureResult.csv..");
			f.delete();
			System.out.println("Deleted");
			out.close();
			RunR.runfeaturescript();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}	

}
