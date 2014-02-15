package Source;

import java.io.File;

import rcaller.RCaller;
 
 
public class RunR{
 
    public static void runscript() {
        try {
        	File f = new File("src/Source/result.csv");
        	if(f.exists()){
        		f.delete();
        	}
        	RCaller caller = new RCaller();
            caller.setRscriptExecutable("E:/R/R-3.0.2/bin/x64/Rscript.exe");
            caller.setRExecutable("E:/R/R-3.0.2/bin/x64/Rscript.exe");
            caller.cleanRCode();
            Runtime.getRuntime().exec("E:/R/R-3.0.2/bin/x64/Rscript.exe E:/Workspace/TwitterStreamPlot/src/Source/run.R");   
                        
            while(!f.exists()){
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void runfeaturescript() {
    	try {
        	File f = new File("src/Source/Features.csv");
        	if(f.exists()){
        		f.delete();
        	}
        	RCaller caller = new RCaller();
            caller.setRscriptExecutable("E:/R/R-3.0.2/bin/x64/Rscript.exe");
            caller.setRExecutable("E:/R/R-3.0.2/bin/x64/Rscript.exe");
            caller.cleanRCode();
            Runtime.getRuntime().exec("E:/R/R-3.0.2/bin/x64/Rscript.exe E:/Workspace/TwitterStreamPlot/src/Source/runFAnalysis.R");   
                        
            while(!f.exists()){
            	
            }
            new File("src/Source/FResult.csv").delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
