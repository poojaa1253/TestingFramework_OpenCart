package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {	
	// two  for loop one for row one for column
	
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpencartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	// we are not using the driver here hence we are creating static methods.
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for (int i=0; i<=sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i) == null) continue; // Skip null rows
				  for(int j=0; j<=sheet.getRow(i).getLastCellNum(); j++) {
					  if (sheet.getRow(i+1) != null && sheet.getRow(i+1).getCell(j) != null) {
					 data[i][j]= sheet.getRow(i+1).getCell(j).toString();
					  }
				}					
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
		
		
	}

}
