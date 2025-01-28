package com.qa.gorest.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	private Workbook work;
	private Sheet sheet;
	public Object[][] getData(String filePath,String sheetName) {
		
	   Object[][] data=null;
	   
	   try {
		FileInputStream fis=new FileInputStream(filePath);
	     work=WorkbookFactory.create(fis);
	     sheet=work.getSheet(sheetName);
	     data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	     for(int rowNum=1;rowNum<sheet.getLastRowNum();rowNum++) {
	    	  for(int colNum=0; colNum<sheet.getRow(0).getLastCellNum();colNum++) {
	    		  data[rowNum][colNum]=sheet.getRow(rowNum).getCell(colNum);
	    	  }
	    	 
	     }
	   }
	   catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return data;
	 
		
		
	}

}
