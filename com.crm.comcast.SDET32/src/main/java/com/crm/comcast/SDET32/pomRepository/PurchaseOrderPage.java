package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPage 
{
	WebDriver driver;
	public PurchaseOrderPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createPurchaseOrderBtn;
	
	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getCreatePurchaseOrderBtn() 
	{
		return createPurchaseOrderBtn;
	}
	
	public void clickOnCreatePurchaseOrderBtn()
	{
		createPurchaseOrderBtn.click();
	}
}
