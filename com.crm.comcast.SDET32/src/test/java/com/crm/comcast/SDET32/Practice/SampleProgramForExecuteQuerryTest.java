package com.crm.comcast.SDET32.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



public class SampleProgramForExecuteQuerryTest {

	public static void main(String[] args) throws SQLException 
	{
		// Step1 - Register The database
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef); 
		
		//Step2.- Get connection to the database.
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		
		//step 3- create SQL statement
		Statement statement = connection.createStatement();
		
		
		
		//Step 4- Execute SQL Querry
		ResultSet result= statement.executeQuery("select * from student;");
		
		while(result.next())
		{
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		}
		
		//step 5- close the database
		connection.close();
		
	}

}
