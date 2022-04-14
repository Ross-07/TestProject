package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage
{
    WebDriver driver;
	
	public OrganizationsPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement organizationPlusImage;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrganizationPlusImage() {
		return organizationPlusImage;
	}
	
	public void clickOnOrganizationPlusImage() {
		organizationPlusImage.click();
	}
}
