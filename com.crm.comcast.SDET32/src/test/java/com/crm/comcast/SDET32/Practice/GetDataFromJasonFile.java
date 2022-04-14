package com.crm.comcast.SDET32.Practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetDataFromJasonFile {

	public static void main(String[] args) throws IOException, ParseException 
	{
		//create an object of JSONParser
		JSONParser parser = new JSONParser();
		//convert physical file to java object using FileReader
		FileReader File = new FileReader("./src/test/resources/JasonFile.json");
		//convert Json file to java file
		Object obj = parser.parse(File);
		//Downcast the object type of reference to JsonObject type to access the get method
		// By the rule of java we cannot access sub class members from super class reference
		JSONObject jObj = (JSONObject)obj;
		// Printing the values into the console using the Json file keys using get method
		System.out.println(jObj.get("browser"));
		System.out.println(jObj.get("url"));
		System.out.println(jObj.get("username"));
		System.out.println(jObj.get("password"));





	}

}
