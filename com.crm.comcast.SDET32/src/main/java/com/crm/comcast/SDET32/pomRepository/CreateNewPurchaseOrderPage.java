package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewPurchaseOrderPage 
{
	WebDriver driver;
	
	public CreateNewPurchaseOrderPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "subject")
	private WebElement subjectNameTextField;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement PlusBtnToSelectVendorName;
	
	@FindBy(name = "search_text")
	private WebElement searchTextFieldInVendorNameChildBrowser;
	
	@FindBy(name = "search")
	private WebElement searchBtnInVendoeNameChildBrowser;
	
	@FindBy(id = "1")
	private WebElement selectVendorName;
	
	@FindBy(name = "postatus")
	private WebElement selectDropDownMenu;
	
	@FindBy(name = "bill_street")
	private WebElement billingAddress;
	
	@FindBy(name = "ship_street")
	private WebElement shippingAddress;
	
	@FindBy(xpath = "//img[@id='searchIcon1']")
	private WebElement lookForItemNameBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchTextFieldForItemNameInChildBrowser;
	
	@FindBy(name = "search")
	private WebElement searchBtnForItemNameInChildBrowser;
	
	@FindBy(xpath ="//a[contains(text(),'ItemName')]")
	private WebElement selectItemName;
	
	@FindBy(id = "qty1")
	private WebElement addQuantity;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getSubjectNameTextField()
	{
		return subjectNameTextField;
	}

	public WebElement getPlusBtnToSelectVendorName() 
	{
		return PlusBtnToSelectVendorName;
	}

	public WebElement getsearchTextFieldInVendorNameChildBrowser()
	{
		return searchTextFieldInVendorNameChildBrowser;
	}

	public WebElement getsearchBtnInVendoeNameChildBrowser() 
	{
		return searchBtnInVendoeNameChildBrowser;
	}

	public WebElement getSelectVendorName() 
	{
		return selectVendorName;
	}

	public WebElement getSelectDropDownMenu() 
	{
		return selectDropDownMenu;
	}

	public WebElement getBillingAddress() 
	{
		return billingAddress;
	}

	public WebElement getShippingAddress() 
	{
		return shippingAddress;
	}

	public WebElement getLookForItemNameBtn() 
	{
		return lookForItemNameBtn;
	}

	public WebElement getsearchTextFieldForItemNameInChildBrowser() 
	{
		return searchTextFieldForItemNameInChildBrowser;
	}

	public WebElement getsearchBtnForItemNameInChildBrowser() 
	{
		return searchBtnForItemNameInChildBrowser;
	}

	public WebElement getSelectItemName() 
	{
		return selectItemName;
	}

	public WebElement getAddQuantity() 
	{
		return addQuantity;
	}
	
	public void passValueToSubjectTextField(String value)
	{
		subjectNameTextField.sendKeys(value);
	}
	
	public void clickOnPlusBtnOfVendorName()
	{
		PlusBtnToSelectVendorName.click();
	}
	
	
	public void handleVendorNameChildBrowser(String value) throws InterruptedException
    {
    	searchTextFieldInVendorNameChildBrowser.sendKeys(value);		
		searchBtnInVendoeNameChildBrowser.click();
		Thread.sleep(10000);
		selectVendorName.click();
    }
	
	
	
    public void handleStatusDropdown(String text) 
    {
		Select select = new Select(selectDropDownMenu);
		select.selectByVisibleText(text);
	}
    
    
    
    public void passValueToBillingAddress(String value)
    {
    	billingAddress.sendKeys(value);
    }
    
    
    public void passValueToShippingAddress(String value)
    {
    	shippingAddress.sendKeys(value);
    }
    
    public void checkItem()
    {
    	lookForItemNameBtn.click();
    }
    
    public void handleItemNameChildBrowser(String value) throws InterruptedException
    {
    	searchTextFieldForItemNameInChildBrowser.sendKeys(value);		
    	searchBtnForItemNameInChildBrowser.click();
		Thread.sleep(10000);
		selectItemName.click();
    }
    //check once
    public void passValueToAddQuantityTextfield()
    {
    	addQuantity.sendKeys();
    }
    
    public void clickOnSaveButton()
    {
    	saveButton.click();
    }
	  
	
	
}
