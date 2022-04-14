package com.crm.comcast.SDET32.GenericLibrary;

public interface IPathConstant
{
	int IMPLICIT_WAIT_TIME = 20;
	int EXPLICIT_WAIT_TIME = 20;
	int FLUENT_WAIT_TIME = 20;
	int POLLING_PERIOD = 250;
	int THREAD_SLEEP = 10000;
	
	
	String PROPERTY_FILE_PATH = "./src/test/resources/PropertyFiless.properties";
	String EXCEL_FILE_PATH =  "./src/test/resources/PurchaseOrder.xlsx";
	String JSON_FILE_PATH = "./src/test/resources/JsonFile.json";
	
	String USERNAME_KEY = "username";
	String PASSWORD_KEY = "password";
	String BROWSER_KEY = "browser";
	
	String SHEET_NAME3 ="Sheet3";
	String SHEET_NAME1 = "Sheet1";
	String SHEET_NAME2 ="Sheet2";
	
	
	String CHROME_BROWSER_KEY = "browser";
	String CHROME_BROWSER_VALUE = "./chromedriver.exe";
	String URL_KEY = "url";
}
