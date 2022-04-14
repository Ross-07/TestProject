package com.crm.comcast.SDET32.BasicTest;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.GenericLibrary.BaseClass;

public class DemoRetryAnalyser extends BaseClass
{
	@Test(retryAnalyzer = com.crm.comcast.SDET32.GenericLibrary.RetryAnalyserImplementationClass.class)
	public void DemoTest()
	{
		System.out.println("=========Step1=========");
		Assert.fail();
		System.out.println("=============step2=========");
	}
}
