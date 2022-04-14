package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage 
{
	WebDriver driver;
	public LogInPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "user_name")
	private WebElement userNameTextField;
	
	@FindBy(name = "user_password")
	private WebElement passwordTextField;
	
	@FindBy(id="submitButton")
	WebElement LoginButton;
	
	
	public WebDriver getDriver() 
	{
		return driver;
	}

	public WebElement getUserNameTextField() 
	{
		return userNameTextField;
	}

	public WebElement getPasswordTextField() 
	{
		return passwordTextField;
	}

	public WebElement getLoginButton() 
	{
		return LoginButton;
	}
	
	public void LoginAction(String username,String password)
	{
		userNameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		LoginButton.click();
	}
}
