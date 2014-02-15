package Source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class tp {

	static short rowcount = 0;

	static FileOutputStream out = null;
	static File f;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f  = new File("src/Source/S4PostLaunch.xls");
		if(f.exists()){			
			InputStream myxls;
			try {
				myxls = new FileInputStream("src/Source/S4PostLaunch.xls");
				workbook = new HSSFWorkbook(myxls);
				sheet = workbook.getSheetAt(0);
				//System.out.println(sheet.getLastRowNum());
				rowcount = (short)(sheet.getLastRowNum() + 1);
				System.out.println("Existing number of rows : " + rowcount);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}				
		int j=0;
		for(int i=10000; i<13000; i++){
			//if(sheet.getRow(i).getCell(1).getStringCellValue().equals(null) || sheet.getRow(i).getCell(1).getStringCellValue().equals("")){
			if(sheet.getRow(i).getCell(1).getStringCellValue().equals("Australia")){
						j++;
				//String[] contnt = {"NorthAmerica", "SouthAmerica", "Asia", "Australia", "Africa", "Europe", "Geolocation not specified"};
				
				String[] contnt = {"SouthAmerica", "Africa"};
				Random r = new Random();
				int index = r.nextInt(2);					  
				sheet.getRow(i).getCell(1).setCellValue(contnt[index]);
			}
			//else{
				//System.out.println("Not NULL");
			//}
		}
		System.out.println(j + " Values Modified");
		  try {
			out = new FileOutputStream(f);
			workbook.write(out);
			out.flush();
			out.close();	
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			 					  

	}

}
