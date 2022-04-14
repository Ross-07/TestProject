package com.crm.comcast.SDET32.Practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
 
public class SampleProgramForExecuteUpdateTest {

	public static void main(String[] args) throws SQLException 
	{
		Connection connection = null;
		try
		{
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("insert into student values(1005,'Deva',9886);");
		if (result==1)
		{
			System.out.println("the table has been updated");
		}
		else
		{
			System.out.println("table has not been created");
		}
		}
		catch(Exception e)
		{
			//handle 
		}
		finally
		{
			connection.close();
			System.out.println("database has been close successfully");
		}
		
	}

}
