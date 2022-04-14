package com.crm.comcast.SDET32.GenericLibrary;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;

public class ListenerImplementationClass implements ITestListener
{

	
	public void onTestFailure(ITestResult result) 
	{
		JavaUtility jUtils = new JavaUtility();
		String time = jUtils.GetSystemDateAndTime().replace(" ", "_").replace(":", "_");
		String testCaseName = result.getMethod().getMethodName()+time;
		EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sDriver);
		File src = eDriver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ErrorShots/"+testCaseName+".png");
		try 
		{
			Files.copy(src, dest);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
}
