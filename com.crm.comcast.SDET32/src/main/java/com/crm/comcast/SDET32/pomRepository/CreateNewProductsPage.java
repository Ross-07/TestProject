package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductsPage
{
	WebDriver driver;
	public CreateNewProductsPage(WebDriver driver ) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "productname")
	private WebElement productNameTextField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	
	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getProductNameTextField() 
	{
		return productNameTextField;
	}

	public WebElement getSaveButton() 
	{
		return saveButton;
	}
	
	public void passValueToTheProductNameTextField(String value)
	{
		productNameTextField.sendKeys(value);
	}
	public void clickOnSaveButton()
	{
		saveButton.click();
	}
	
	
	
}
