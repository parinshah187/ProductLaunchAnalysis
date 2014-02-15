package Source;

import static com.googlecode.charts4j.Color.*;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;


public class Plot {
	
	public static void makeChart(int[] global, String title) {
	    	
		int total = global[0] + global[1] + global[2];
		
		int[] perc = {(int)((global[0]*100)/total), (int)((global[1]*100)/total), (int)((global[2]*100)/total)};		
		
		Slice s1 = Slice.newSlice(perc[0], Color.newColor("3366CC"), Integer.toString(perc[0]) + "%", "Positive");
	    Slice s2 = Slice.newSlice(perc[1], Color.newColor("990099"), Integer.toString(perc[1]) + "%", "Neutral");
	    Slice s3 = Slice.newSlice(perc[2], Color.newColor("DD4477"), Integer.toString(perc[2]) + "%", "Negative");

	    PieChart chart = GCharts.newPieChart(s1, s2, s3);
	    chart.setTitle(title, BLACK, 16);
	    chart.setSize(500, 200);
	    chart.setThreeD(true);	        
	    String myURL = chart.toURLString();
	       		    
        BareBonesBrowserLaunch.openURL(myURL);
	}
	
/*	public static void main(String[] args) throws URISyntaxException{
		int[] n = {100,200,150,200,50,124,243};
		global(n, "Sample");
	}
*/
	
	public static void global(int[] global, String title)
    {
		System.out.println();
		int total = global[0] + global[1] + global[2] + global[3] + global[4] + global[5] + global[6];
		
		int[] perc = {(int)((global[0]*100)/total), (int)((global[1]*100)/total), (int)((global[2]*100)/total), (int)((global[3]*100)/total), (int)((global[4]*100)/total), (int)((global[5]*100)/total), (int)((global[6]*100)/total)};
		
		String[] contnt = {"NorthAmerica", "SouthAmerica", "Asia", "Australia", "Africa", "Europe", "Geolocation not specified"};
		
    	Slice s1 = Slice.newSlice(perc[0], Color.newColor("3366CC"), Integer.toString(perc[0]) + "%", contnt[0]);
	    Slice s2 = Slice.newSlice(perc[1], Color.newColor("990099"), Integer.toString(perc[1]) + "%", contnt[1]);
	    Slice s3 = Slice.newSlice(perc[2], Color.newColor("DD4477"), Integer.toString(perc[2]) + "%", contnt[2]);
	    Slice s4 = Slice.newSlice(perc[3], Color.newColor("006400"), Integer.toString(perc[3]) + "%", contnt[3]);
	    Slice s5 = Slice.newSlice(perc[4], Color.newColor("2E0854"), Integer.toString(perc[4]) + "%", contnt[4]);
	    Slice s6 = Slice.newSlice(perc[5], Color.newColor("E6693E"), Integer.toString(perc[5]) + "%", contnt[5]);
	    Slice s7 = Slice.newSlice(perc[6], Color.newColor("F3B49F"), Integer.toString(perc[6]) + "%", contnt[6]);
	    
	    PieChart chart = GCharts.newPieChart(s1, s2, s3, s4, s5, s6, s7);

	    chart.setTitle(title + " Tweets over the Globe ", BLACK, 16);
	    chart.setSize(500, 200);
	    chart.setThreeD(true);
	    String myURL = chart.toURLString();
	        
	    BareBonesBrowserLaunch.openURL(myURL);
                
    }
 
}