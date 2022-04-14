package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOpportunityUsingOrganizationTest {

	public static void main(String[] args) throws IOException 
	{
		WebDriver driver = null;
		//Generating Random Number
		Random random = new Random();
		int RandomNumber = random.nextInt(10000);
		
		Date date = new Date();
		int day = date.getDate();
		int month = date.getMonth();
		String year = date.toString().split(" ")[5];
		String calDate = year+"-0"+month+"-"+day;
		System.out.println(calDate);
		
		
		//Getting the data from Property File
		FileInputStream fisForProp = new FileInputStream("./src/test/resources/PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fisForProp);
		
		String Browser = property.getProperty("browser");
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		
		//Getting the Data from excel sheet
		FileInputStream fisforExcel = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisforExcel);
		Sheet sheet = workbook.getSheet("Sheet2");
		String organizationName = sheet.getRow(3).getCell(2).getStringCellValue()+RandomNumber;
		String FirstName = sheet.getRow(3).getCell(4).getStringCellValue();
		String LastName = sheet.getRow(3).getCell(5).getStringCellValue();
		String opportunityName = sheet.getRow(3).getCell(3).getStringCellValue();
		String saleStage = sheet.getRow(3).getCell(7).getStringCellValue();		
		
		if(Browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(Browser.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser Has Not Been Found");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Navigating to the Application
		driver.get(url);
		//Login Action
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//creating organization to remove data dependency 
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verification
		String actualOrganizationName = driver.findElement(By.className("dvHeaderText")).getText();
		if(actualOrganizationName.contains(organizationName))
		{
			System.out.println("Pass:Organization has not been created");
		}
		else
		{
			System.out.println("fail:Organization has not created");
		}
		
		//creating contacts
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		Select select = new Select(driver.findElement(By.name("salutationtype")));
		select.selectByVisibleText("Mr."); 
		driver.findElement(By.name("firstname")).sendKeys(FirstName);
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//td[text()='Organization Name 			']/parent::tr/descendant::img[@src='themes/softed/images/select.gif']")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		for (String Window : childWindow) 
		{
			driver.switchTo().window(Window);
			if (!parentWindow.equals(Window)) 
			{
				driver.findElement(By.id("search_txt")).sendKeys(organizationName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(""+organizationName+"")).click();
				break;
			}
		}
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    String actualContactName = driver.findElement(By.className("dvHeaderText")).getText();
	    
	    //verification
	    if (actualContactName.contains(LastName)) 
	    {
			System.out.println("pass: contact has been created");
		}
	    else 
	    {
			System.out.println("Fail:contact has not been created");
		}
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(opportunityName);
		driver.findElement(By.xpath("//td[text()='Amount: ($) 			']/parent::tr/descendant::img")).click();
		String ParentWindowOpportunity = driver.getWindowHandle();
		Set<String> ChildWindowOpportunity = driver.getWindowHandles();
		for (String Window : ChildWindowOpportunity) 
		{
			driver.switchTo().window(Window);
			if (!ParentWindowOpportunity.equals(Window)) 
			{
				driver.findElement(By.id("search_txt")).sendKeys(organizationName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(""+organizationName+"")).click();
				break;
			}
		}
		driver.switchTo().window(ParentWindowOpportunity);
		driver.findElement(By.name("closingdate")).sendKeys(calDate);
		Select select1 = new Select(driver.findElement(By.name("sales_stage")));
		select1.selectByVisibleText("salesStage");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualopportunityName = driver.findElement(By.className("dvHeaderText")).getText();
		if (actualopportunityName.contains(opportunityName)) 
		{
			System.out.println("pass:opportunity has been created");
		}
		else
		{
			System.out.println("Fail:opportunity has not been created");
		}
		
		//logout Action
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		//closing the workbook
		workbook.close();
		//closing the browser and the server
		driver.quit();
		
	}

}
