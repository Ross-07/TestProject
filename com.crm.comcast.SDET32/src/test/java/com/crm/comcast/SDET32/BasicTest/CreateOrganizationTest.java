package com.crm.comcast.SDET32.BasicTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.GenericLibrary.BaseClass;
import com.crm.comcast.SDET32.GenericLibrary.IPathConstant;
import com.crm.comcast.SDET32.pomRepository.CreateNewOrganizationPage;
import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LogInPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationInformationPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationsPage;
/**
 * This class is using the data from property file and and automating the VTiger scenario
 * @author supriya
 *
 */
public class CreateOrganizationTest  extends BaseClass
{
	/**
	 * This test script is to create organization
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	@Test
	public void createOrganizationTest() throws EncryptedDocumentException, IOException
	{
			
			// Generating RandomNumber
			int randomValue = jUtils.GetRandomValue();

			// Getting data from ExcelSheet and Initializing The Organization name
			String organizationName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 1, 2) + randomValue;
			
			
			//Creating Object For Pom Classes
			LogInPage login = new LogInPage(driver);
			HomePage home = new HomePage(driver);
			OrganizationsPage orgPage = new OrganizationsPage(driver);
			OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
			CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);

			//Clicking on Organization Link
			home.clickOnOrganizationLink();
			
			//Clicking on Organization Plus Image
			orgPage.clickOnOrganizationPlusImage();

			//Creating new Organization
			createNewOrg.passValueToOrganizationTextField(organizationName);
			createNewOrg.clickOnSaveButton();
			
			// Verification
			String actualTitle = orgInfo.verifyOrganizationNameText();
			
			if (actualTitle.contains(organizationName)) {
				System.out.println("Pass: Organization has been created");
			} else
				System.out.println("Fail: Organization has not been created");
			
		}
	
}

