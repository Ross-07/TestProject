package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage 
{
	WebDriver driver;
	public CreateNewVendorPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "vendorname")
	private WebElement vendorNameTextField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getVendorNameTextField()
	{
		return vendorNameTextField;
	}

	public WebElement getSaveBtn() 
	{
		return saveBtn;
	}
	
	public void passValueToVendorNameField(String vendorName)
	{
		vendorNameTextField.sendKeys(vendorName);
	}
	
	public void clickOnSaveBtn()
	{
		saveBtn.click();
	}
}
