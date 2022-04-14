package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewContactsPage 
{

	WebDriver driver;

	public CreateNewContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "salutationtype")
	private WebElement salutationDropDown;

	@FindBy(name = "firstname")
	private WebElement firstNameTextField;

	@FindBy(name = "lastname")
	private WebElement lastNameTextField;

	@FindBy(xpath = "//td[text()='Organization Name 			']/parent::tr/descendant::img")
	private WebElement organizationLookUpImage;

	@FindBy(id = "search_txt")
	private WebElement organizationLookUpSearchTextField;

	@FindBy(name = "search")
	private WebElement searchButtonInLookUp;

	@FindBy(id = "1")
	private WebElement organizationNameAfterSearch;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getSalutationDropDown() {
		return salutationDropDown;
	}

	public WebElement getFirstNameTextField() {
		return firstNameTextField;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getOrganizationLookUpImage() {
		return organizationLookUpImage;
	}

	public WebElement getOrganizationLookUpSearchTextField() {
		return organizationLookUpSearchTextField;
	}

	public WebElement getSearchButtonInLookUp() {
		return searchButtonInLookUp;
	}

	public WebElement getOrganizationNameAfterSearch() {
		return organizationNameAfterSearch;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public void handleSalutationDropDown(String text) {

		Select select = new Select(salutationDropDown);
		select.selectByVisibleText(text);
	}

	public void passValueToFirstName(String value) {

		firstNameTextField.sendKeys(value);
	}

	public void passValueToLastName(String value) {

		lastNameTextField.sendKeys(value);
	}
	
	public void clickOnOrgLookUpImage() {
		organizationLookUpImage.click();	
	}
	
	public void selectOrgFromChildWindow(String value) throws InterruptedException {
		organizationLookUpSearchTextField.sendKeys(value);
		searchButtonInLookUp.click();
		Thread.sleep(10000);
		organizationNameAfterSearch.click();	
	}
	
	public void clickOnSaveButton() {
		saveButton.click();
	}
}
