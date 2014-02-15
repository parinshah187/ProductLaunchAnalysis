package Source;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Process {
	static short rowcount;
	static int colcount;
	static FileOutputStream out = null;
	static File f;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	
	public static void main(String[] args) {
		try {			
			ToCSV.convert();
			RunR.runscript();
			Thread.sleep(500);		
			FeatureCheck.feature();
			int[][] output = Score.fetchresult();
			int[] global = Score.getGlobal(output);
		
			print(output, global);
			String[] temp = {"Positive","Neutral","Negative"};
			for(int i=0; i<1; i++){
				int[] tmp = {output[0][i], output[1][i], output[2][i], output[3][i], output[4][i], output[5][i], output[6][i]};
				Plot.global(tmp, temp[i]);				
			}
			
			System.out.println("\n\nViewing pie-charts in browser window..");
		
			String[] contnt = {"NorthAmerica", "SouthAmerica", "Asia", "Australia", "Africa", "Europe", "Geolocation not specified"};
			for(int i=0; i<7; i++){
				int[] tmp = {output[i][0], output[i][1], output[i][2]};
				Plot.makeChart(tmp, "Analysis for Tweets - " + contnt[i]);
				Thread.sleep(100);
			}

			Plot.makeChart(global, "Global Analysis");
			new File("src/Source/CSVResult.csv").delete();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private static void print(int[][] a, int[] s){
		System.out.println("----------------------------------");
		System.out.println("Output {Positive, Neutral, Negative, Geolocation(Continent)}\n");
		
		String[] contnt = {"NorthAmerica", "SouthAmerica", "Asia", "Australia", "Africa", "Europe", "Not specified"};
		
		for(int i=0; i<7; i++){
			for(int j=0; j<3; j++){
				System.out.print(a[i][j] + " \t ");
			}								
			System.out.print(contnt[i]);			
			System.out.println();				
		}
		
		System.out.println("-----------------------");
		for(int j=0; j<3; j++)
			System.out.print(s[j] + " \t ");
		
	}
			
}