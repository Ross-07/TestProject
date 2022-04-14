package com.crm.comcast.SDET32.Practice;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateContactWithOrganizationNameTest {

	public static void main(String[] args) throws IOException 
	{
		WebDriver driver = null;
		//Generating random Number.
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		
		
		FileInputStream fisForProperty = new FileInputStream("./src/test/resources/PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fisForProperty);
		String browser = property.getProperty("Browser");
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		//getting the data for excel sheet
		FileInputStream fisForExcel = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		Sheet sheet = workbook.getSheet("Sheet2");
		String organizationName = sheet.getRow(2).getCell(0).getStringCellValue()+randomNumber;
		String LastName = sheet.getRow(2).getCell(3).getStringCellValue()+randomNumber;
		String FirstName = sheet.getRow(2).getCell(4).getStringCellValue()+randomNumber;
		
		//Launching The Browser
		if (browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("Firefox"))
		{
			driver  = new FirefoxDriver();
		}
		else
		{
			System.out.println("browser does not found");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// navigating the application
		driver.get(url);
		// login action
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
		if (actualOrganizationName.contains(organizationName)) 
		{
			System.out.println("Pass:Organization has been created");
		}
		else
		{
			System.out.println("fail: organization has not been created");
		}
		
		// creating contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		Select select = new Select(driver.findElement(By.name("salutationtype")));
		select.selectByVisibleText("Mr.");
		driver.findElement(By.name("firstname")).sendKeys(FirstName);
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//td[text()='Organization Name 			']/parent::tr/descendant::img[@src='themes/softed/images/select.gif'"));
		
		//handle window
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String window : childWindows) 
		{
			driver.switchTo().window(window);
			if (!parentWindow.equals(window)) 
			{
				driver.findElement(By.id("search_txt")).sendKeys(organizationName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(""+organizationName+"")).click();
				break;
			}
		}
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]")).click();
		String actualContactName = driver.findElement(By.className("dvHeaderText")).getText();
		
		//verification
		if (actualContactName.contains(LastName))
		{
			System.out.println("pass:contact has been created");
		}
		else
		{
			System.out.println("Fail: Contact has not been created");
		}
		
		//Logout Action
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//closing the workbook
		workbook.close();
		//closing the browser and server 
		driver.quit();
	
		
		
		
		
		
	}

}
