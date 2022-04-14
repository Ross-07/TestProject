package PurchaseOrderTestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.comcast.SDET32.GenericLibrary.ExcelUtility;
import com.crm.comcast.SDET32.GenericLibrary.FileUtility;
import com.crm.comcast.SDET32.GenericLibrary.IPathConstant;
import com.crm.comcast.SDET32.GenericLibrary.JavaUtility;
import com.crm.comcast.SDET32.GenericLibrary.WebDriverUtility;
import com.crm.comcast.SDET32.pomRepository.CreateNewProductsPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewPurchaseOrderPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewVendorPage;
import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LogInPage;
import com.crm.comcast.SDET32.pomRepository.ProductInformationPage;
import com.crm.comcast.SDET32.pomRepository.ProductsPage;
import com.crm.comcast.SDET32.pomRepository.PurchaseInformationPage;
import com.crm.comcast.SDET32.pomRepository.PurchaseOrderPage;
import com.crm.comcast.SDET32.pomRepository.VendorPage;

/**
 * 
 * @author supriya
 *
 */
public class CreatePurchaseOrderAndSelectTheStatusAs_ApprovedTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = null;
		
		// Creating An object of generic classes
		JavaUtility jUtils = new JavaUtility();
		FileUtility fUtils = new FileUtility();
		ExcelUtility eUtils = new ExcelUtility();
		WebDriverUtility wUtils = new WebDriverUtility();
		
		// Generating random Number.
	    int randomNumber = jUtils.GetRandomValue();
	    
	    //Getting the data from external file
		String browser = fUtils.GetDataFromPropertyFile(IPathConstant.BROWSER_KEY);
		String url = fUtils.GetDataFromPropertyFile(IPathConstant.URL_KEY);
		String username=fUtils.GetDataFromPropertyFile(IPathConstant.USERNAME_KEY);
		String password=fUtils.GetDataFromPropertyFile(IPathConstant.PASSWORD_KEY);
		
	
		// getting the data for excel sheet
		String subject = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1,1,0)+randomNumber;
		String vendorName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1,1,1)+randomNumber;
		String BillingAddress = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1,1,2)+randomNumber;
		String shippingAddress = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1,1,3)+randomNumber;
		String productName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 1, 0)+randomNumber;
		//String ItemName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1,1,4)+randomNumber;
		//String created = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 1, 4);
		String approved = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 2, 4);
		//String delivered = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 3, 4);
		//String cancelled = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 4, 4);
		//String RecievedShipment = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME1, 5, 4);
		

		// Launching The Browser
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("browser does not found");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// navigating the application
		driver.get(url);
		
		
		// Creating an object of POM classes
		LogInPage login = new LogInPage(driver);
		HomePage home = new HomePage(driver);
		PurchaseOrderPage purchase = new PurchaseOrderPage(driver);
		CreateNewPurchaseOrderPage purchasePage = new CreateNewPurchaseOrderPage(driver);
		PurchaseInformationPage purchaseInfo = new PurchaseInformationPage(driver);
		ProductsPage product = new ProductsPage(driver);
		CreateNewProductsPage productPage = new CreateNewProductsPage(driver);
		ProductInformationPage productinfo = new ProductInformationPage(driver);
		VendorPage vPage = new VendorPage(driver);
		CreateNewVendorPage createVendor = new CreateNewVendorPage(driver);
		
		// login action
		login.LoginAction(username, password);
		
        //clicking on product link
		home.clickOnProductsLink();
		
		// clicking on create Product button (plus sign)
		product.clickOnCreateProductsBtn();
		
		//filling the data in the new create product page
		productPage.passValueToTheProductNameTextField(productName);
		//saving the product page
		productPage.clickOnSaveButton();
		
		//moving to more link
		home.MoveToMore();
		
		//Navigate to the Vendor Link
		home.clickOnVendorLink();
		
		//click  on create new Vendor Plus button
		vPage.clickOnVendorPlusBtn();
		
		//Navigate To New Vendor Page
		createVendor.passValueToVendorNameField(vendorName);
		createVendor.clickOnSaveBtn();
		
		//move to more again
		home.MoveToMore();
		

		// Navigating To The Purchase Order Module
		home.clickOnPurchaseOrderLink();
		
		//clicking on create purchase order (plus sign)
		purchase.clickOnCreatePurchaseOrderBtn();
		
		//Navigating to create new purchase order page
		purchasePage.passValueToSubjectTextField(subject);
		//Handle vendor name and child browser
		purchasePage.clickOnPlusBtnOfVendorName();
		String parentWindowTitle = driver.getTitle();
		wUtils.switchToWindow(driver, parentWindowTitle);
		purchasePage.handleVendorNameChildBrowser(vendorName);
		wUtils.switchToWindow(driver, parentWindowTitle);
		
		//handling status dropDown
		//purchasePage.handleStatusDropdown(created);
		purchasePage.handleStatusDropdown(approved);
		//purchasePage.handleStatusDropdown(delivered);
		//purchasePage.handleStatusDropdown(cancelled);
		//purchasePage.handleStatusDropdown(RecievedShipment);
		
		//billing address
		purchasePage.passValueToBillingAddress(BillingAddress);
		//shipping address
		purchasePage.passValueToShippingAddress(shippingAddress);
		
		//handle item name
		purchasePage.handleItemNameChildBrowser(productName);
		String parentWindowTitle1 = driver.getTitle();
		wUtils.switchToWindow(driver, parentWindowTitle);
		purchasePage.handleVendorNameChildBrowser(parentWindowTitle1);
		wUtils.switchToWindow(driver, parentWindowTitle);
		
		//click on save button
		purchasePage.clickOnSaveButton();
		
		//sign out action
		home.logoutAction();
		

		
	}

}
