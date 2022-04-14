package PurchaseOrderTestCase;

import java.io.FileInputStream;
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

public class CreatePurchaseOrderAndSelectTheStatusAs_Delivered {

	public static void main(String[] args) throws IOException 
	{
		WebDriver driver = null;
		//Generating random Number.
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		
		
		FileInputStream fisForProperty = new FileInputStream("./src/test/resources/PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fisForProperty);
		String browser = property.getProperty("Browser");
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		
		
		//getting the data for excel sheet
				FileInputStream fisForExcel = new FileInputStream("./src/test/resources/PurchaseOrder.xlsx");
				Workbook workbook = WorkbookFactory.create(fisForExcel);
				Sheet sheet = workbook.getSheet("Sheet1");
				String SubjectName = sheet.getRow(1).getCell(0).getStringCellValue()+randomNumber;
				String VendorName = sheet.getRow(1).getCell(1).getStringCellValue()+randomNumber;
				String BillingAddress = sheet.getRow(1).getCell(2).getStringCellValue()+randomNumber;
				String ShippingAddress = sheet.getRow(1).getCell(3).getStringCellValue()+randomNumber;
				//String ItemName = sheet.getRow(1).getCell(4).getStringCellValue()+randomNumber;
				String ProductName = sheet.getRow(0).getCell(4).getStringCellValue()+randomNumber;
				
				//Launching The Browser
				if (browser.equals("chrome"))
				{
					driver=new ChromeDriver();
				}
				else if(browser.equals("Firefox"))
				{
					driver  = new FirefoxDriver();
				}
				else
				{
					System.out.println("browser does not found");
				}
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				// navigating the application
				driver.get(url);
				// login action
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				
				//creating product name and this is as item name in purchase order
				driver.findElement(By.linkText("Products")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				driver.findElement(By.name("productname")).sendKeys(ProductName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//creating vendor name 
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.linkText("More"))).perform();
				driver.findElement(By.xpath("//a[text()='Vendors']")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				driver.findElement(By.name("vendorname")).sendKeys(VendorName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//purchase order starts from here
				Actions action1 = new Actions(driver);
				action1.moveToElement(driver.findElement(By.linkText("More"))).perform();
				
				driver.findElement(By.name("Purchase Order")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				//giving subject name
				driver.findElement(By.name("subject")).sendKeys(SubjectName);
				
				//vendor name
				driver.findElement(By.xpath("//td[text()='Vendor Name 			']/parent::tr/descendant::img")).click();
				
				//handle window
				String VendorparentWindow = driver.getWindowHandle();
				Set<String> VendorchildWindows = driver.getWindowHandles();
				for (String window : VendorchildWindows) 
				{
					driver.switchTo().window(window);
					if (!VendorparentWindow.equals(window)) 
					{
						driver.findElement(By.id("search_txt")).sendKeys(VendorName);
						driver.findElement(By.name("search")).click();
						driver.findElement(By.linkText(""+VendorName+"")).click();
						break;
					}
				}
				driver.switchTo().window(VendorparentWindow);
				//Select Status as Approved
				Select selectStatus = new Select(driver.findElement(By.name("postatus")));
				selectStatus.selectByVisibleText("Delivered");
				
				
				driver.findElement(By.name("bill_street")).sendKeys(BillingAddress);
				driver.findElement(By.name("ship_street")).sendKeys(ShippingAddress);
				
				
				//Handle ItemName
				driver.findElement(By.id("searchIcon1")).click();
				//handle Product window
				String ProductparentWindow = driver.getWindowHandle();
				Set<String> ProductchildWindows = driver.getWindowHandles();
				for (String window : ProductchildWindows) 
				{
					driver.switchTo().window(window);
					if (!ProductparentWindow.equals(window)) 
					{
						driver.findElement(By.id("search_txt")).sendKeys(ProductName);
						driver.findElement(By.name("search")).click();
						driver.findElement(By.linkText(""+ProductName+"")).click();
						break;
					}
				}
				driver.switchTo().window(ProductparentWindow);
				//putting the quantity
				driver.findElement(By.id("qty1")).sendKeys("6");
				//putting the value of list price
				driver.findElement(By.id("listPrice1")).sendKeys("501");
				//saving purchase order
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Logout Action
				Actions action2 = new Actions(driver);
				action2.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				//closing the workbook
				workbook.close();
				//closing the browser and server 
				driver.quit();
				
	}

}
