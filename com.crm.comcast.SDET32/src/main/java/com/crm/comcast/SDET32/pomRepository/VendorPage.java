package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage 
{
	WebDriver driver;
	public VendorPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement clickOnCreateVendorPlusBtn;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getClickOnCreateVendorPlusBtn() 
	{
		return clickOnCreateVendorPlusBtn;
	}
	
	public void clickOnVendorPlusBtn()
	{
		clickOnCreateVendorPlusBtn.click();
	}
	
	
}
