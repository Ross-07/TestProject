package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	WebDriver driver;
	public HomePage(WebDriver driver) 
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLink;
	
	@FindBy(linkText = "Products")
	private WebElement productsLink;
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(name = "Vendors")
	private WebElement vendorLink;
	

	@FindBy(name = "Purchase Order")
	private WebElement purchaseOrderlink;


	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutImage;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	public WebDriver getDriver() 
	{
		return driver;
	}
	
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactsLink()
	{
		return contactsLink;
	}

	public WebElement getOpportunitiesLink() 
	{
		return opportunitiesLink;
	}

	public WebElement getProductsLink() 
	{
		return productsLink;
	}

	public WebElement getLeadsLink() 
	{
		return leadsLink;
	}

	public WebElement getMoreLink() 
	{
		return moreLink;
	}
	
	public WebElement getVendorLink() 
	{
		return vendorLink;
	}
	
	public WebElement getPurchaseOrderlink()
	{
		return purchaseOrderlink;
	}
	
	public WebElement getLogoutImage() 
	{
		return logoutImage;
	}

	public WebElement getSignOutLink() 
	{
		return signOutLink;
	}

	public void clickOnOrganizationLink()
	{
		organizationLink.click();
	}
	
	public void clickOnContactsLink()
	{
		contactsLink.click();
	}
	
	public void clickOnOpportunitiesLink()
	{
		opportunitiesLink.click();
	}
	
	public void clickOnProductsLink()
	{
		productsLink.click();
	}
	
	public void clickOnVendorLink()
	{
		vendorLink.click();
	}
	
	public void clickOnPurchaseOrderLink()
	{
		purchaseOrderlink.click();
	}
	
	public void logoutAction() 
	{
		
		Actions action = new Actions(driver);
		action.moveToElement(logoutImage).perform();
		signOutLink.click();
	}
	
	public void MoveToMore()
	{
		Actions action = new Actions(driver);
		action.moveToElement(moreLink).perform();
	}

		
	
		
}
