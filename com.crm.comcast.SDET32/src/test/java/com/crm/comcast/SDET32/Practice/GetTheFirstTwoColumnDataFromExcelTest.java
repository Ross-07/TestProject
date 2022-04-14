package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class GetTheFirstTwoColumnDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		int rowcount = sheet.getLastRowNum();
		for (int i = 1; i <= rowcount; i++) 
		{
			Row row = sheet.getRow(i);
			String firstColData = row.getCell(0).getStringCellValue();
			String SecondColData = row.getCell(1).getStringCellValue();
			System.out.println(firstColData+"\t"+SecondColData);
		}
		
		
	}

}
