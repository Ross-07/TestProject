package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage 
{
	WebDriver driver;
	public ProductInformationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className ="lvtHeaderText")
	private WebElement actualProductName;
	
	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getActualProductName() 
	{
		return actualProductName;
	}
	
	public String verifyActualProductName(String value)
	{
		return actualProductName.getText();
	}
	
	
}
