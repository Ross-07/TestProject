package com.crm.comcast.SDET32.Practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.comcast.SDET32.GenericLibrary.ExcelUtility;
import com.crm.comcast.SDET32.GenericLibrary.FileUtility;
import com.crm.comcast.SDET32.GenericLibrary.IPathConstant;
import com.crm.comcast.SDET32.GenericLibrary.JavaUtility;
import com.crm.comcast.SDET32.GenericLibrary.WebDriverUtility;

public class CreateOrganizationTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		WebDriver driver = null;
		JavaUtility jUtils = new JavaUtility();
		FileUtility fUtils = new FileUtility();
		ExcelUtility eUtils = new ExcelUtility();
		WebDriverUtility wUtils = new WebDriverUtility();

		// Generating Random Number
		int randomValue = jUtils.GetRandomValue();

		// Getting Data from ExcelSheet and Initializing the organization name
		String organizationName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 1, 2);

		// Getting the data from property file
		String browser = fUtils.GetDataFromPropertyFile(IPathConstant.BROWSER_KEY);
		String url = fUtils.GetDataFromPropertyFile("url");
		String username = fUtils.GetDataFromPropertyFile(IPathConstant.USERNAME_KEY);
		String password = fUtils.GetDataFromPropertyFile(IPathConstant.PASSWORD_KEY);

		// Runtime PolyMorphism using data from the property file
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser is not Present");
		}

		driver.manage().window().maximize();
		wUtils.waitForElement(driver);

		// Navigating to the Vtiger
		driver.get(url);

		// Logging in to the application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		// creating organization to remove data dependency
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verifications

		String actualOrganizationName = driver.findElement(By.className("dvHeaderText")).getText();
		if (actualOrganizationName.contains(organizationName)) {
			System.out.println("Pass:Organization has been created");
		} else {
			System.out.println("fail: organization has not been created");
		}
		
		
		//LogOut Action
		wUtils.MoveToElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG'")));
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
