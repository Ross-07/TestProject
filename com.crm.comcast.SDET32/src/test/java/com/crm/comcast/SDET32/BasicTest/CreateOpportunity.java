package com.crm.comcast.SDET32.BasicTest;

import java.io.IOException;

import org.testng.annotations.Test;

import com.crm.comcast.SDET32.GenericLibrary.BaseClass;
import com.crm.comcast.SDET32.GenericLibrary.IPathConstant;
import com.crm.comcast.SDET32.pomRepository.ContactPage;
import com.crm.comcast.SDET32.pomRepository.ContactsInformationPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewContactsPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewOpportunityPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewOrganizationPage;
import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LogInPage;
import com.crm.comcast.SDET32.pomRepository.OpportunitiesInformationPage;
import com.crm.comcast.SDET32.pomRepository.OpportunitiesPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationInformationPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationsPage;

public class CreateOpportunity extends BaseClass
{

		    @Test
		    public void createOpportunity() throws IOException, InterruptedException
		    {
			
			   int randomNumber = jUtils.GetRandomValue();

			// Generating System Data time
			String calDate = jUtils.GetSystemDateInYYYY_MM_DD_Format();
			
			// Getting the data for Excel Sheet
			String organizationName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3,3,0) + randomNumber;
			String firstName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3,3,2) + randomNumber;
			String lastName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3, 3, 3) + randomNumber;
			String opportunityName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3, 3, 1) + randomNumber;
			String salesStage = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3, 3, 5);
			String salutationType = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME3, 3, 6);

			// Creating Object For Pom Classes
			LogInPage login = new LogInPage(driver);
			HomePage home = new HomePage(driver);
			OrganizationsPage orgPage = new OrganizationsPage(driver);
			OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
			CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
			ContactPage contact=new ContactPage(driver);
			CreateNewContactsPage newContact = new CreateNewContactsPage(driver);
			ContactsInformationPage contactsInfo = new ContactsInformationPage(driver);
			OpportunitiesPage oppPage = new OpportunitiesPage(driver);
			CreateNewOpportunityPage createOpp = new CreateNewOpportunityPage(driver);
			OpportunitiesInformationPage oppInfo = new OpportunitiesInformationPage(driver);

			// Clicking on Organization Link
			home.clickOnOrganizationLink();

			// Clicking on Organization Plus Image
			orgPage.clickOnOrganizationPlusImage();

			// Creating new Organization
			createNewOrg.passValueToOrganizationTextField(organizationName);
			createNewOrg.clickOnSaveButton();

			// Verification
			String actualTitle = orgInfo.verifyOrganizationNameText();

			if (actualTitle.contains(organizationName)) {
				System.out.println("Pass: Organization has been created");
			} else
				System.out.println("Fail: Organization has not been created");
			
			// Creating Contacts		
			home.clickOnContactsLink();
			
			contact.clickOnContactPlusImage();
			
			newContact.handleSalutationDropDown(salutationType);
			newContact.passValueToFirstName(firstName);
			newContact.passValueToLastName(lastName);
			newContact.clickOnOrgLookUpImage();
			String parenWindowTitle = driver.getTitle();
			wUtils.switchToWindow(driver, parenWindowTitle);
			newContact.selectOrgFromChildWindow(organizationName);
			wUtils.switchToWindow(driver, parenWindowTitle);
			newContact.clickOnSaveButton();
			
			
	}

}
