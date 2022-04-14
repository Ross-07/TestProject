package com.crm.comcast.SDET32.GenericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class WebDriverUtility {
	private long pollingPeriod;

	/**
	 * 
	 * @param driver
	 */
	public void waitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	} 

	/**
	 * @throws InterruptedException 
	 * 
	 */
	public void waitForElementToBeClickableForSpecificTime(WebDriver driver,WebElement element) throws InterruptedException
	
	{
		FluentWait wait = new FluentWait(driver);
		wait.pollingEvery(pollingPeriod,TimeUnit.SECONDS);
		wait.wait();
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * 
	 * @param element
	 * @throws InterruptedException 
	 */
	public void waitForElement(WebElement element) throws InterruptedException {
		int count = 0;
		while (count < 20) {
			try {
				element.click();
				break;
			} catch (Exception e) {
				Thread.sleep(10000);
				count++;
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param ParentWindowTitle
	 */
	public void switchToWindow(WebDriver driver, String ParentWindowTitle) {

		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String WinID = it.next();
			driver.switchTo().window(WinID);
			String CurrentWindowTitle = driver.getTitle();
			if (!CurrentWindowTitle.contains(ParentWindowTitle)) {
				break;
			}
		}

	}

	/**
	 * 
	 * @param driver
	 */
	public void acceptAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * 
	 * @param driver
	 */
	public void dismissAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * 
	 * @param element
	 * @param VisibleText
	 */
	public void HandleDropDown(WebElement element, String VisibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(VisibleText);
	}

	/**
	 * 
	 * @param element
	 * @param Index
	 */
	public void HandleDropDown(WebElement element, int Index) {
		Select select = new Select(element);
		select.selectByIndex(Index);
	}

	/**
	 * 
	 * @param Value
	 * @param element
	 */
	public void HandleDropDown(String Value, WebElement element) {
		Select select = new Select(element);
		select.selectByValue(Value);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void MoveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * 
	 * @param driver
	 */
	public void RightClickAction(WebDriver driver) {
		Actions action = new Actions(driver);
		action.contextClick().perform();
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void RightClickAction(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick().perform();
	}

	/**
	 * 
	 * @param driver
	 * @param javaScriptCode
	 */
	public void HandleAssyncScript(WebDriver driver, String javaScriptCode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript(javaScriptCode, null);
	}

	/**
	 * 
	 * @param driver
	 * @param FileName
	 * @throws IOException 
	 */
	public void takeScreenShot(WebDriver driver, String FileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ErrorShot/" + FileName + ".png");
		Files.copy(src, dest);
	}

	/**
	 * 
	 * @param driver
	 * @param Index
	 */
	public void SwitchToFrames(WebDriver driver, int Index) {
		driver.switchTo().frame(Index);
	}

	/**
	 * 
	 * @param driver
	 * @param Value
	 */
	public void SwitchToFrames(WebDriver driver, String Value) {
		driver.switchTo().frame(Value);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void SwitchToFrames(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

}
