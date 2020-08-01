package API_Function;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.*;

import Weather_check_Base.Base_Class;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Weather_Info_API extends Base_Class
{
	public static String responseCity;
	
	public static Float Temp;
	
	public static void cityTemp(String cityReceived) throws Exception
	{
		Properties config = new Properties();
		
		Properties OR = new Properties();
		
		Properties_File = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\"
				+ "Weather_check_Properties\\config.properties");

		config.load(Properties_File);
		
		Properties_File = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\"
				+ "Weather_check_Properties\\OR.properties");

		OR.load(Properties_File);
		
		RestAssured.baseURI = config.getProperty("API_BASE_URL");
		
		String GETResponse = given().log().all().queryParam(OR.getProperty("key"), OR.getProperty("keyValue"))
				.queryParam(OR.getProperty("key2"), cityReceived)
				.queryParam(OR.getProperty("key3"), OR.getProperty("key3Value"))
				.when().get(config.getProperty("API_End_Point"))
				.then().log().all().extract().response().asString();
		
		JsonPath JS = new JsonPath(GETResponse);
		
		responseCity = JS.get("name");
		
		try 
		{
			Temp = JS.get("main.temp");
		} 
		catch (Exception e) 
		{
			Temp = (float) JS.getFloat("main.temp");
		}
		
		System.out.println(responseCity);
		
		System.out.println(Temp);
	}
}
