package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOpportunityPage 
{
	WebDriver driver;
	public CreateNewOpportunityPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "potentialname")
	private WebElement opportunityNameTextField;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement orgPlusLookUpImage;
	
	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupRadioButton;
	
	@FindBy(name = "assigned_group_id")
	private WebElement groupDropdown;
	
	@FindBy(name = "closingdate")
	private WebElement closingDateCalender;
	
	@FindBy(name = "sales_stage")
	private WebElement salesStageDropdown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButtonInOpportunities;
	
	@FindBy(id = "search_txt")
	private WebElement searchTextFieldInOpportunity;
	
	@FindBy(name = "search")
	private WebElement searchButton;
	
	@FindBy(id = "1")
	private WebElement orgNameAfterSearch;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOpportunityNameTextField() {
		return opportunityNameTextField;
	}

	public WebElement getOrgPlusLookUpImage() {
		return orgPlusLookUpImage;
	}

	public WebElement getGroupRadioButton() {
		return groupRadioButton;
	}

	public WebElement getGroupDropdown() {
		return groupDropdown;
	}

	public WebElement getClosingDateCalender() {
		return closingDateCalender;
	}

	public WebElement getSalesStageDropdown() {
		return salesStageDropdown;
	}

	public WebElement getSaveButtonInOpportunities() {
		return saveButtonInOpportunities;
	}

	public WebElement getSearchTextFieldInOpportunity() {
		return searchTextFieldInOpportunity;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getOrgNameAfterSearch() {
		return orgNameAfterSearch;
	}
	
	public void sendValuesToOpportunityName(String value) {
		opportunityNameTextField.sendKeys(value);
	}
	
	public void clickOnThePlusButton() {
		orgPlusLookUpImage.click();
	}
	
	public void clickOnTheGroupRadioButton() {
		groupRadioButton.click();
				
	}
	
	public void passValueToTheClosingDate(String date) {
	closingDateCalender.clear();
	closingDateCalender.sendKeys(date);
	}
	
	public void handleSalesStageDropdown(String text) {
		
		Select select = new Select(salesStageDropdown);
		select.selectByVisibleText(text);
	}
	
	
	public void clickOnSaveButton() {
		saveButtonInOpportunities.click();
	}
	

	public void handleOppChildBrowser(String value) throws InterruptedException {
		searchTextFieldInOpportunity.sendKeys(value);		
		searchButton.click();
		Thread.sleep(10000);
		orgNameAfterSearch.click();

	}
}
