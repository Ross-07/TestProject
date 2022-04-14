package com.crm.comcast.SDET32.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getStringDataFromExcelSheet(String sheetName, int rowNo, int CellNo) throws EncryptedDocumentException, IOException
	{
		// converting the physical representation of the file in java representation
		FileInputStream fisFromExcel = new FileInputStream("./src/test/resources/PurchaseOrder.xlsx");
		// converting the physical representation workbook into java representation
		Workbook workbook = WorkbookFactory.create(fisFromExcel);
		//getting the control of the sheet
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(CellNo).getStringCellValue();
	}
	public double getNumericDataFromExcelSheet(String sheetName, int rowNo, int cellNo) throws EncryptedDocumentException, IOException
	{
		// converting the physical representation of the file in java representation
		FileInputStream fisFromExcel = new FileInputStream("./src/test/resources/Book1.xlsx");
		// converting the physical representation workbook into java representation
		Workbook workbook = WorkbookFactory.create(fisFromExcel);
		//getting the control of the sheet
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getNumericCellValue();
	}
	public boolean getBooleanDataFromExcelSheet(String sheetName,int rowNo, int cellNo) throws EncryptedDocumentException, IOException
	{
		// converting the physical representation of the file in java representation
		FileInputStream fisFromExcel = new FileInputStream("./src/test/resources/Book1.xlsx");
		// converting the physical representation workbook into java representation
		Workbook workbook = WorkbookFactory.create(fisFromExcel);
		//getting the control of the sheet
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getBooleanCellValue();
	}
	
	
	public void writeValueToExcelSheet(String sheetName,int rowNo, int cellNO,String value) throws EncryptedDocumentException, IOException
	{
		// converting the physical representation of the file in java object
		String filePath = "./src/test/resources/Book1.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		// converting the physical representation workbook into java representation
		Workbook workbook = WorkbookFactory.create(fis);
		// getting the control of the cell and creating the cell
		 workbook.getSheet(sheetName).getRow(rowNo).createCell(cellNO).setCellValue(value);
		 //passing the value of the particular script
		 //converting the java representation of the workbook into physical representation and save it
		FileOutputStream fos = new FileOutputStream(filePath);
		//converting the java representation of the workbook into physical representation of the workbook
		workbook.write(fos);
		System.out.println("Data is updated successfully");
		workbook.close();
		 
		
		
		
	}
	
}
