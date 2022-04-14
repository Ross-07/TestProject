package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetMultipleDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowcount = sheet.getPhysicalNumberOfRows();
		int colcount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(rowcount);
		System.out.println(colcount);
		Object[] []data = new Object[rowcount][colcount];
		for (int i = 0; i < rowcount; i++) 
		{
			for (int j = 0; j < colcount; j++)
			{
				data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		for (Object[] a : data) 
		{
			for (Object b : a) 
			{
				System.out.print(b+"\t");
			}
			System.out.println();
		}
		
	
	}

}
