package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseInformationPage 
{
	WebDriver driver;
	
	public PurchaseInformationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "lvtHeaderText")
	private WebElement actualPurchaseOrderInfo;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getActualPurchaseOrderPurchaseOrderInfo() {

		return actualPurchaseOrderInfo;
	}
	
	public String verifyPurchaseOrder()
	{
		return actualPurchaseOrderInfo.getText();
	}
}
