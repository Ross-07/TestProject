package com.crm.comcast.SDET32.Practice;

import org.testng.annotations.Test;

public class organizationTest 
{
	@Test
	public void createOrganizationTest()
	{
		System.out.println("organization has been created");
		System.out.println("=============================");
	}
	
	@Test
	public void createContactWithOrganizationName()
	{
		System.out.println("organization has been created");
		System.out.println("contact has been created");
		System.out.println("=================================");
	}
	
	@Test
	public void createOpportunityWithOrgNameAndContactInfo()
	{
		System.out.println("organization has been created");
		System.out.println("opportunity has been created");
		System.out.println("contact has been created");
		System.out.println("========================================");
	}
	
}
