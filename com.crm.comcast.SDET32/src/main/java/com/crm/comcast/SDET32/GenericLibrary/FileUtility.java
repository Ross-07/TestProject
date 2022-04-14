package com.crm.comcast.SDET32.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility 
{
	public String GetDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fisForProperty = new FileInputStream("./src/test/resources/PropertyFiless.properties");
		Properties property = new Properties();
		property.load(fisForProperty);
		return property.getProperty(key);
	}
	public String GetDataFromJsonFile(String filePath,String key) throws IOException, ParseException
	{
		// create an object of jsonParser
		JSONParser parser = new JSONParser();
		//convert physical file to java object using FileReader
		FileReader file = new FileReader(filePath);
		//convert JSON file to java file 
		Object obj = parser.parse(file);
		//Downcast the object type of reference to JsonObject type to access the get method
		// by the ruleOf java we cannot access sub class members from super class
		JSONObject jObj = (JSONObject) obj;
		//Printing the value into the console using the JSON file keys using get method
		return jObj.get(key).toString();
		
		
	}
}
