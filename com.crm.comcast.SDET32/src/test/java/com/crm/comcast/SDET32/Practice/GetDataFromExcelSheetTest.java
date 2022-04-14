package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelSheetTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		// converting the physical representation file into java representation file.
		FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
		// converting the physical representation workbook into java representation.
		Workbook workbook = WorkbookFactory.create(fis);
		// getting the control of the sheet.
		Sheet sheet = workbook.getSheet("sheet1");
		//getting the control of the row
	    Row row = sheet.getRow(1);
	    // getting the control of the cell
	    Cell cell = row.getCell(0);
	    // converting cell type object into string type
	   String data = cell.getStringCellValue();
	   //printing the value into the console
	   System.out.println(data);
	   workbook.close();
	}

}
