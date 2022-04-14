package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Practice001 {

	public static void main(String[] args) throws IOException 
	{
		Random random= new Random();
		int randomValue = random.nextInt(10000);
		// converting the physical representation file into java representation file.
				FileInputStream fisOfExcel = new FileInputStream("./src/test/resources/Book1.xlsx");
				// converting the physical representation workbook into java representation.
				Workbook workbook = WorkbookFactory.create(fisOfExcel);
				// getting the control of the sheet.
				Sheet sheet = workbook.getSheet("Sheet2");
				//getting the control of the row
			    Row row = sheet.getRow(1);
			    // getting the control of the cell
			    Cell cell = row.getCell(2);
			    // converting cell type object into string type
			   String data = cell.getStringCellValue();
			   //printing the value into the console
			   System.out.println(data);
			   String organisationName = data+randomValue;
		WebDriver driver = null;
		FileInputStream fisOfProperty = new FileInputStream("./PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fisOfProperty);
		String BROWSER = property.getProperty("Browser");
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("username");
		String PASSWORD = property.getProperty("password");
		
		
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		
		else if(BROWSER.equals("FireFox"))
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organisationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualTitle = driver.findElement(By.className("dvHeaderText")).getText();
		String expectedTitle = organisationName;
		
		if(actualTitle.contains(expectedTitle))
		{
			System.out.println("Pass:Organization has been created");
		}
		else
		{
			System.out.println("Fail:Organizations has not been created");
		}
		
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
