package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage 
{
	WebDriver driver;
	public OpportunitiesPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement opportunityPlusImage;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOpportunityPlusImage() {
		return opportunityPlusImage;
	}
	
	public void clickOnOpportunitiesPlusImage() {
		opportunityPlusImage.click();
	}
}
