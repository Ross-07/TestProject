package com.crm.comcast.SDET32.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RuntestUsingMultipleDataHavingMultipleColumnTest01 
{
	@DataProvider
	public Object[][] dataProviderForTicketBooking()
	{
		Object [][] objArr = new Object[5][3];
		objArr[0][0] = "Banglore";
		objArr[0][1] = "Bhagalpur";
		objArr[0][2] = 6000;
		
		objArr[1][0] = "Banglore";
		objArr[1][1] = "Jehanabad";
		objArr[1][2] = 4020;
		
		objArr[2][0] = "Banglore";
		objArr[2][1] = "Patna";
		objArr[2][2] = 3600;
		
		objArr[3][0] = "Banglore";
		objArr[3][1] = "Daltonganj";
		objArr[3][2] = 6000;
		
		objArr[4][0] = "Banglore";
		objArr[4][1] = "Bhojpur";
		objArr[4][2] = 4050;
		
		return objArr;
	}
	
	@Test(dataProvider ="dataProviderForTicketBooking")
	public void ticketBookingTest(String from, String to, int price)
	{
		System.out.println("Travelling from "+from+" to" +to+" and the price is "+price);
	}
}
