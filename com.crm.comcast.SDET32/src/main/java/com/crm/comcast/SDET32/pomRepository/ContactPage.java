package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage 
{
	WebDriver driver;
	public ContactPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement contactPlusImage;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getContactPlusImage() {
		return contactPlusImage;
	}
	
	public void clickOnContactPlusImage()
	{
		contactPlusImage.click();
	}
	
	
}
