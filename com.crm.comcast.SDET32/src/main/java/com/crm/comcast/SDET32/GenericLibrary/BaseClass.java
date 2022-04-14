package com.crm.comcast.SDET32.GenericLibrary;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LogInPage;

public class BaseClass
{
	public WebDriver driver = null;
	static WebDriver sDriver;
	
	//Creating the object of all the Libraries
	public JavaUtility jUtils = new JavaUtility();
	public FileUtility fUtils = new FileUtility();
	public ExcelUtility eUtils = new ExcelUtility();
	public WebDriverUtility wUtils = new WebDriverUtility();
	@BeforeSuite  
	public void bsConfig()
	{
		System.out.println("====Data Base connection has been Established=====");
	}
	//@Parameters("browser")
	@BeforeClass
	public void bcConfig(String browser) throws IOException
	{
		//String browser = fUtils.GetDataFromPropertyFile(IPathConstant.BROWSER_KEY);
		String url = fUtils.GetDataFromPropertyFile(IPathConstant.URL_KEY);
		
		// Runtime PolyMorphism- Using Data From the Property file
					if (browser.equals("chrome")) {
						driver = new ChromeDriver();
					} else if (browser.equals("firefox")) {
						driver = new FirefoxDriver();
					} else
						System.out.println("Browser is not present");
					sDriver = driver;
					
					driver.manage().window().maximize();
					wUtils.waitForElement(driver);
					
					// Navigating to the VTiger
					driver.get(url);
					
			System.out.println("===Browser has been launched===");
	}
	
	@BeforeMethod
	public void bmConfig() throws IOException
	{
		String username = fUtils.GetDataFromPropertyFile(IPathConstant.USERNAME_KEY);
		String password = fUtils.GetDataFromPropertyFile(IPathConstant.PASSWORD_KEY);
		LogInPage login = new LogInPage(driver);
		login.LoginAction(username, password);
		System.out.println("=====successfully logged in=====");
	}
	
	@AfterMethod
	public void amConfig()
	{
		HomePage home = new HomePage(driver);
		home.logoutAction();
		System.out.println("========successfuly logged out from the application========");
	}
	
	@AfterClass
	public void acConfig()
	{
		driver.quit();
		System.out.println("======browser has been closed======");
	}
	
	
	@AfterSuite
	public void asConfig()
	{
		System.out.println("======database connection has been closed=====");
	}
}
