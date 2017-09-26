/**
 * 
 */
package com.filescanner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Roopam
 *
 */
public class FileScanningUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Path fileDir=Paths.get("C:/Users/Roopam/test/KDB");
			args=new String[]{"01/08/2017 09:10:20","01/08/2017 13:10:20"};
			SimpleDateFormat dtfmtObj=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat dtfmtObj1=new SimpleDateFormat("yyyyMMddHHmm");
			final Date startDate=dtfmtObj.parse(args[0]);
			final Date endDate=dtfmtObj.parse(args[1]);
			SimpleFileVisitor visitor=new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult preVisitDirectory(Path arg0, BasicFileAttributes arg1)
						throws IOException {
					//System.out.println("About to be visited"+ arg0.getRoot());
					SimpleDateFormat dtfmtObj2=null;
					String path=arg0.toString();
					Calendar cal = Calendar.getInstance();
					String lstSegmt = path.substring(path.lastIndexOf('\\') + 1);
					if(lstSegmt.length()==12){
						 dtfmtObj2=new SimpleDateFormat("yyyyMMddHHmm");
					}
					else{
						 dtfmtObj2=new SimpleDateFormat("yyyyMMddHH");
					}
					if(lstSegmt.length()==1 || !lstSegmt.matches("-?\\d+(\\.\\d+)?"))
						return FileVisitResult.CONTINUE;
					try {
						Date date=dtfmtObj2.parse(lstSegmt);
						cal.setTime(date);
						if(date.compareTo(startDate)>0 && date.compareTo(endDate) <0 ){
							System.out.println(arg0);
						} 
					} catch (ParseException e) {						
						e.printStackTrace();
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path arg0, BasicFileAttributes arg1)
						throws IOException {
					if(arg1.isRegularFile()){
						System.out.println("Regular file");
					}
					return FileVisitResult.CONTINUE;
				}

			};
			Files.walkFileTree(fileDir, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
