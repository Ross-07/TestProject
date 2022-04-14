package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		// converting the physical representation file in to java object.
		FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
		//converting the physical representation of workbook to java representation
		Workbook workbook = WorkbookFactory.create(fis);
		//getting the control of the cell and creating the cell
		Cell cell = workbook.getSheet("Sheet2").getRow(1).createCell(3);
		//passing the value to the particular cell
		cell.setCellValue("Pass");
		//converting the java representation file to physical representation and saving
		FileOutputStream fos = new FileOutputStream("./src/test/resources/Book1.xlsx");
		//converting the java representation of the workbook into physical representation of the workbook
		workbook.write(fos);
		workbook.close();
	}

}
