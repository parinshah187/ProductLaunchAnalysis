package Source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ToCSV {
	
	static short rowcount;
	static int colcount;
	static FileOutputStream out = null;
	static File f;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	
	
	public static void convert(){
		f  = new File("src/Source/S4PostLaunch.xls");
		if(f.exists()){			
			InputStream myxls;
			try {
				myxls = new FileInputStream("src/Source/S4PostLaunch.xls");
				workbook = new HSSFWorkbook(myxls);
				sheet = workbook.getSheetAt(0);
				rowcount = (short)(sheet.getLastRowNum() + 1);
				colcount = sheet.getRow(0).getLastCellNum();
				
				System.out.println("Number of rows in xls : " + rowcount);
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else{
			System.out.println("File does not exist");
			System.exit(0);
		}

		try {        	        				
	        File f1 = new File("src/Source/CSVResult.csv");
	        if(f1.exists()){
	        	if(f1.delete()){
	        		System.out.println("Existing file deleted.");
	        	}
	        }
	        System.out.println("Creating new file..");
			f1.createNewFile();
			out = new FileOutputStream(f1, true);
			
			byte[] b = null;

			for(int i = 0; i < rowcount; i++){
				String content = "";
				for(int j = 0; j < colcount; j++){
					if(j!=(colcount-1))
						content = content + "\"" + sheet.getRow(i).getCell(j).getStringCellValue() + "\",";						
					else
						content = content + "\"" + sheet.getRow(i).getCell(j).getStringCellValue() + "\"\n";						
				}
            	b = content.getBytes();				
        		out.write(b);        		
			}			
			System.out.println("Converted to csv successfully");
			out.close();
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e) {
    			e.printStackTrace();
    			System.out.println("Exception thrown while writing data in excel file");
    	}

	}
}
