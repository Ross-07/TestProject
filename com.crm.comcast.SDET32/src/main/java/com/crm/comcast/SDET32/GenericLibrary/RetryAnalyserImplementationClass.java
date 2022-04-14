package com.crm.comcast.SDET32.GenericLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementationClass implements IRetryAnalyzer
{
	int retryLimit = 5;
	int counter = 0;

	public boolean retry(ITestResult result) 
	{
		if(counter<retryLimit)
		{
			counter++;
			return true;
		}
		return false;
	}
	
}
