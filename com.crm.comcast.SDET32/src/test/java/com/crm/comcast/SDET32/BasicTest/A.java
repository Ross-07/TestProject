package com.crm.comcast.SDET32.BasicTest;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.GenericLibrary.BaseClass;
@Listeners(com.crm.comcast.SDET32.GenericLibrary.ListenerImplementationClass.class)

public class A extends BaseClass
{
	@Test
	public void demo1()
	{
		System.out.println("In");
		Assert.fail();
		System.out.println("Out");
	}
}
