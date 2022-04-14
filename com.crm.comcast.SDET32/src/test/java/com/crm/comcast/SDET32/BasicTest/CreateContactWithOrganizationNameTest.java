package com.crm.comcast.SDET32.BasicTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.comcast.SDET32.GenericLibrary.ExcelUtility;
import com.crm.comcast.SDET32.GenericLibrary.FileUtility;
import com.crm.comcast.SDET32.GenericLibrary.JavaUtility;
import com.crm.comcast.SDET32.GenericLibrary.WebDriverUtility;

public class CreateContactWithOrganizationNameTest {

	public static void main(String[] args) throws IOException, InterruptedException  
	{
		WebDriver driver = null;

		// creating object for all the libraries
		JavaUtility jUtils = new JavaUtility();
		WebDriverUtility wUtils = new WebDriverUtility();
		FileUtility fUtils = new FileUtility();
		ExcelUtility eUtils = new ExcelUtility();
		// Generating Random number
		int randomNumber = jUtils.GetRandomValue();

		// Generating System Data time
		String calDate = jUtils.GetSystemDateInYYYY_MM_DD_Format();

		// Getting the Data from Property File
		String browser = fUtils.GetDataFromPropertyFile("browser");
		String url = fUtils.GetDataFromPropertyFile("url");
		String username = fUtils.GetDataFromPropertyFile("username");
		String password = fUtils.GetDataFromPropertyFile("password");

		// Getting the data for Excel Sheet
		String organizationName = eUtils.getStringDataFromExcelSheet("Sheet2", 2, 2) + randomNumber;
		String firstName = eUtils.getStringDataFromExcelSheet("Sheet2", 2, 4) + randomNumber;
		String lastName = eUtils.getStringDataFromExcelSheet("Sheet2", 2, 5) + randomNumber;
		String salutationType = eUtils.getStringDataFromExcelSheet("Sheet2", 2, 8);

		// Launching the Browser
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else
			System.out.println("broswer not found");

		driver.manage().window().maximize();
		wUtils.waitForElement(driver);

		// Navigating to the application
		driver.get(url);

		// Login Action
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// Creating Organization to remove data dependency
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verification
		String actualOrganizationName = driver.findElement(By.className("dvHeaderText")).getText();
		if (actualOrganizationName.contains(organizationName)) {
			System.out.println("Pass: Organization has been created");
		} else
			System.out.println("Fail: Organization has not been created");
		WebElement locator = driver.findElement(By.linkText("Contacts"));
		wUtils.waitForElement(locator);
		
		// Creating Contacts
		locator.click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		Select select = new Select(driver.findElement(By.name("salutationtype")));
		select.selectByVisibleText(salutationType);

		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath(
				"//td[text()='Organization Name 			']/parent::tr/descendant::img[@src='themes/softed/images/select.gif']"))
				.click();
		// Switching To Window
		String parenWindowTitle = driver.getTitle();
		wUtils.switchToWindow(driver, parenWindowTitle);
		driver.findElement(By.id("search_txt")).sendKeys(organizationName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("" + organizationName + "")).click();
		wUtils.switchToWindow(driver, parenWindowTitle);

		// Clicking on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualContactName = driver.findElement(By.className("dvHeaderText")).getText();

		// Verification
		if (actualContactName.contains(lastName)) {
			System.out.println("Pass: Contact has been created");

		} else
			System.out.println("Fail: Contact has not been created");

		// Logout Action
		wUtils.MoveToElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		driver.navigate().to(url);
		
		
		// Closing the browser and server
		driver.quit();
	}

}
