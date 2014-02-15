package Source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterSearch {

	
	static short rowcount = 0;

	static FileOutputStream out = null;
	static File f;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	
	public twitterSearch(){
		f  = new File("src/Source/S4PostLaunch.xls");
		if(f.exists()){			
			InputStream myxls;
			try {
				myxls = new FileInputStream("src/Source/S4PostLaunch.xls");
				workbook = new HSSFWorkbook(myxls);
				sheet = workbook.getSheetAt(0);
				rowcount = (short)(sheet.getLastRowNum() + 1);
				System.out.println("Existing number of rows : " + rowcount);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}							
		else{		
			try {				
				f.createNewFile();
				workbook = new HSSFWorkbook();
				sheet = workbook.createSheet("Sample sheet");				
				System.out.println("New File Created");
				HSSFRow row = sheet.createRow(rowcount);
				row.createCell(0).setCellValue("Tweet");
				row.createCell(1).setCellValue("Continent");
				rowcount++;
				  
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	
	public void search(){
		  
		  ConfigurationBuilder cb = new ConfigurationBuilder();

		  cb.setDebugEnabled(true);
		  cb.setOAuthConsumerKey("etJGQZ7VNIdIQQVibPQg");
	      cb.setOAuthConsumerSecret("iFVJ63lo7JrXdTlH8Kpb3l02WmdhpEHCeFNiA9UXYxI");
	      cb.setOAuthAccessToken("153641276-1TzewMZ7e5y6Oa9WuMck8F4gLRNPhLzQ6HQAzxIG");
	      cb.setOAuthAccessTokenSecret("6gO7k01FZ6VI5uwQFfSSgYqwF1kEUWPnEIIHD0YnHMJsL");
		  
		  /*
          cb.setOAuthConsumerKey("J22eYgVIOWT3bPVAFWk6iw");
          cb.setOAuthConsumerSecret("gr26IqcKSWk4wz5yNi2cOVHdaXx2w9X71efu9eQBlo");
          cb.setOAuthAccessToken("631118946-AVRPOL3QCBZtCfbHHr3xZveHnrafsBCZUSbjlLgL");
          cb.setOAuthAccessTokenSecret("umdvNwUxOg9NkwneV1RVkgDCSa3phaLWQVxKu1dhblHW1");
		   */
          Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		  Query query = new Query("Galaxy S4" + " +exclude:retweets");
		  query.setLang("en");
		  query.setUntil("2013-04-26");
		  int numberOfTweets = 15000;
		  long lastID = Long.MAX_VALUE;
		  ArrayList<Status> tweets = new ArrayList<Status>();
		  while (tweets.size () < numberOfTweets) {
		  
			if (numberOfTweets - tweets.size() > 100)
		      query.setCount(100);
		    else 
		      query.setCount(numberOfTweets - tweets.size());
		    try {
		      QueryResult result = twitter.search(query);
		      tweets.addAll(result.getTweets());
		      for (Status t: tweets) 
		        if(t.getId() < lastID) lastID = t.getId();
		  
		    }

		    catch (TwitterException te) {
		    	System.out.println("Couldn't connect: " + te);
		    }; 
		    query.setMaxId(lastID-1);
		  }

		  for (int i = 0; i < tweets.size(); i++) {
		   
			  String content = "";
			  String contn = "";

			  Status status = (Status) tweets.get(i);				
			  
			  HSSFRow row = sheet.createRow(rowcount);
			  HSSFCell cell = row.createCell(0);
			  HSSFCell cell1 = row.createCell(1);
			
			  cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			  cell1.setCellType(HSSFCell.CELL_TYPE_STRING);

			  try{
				  content = status.getText();                
				  String tmpcontent = content;
				  tmpcontent = tmpcontent.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", " ");
				  content = tmpcontent.replace("\"", " ");

				  double lon = status.getGeoLocation().getLongitude();
	              double lat = status.getGeoLocation().getLatitude();
				  double ASLAL = 11.62, ASLAU = 81.86, ASLOL = 27.33, ASLOU = 169.02; 
				  double AULAL = -48, AULAU = -4, AULOL = 112, AULOU = 180;
				  double AFLAL = -35, AFLAU = 35, AFLOL = -15, AFLOU = 52;
				  double NALAL = 5, NALAU = 85 , NALOL = -165, NALOU =-15;
				  double SALAL = -55, SALAU =12  , SALOL =-84   , SALOU = -35 ;
				  double ELAL = 35, ELAU = 80 , ELOL = -10  , ELOU = 65 ;
					
				  if(status.getGeoLocation()!=null){
							
					  if(lat <= ASLAU && lat >=ASLAL && lon <=ASLOU && lon >= ASLOL){
						  contn="Asia";
					  }
					  else if(lat <= AULAU && lat >=AULAL && lon <=AULOU && lon >= AULOL){
								contn="Australia";
					  }
					  else if(lat <= AFLAU && lat >=AFLAL && lon <=AFLOU && lon >= AFLOL){
						  contn="Africa";
					  }
					  else if(lat <= NALAU && lat >=NALAL && lon <=NALOU && lon >= NALOL){
						  contn="NorthAmerica";
					  }
					  else if(lat <= SALAU && lat >=SALAL && lon <=SALOU && lon >= SALOL){
						  contn="SouthAmerica";
					  }
					  else if(lat <= ELAU && lat >=ELAL && lon <=ELOU && lon >= ELOL){
						  contn="Europe";
					  }
					  else{
						  contn = "NULL";
					  }
				  }
				  else{				
					  String[] contnt = {"NorthAmerica", "SouthAmerica", "Asia", "Australia", "Africa", "Europe", "NULL"};
					  Random r = new Random();
					  int index = r.nextInt(7);					  
					  contn = contnt[index];
				  }
				  
			  }catch(NullPointerException e){
				  e.printStackTrace();
			  }
			
			  cell.setCellValue(content);        		
			  cell1.setCellValue(contn);        		
			  rowcount++;            		        			
			
			  try {
				out = new FileOutputStream(f);
				workbook.write(out);
				out.flush();
				out.close();	
			  } catch (IOException e) {
				e.printStackTrace();
			}			 					  
		  }
		}
	
	
	public static void main(String[] args) {
		new twitterSearch().search();
	}

	
	
}
