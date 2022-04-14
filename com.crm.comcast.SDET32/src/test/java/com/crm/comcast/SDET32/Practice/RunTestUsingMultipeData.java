package com.crm.comcast.SDET32.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RunTestUsingMultipeData 
{
	@DataProvider
	public Object[][]dataProviderForTicketBooking()
	{
		Object [][] objArr = new Object[5][2];
		objArr[0][0] = "Banglore";
		objArr[0][1] = "Bhagalpur";
		
		objArr[1][0] = "Banglore";
		objArr[1][1] = "Jehanabad";
		
		objArr[2][0] = "Banglore";
		objArr[2][1] = "Patna";
		
		objArr[3][0] = "Banglore";
		objArr[3][1] = "Daltonganj";
		
		objArr[4][0] = "Banglore";
		objArr[4][1] = "Bhojpur";
		
		return objArr;
	}
	
	@Test(dataProvider ="dataProviderForTicketBooking")
	public void ticketBookingTest(String from,String to,int price)
	{
		System.out.println("Travelling from"+from+"to"+to);
	}
}
