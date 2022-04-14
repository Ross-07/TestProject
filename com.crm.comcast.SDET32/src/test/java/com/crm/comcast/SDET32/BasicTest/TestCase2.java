package com.crm.comcast.SDET32.BasicTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCase2 {

	public static void main(String[] args) throws IOException 
	{

		WebDriver driver = null;
		FileInputStream fis = new FileInputStream("./PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fis);
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
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys("Kumar");
		driver.findElement(By.xpath("//input[@name='assigntype'][1]")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		String actualTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String expectedTitle = "Kumar";
		
		System.out.println(actualTitle);
		System.out.println(expectedTitle);
		
		if(actualTitle.contains(expectedTitle))
		{
			System.out.println("Pass:Contact has been created");
		}
		else
		{
			System.out.println("Fail:Contact has not been created");
		}
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		
	}

}
