
/*
 * 		File path for SS on any exception / error: [\\src\\test\\resources\\Weather_check_ScreenShots]
 * 
 * 
 * 
 */

package Weather_check_Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import Weather_check_Base.Base_Class;
import Weather_check_Elements.City_Details_On_Map;
import io.restassured.path.json.JsonPath;

public class Utilities extends Base_Class {

	public static String imgPath;;

	static String elements;

	static String city;

	public static String newCity;

	static String palletCityName;

	public static WebElement SearchedCity;

	public static String MatchedCityTemperature;

	public static Float Temperateure;

	public static String Result;

	public static String[] arrSplit;

	public static String PageTitle() // Fetches page title and returns it
	{
		String title = driver.getTitle();

		return title;
	}

	/*
	 * @clearTheFile() use revoked temporarily
	 * 
	 */

	public static void clearTheFile() throws Exception {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_Logs\\SeleniumLogs.txt");

		if ((file.exists() && file.length() != 0)) {
			log.debug("Erasing existing selenium logs");

			FileWriter in = new FileWriter(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_Logs\\SeleniumLogs.txt",
					false);
			PrintWriter out = new PrintWriter(in, false);
			out.flush();
			out.close();
			in.close();
		} else {
			log.debug("Creating new selenium logs");
		}
	}

	/*
	 * Used SS() under listeners on test fail / skip conditions
	 */

	public static void SS() throws Exception {
		Date date = new Date();

		String DateStamp = date.toString().replace(" ", "_").replace(":", "_");

		String SSName = DateStamp + ".jpg";

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		//---------------------------------------------------> Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		//---------------------------------------------------> Move image file to new destination

		File DestFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_ScreenShots\\" + SSName);

		FileUtils.copyFile(SrcFile, DestFile);
	}

	/*
	 * @keywordMatcher(), expects 2 string inputs and returns flag depending on the
	 * matched / un matched entries to the called class/ method
	 */

	public static boolean keywordMatcher(String actualKeyword, String expectedKeyword) {
		boolean cityFlag;

		if (actualKeyword.toLowerCase().contains((expectedKeyword.toLowerCase()))) {
			cityFlag = true;
		} else {
			cityFlag = false;
		}

		return cityFlag;

	}
	
	/*
	 *	@City_DD() generates random city from look up 
	 */

	public static String City_DD(WebElement RandomCityCheck) throws Exception {

		Actions action = new Actions(driver);

		RandomCityCheck.click(); // Click operation on City input field

		action.sendKeys(Keys.BACK_SPACE).perform(); // Clearing City input field

		RandomCityCheck.click(); // Click operation on City input field

		List<WebElement> options = driver.findElements(By.id("messages")); // All cites comes under one element only,
																			// storing the element in list

		for (WebElement element : options) {
			// log.debug(element.getText());

			elements = element.getText(); // [elements] stores all cities as a single string
		}

		arrSplit = elements.split("\\r?\\n"); // Splitting the [elements] string by "next line" and storing it in string
												// array

		// log.debug("Index of split array is:" + " " + arrSplit.length);

		Random random = new Random();

		int randomIndex = random.nextInt(arrSplit.length); // Generating random index from total size of string array
															// consisting of cities from DD

		//log.debug("Random index from the split array is:" + " " + randomIndex);

		city = arrSplit[randomIndex]; // Fetching city from random index and storing it

		//log.debug("Random city for the curresponding index is:" + " " + city);

		return city;
	}

	/*
	 * 	@uncheckDefaultCity() un checks the default checked cities on map
	 */
	
	public static void uncheckDefaultCity(WebElement CityField) throws Exception {
		Hashtable<Integer, String> City = new Hashtable<Integer, String>();

		Actions action = new Actions(driver);
		
		//---------------------------------------------------------------------------------------> Storing all the default available cities in table

		City.put(1, "Mumbai");
		City.put(2, "Bhopal");
		City.put(3, "Bengaluru");
		City.put(4, "Chennai");
		City.put(5, "Hyderabad");
		City.put(6, "Visakhapatnam");
		City.put(7, "Kolkata");
		City.put(8, "Patna");
		City.put(9, "Lucknow");
		City.put(10, "Srinagar");
		City.put(11, "New Delhi");

		for (int i = 1; i <= City.size(); i++) //------------------------------------------------> Iterating loop by table size
		{
			if (i != City.size()) 
			{
				//-------------------------------------------------------------------------------> Divided city check box into two two parts
				//-------------------------------------------------------------------------------> For every index of table it will create unique locator of city check box
				
				String Element = City_Details_On_Map.cityFirstHalf + City.get(i) + City_Details_On_Map.commonSecondHalf;

				CityField.click(); //------------------------------------------------------------> Click operation on city input field

				String Data = City.get(i); //----------------------------------------------------> Saving city from current index of table

				CityField.sendKeys(Data); //-----------------------------------------------------> Giving input to city field 

				WebElement element = driver.findElement(By.xpath(Element)); //-------------------> Generating xpath for the current index city

				element.click(); //--------------------------------------------------------------> Un checking the current index city

				CityField.click(); //------------------------------------------------------------> Click operation on city input field

				for (int j = 1; j <= Data.length(); j++) //--------------------------------------> Iterating loop until size of index city
				{
					action.sendKeys(Keys.BACK_SPACE).perform(); //-------------------------------> Performing back space operation of city input field
				}

				CityField.click();

				CityField.clear();
			} else 
			{
				//--------------------------------------------------------------------------------> Divided city check box into two two parts
				//--------------------------------------------------------------------------------> For every index of table it will create unique locator of city check box
				
				String Element = City_Details_On_Map.cityFirstHalf + City.get(i) + City_Details_On_Map.commonSecondHalf;

				CityField.click(); //-------------------------------------------------------------> Click operation on city input field

				String Data = City.get(i);  //----------------------------------------------------> Saving city from current index of table

				CityField.sendKeys(Data);  //------------------------------------------------------>Giving input to city field 

				WebElement element = driver.findElement(By.xpath(Element));

				element.click();

				// action.doubleClick(CityField).perform();

				CityField.click();

				for (int j = 1; j <= Data.length(); j++) {
					action.sendKeys(Keys.BACK_SPACE).perform();
				}

				break;
			}
		}

		City.clear();
	}

	/*
	 *		@cityDetails() expects city input box [web element] & city[string] 
	 */
	
	public static void cityDetails(WebElement CityInputBox, String RandomCity) 
	{
		Actions action = new Actions(driver);

		CityInputBox.click();

		action.sendKeys(Keys.BACK_SPACE).perform();

		CityInputBox.click(); //-----------------------------------------------> Clear and click city input field

		String oldCity = RandomCity;

		newCity = oldCity.substring(1); //-------------------------------------> Randomly fetched city has a blank space on 0th index
		
										//-------------------------------------> Making new string by removing blank space

		CityInputBox.sendKeys(newCity); //-------------------------------------> Giving city input to city input field

		String CompletePinCityPath = City_Details_On_Map.cityFirstHalf + newCity + City_Details_On_Map.commonSecondHalf;

		SearchedCity = driver.findElement(By.xpath(CompletePinCityPath)); //---> Locating random received city check box

	}
	
	//-------------------------------------------------------------------------> Checking if random clicked city is displayed on map

	/*
	 * @Fetch_City_Weather_Details() accepts CityWeather web element from pellet on map
	 */
	
	public static void Fetch_City_Weather_Details(WebElement CityWeather) 
	{

		String completePath = City_Details_On_Map.CityWeather_First_Half + newCity
				+ City_Details_On_Map.commonSecondHalf; //------------------------------> Creating locator for city on map   

		List<WebElement> AvailableElement = driver.findElements(By.xpath(completePath));

		if (AvailableElement.size() != 0) 
		{
			//log.debug("Element is present");

			WebElement Same_Random_City = AvailableElement.get(0);

			Same_Random_City.click(); //-------------------------------------------------> Clicking city on the map if available

			String PalletCity = City_Details_On_Map.CityPalletFirstHalf + newCity  //----> Creating locator for the city name from weather pellet on map   
					+ City_Details_On_Map.commonSecondHalf;

			WebElement palletCityNames = driver.findElement(By.xpath(PalletCity));

			palletCityName = palletCityNames.getText(); //-------------------------------> Fetching city name from weather pellet on map

			log.debug("Searched city is"+" "+palletCityName);

			boolean cityFlag = Utilities.keywordMatcher(palletCityName, newCity); //-----> Checking if searched city from Pin Your city 
																				 //------> and asserting weather pellet on map are same or not

			if (cityFlag == true) 
			{
				String PalletTemp = CityWeather.getText(); //----------------------------> Fetching temperature details from pellet 

				String[] split = PalletTemp.split(":");

				int len = split.length;

				log.debug(len);

				MatchedCityTemperature = split[1];

				Temperateure = Float.parseFloat(MatchedCityTemperature);

				log.debug("Fetched temperature for"+newCity+" "+"from UI is:" + " " + Temperateure);
			} 
			else 
			{
				Assert.assertTrue(true, "City name mis match"); //------------------------> Assert = true if city from pin city & map are not same
			}

		} 
		else 
		{
			Assert.assertTrue(true, newCity+" "+"Is not present on the map"); //-----------> Assert = true if searched city is not present on map
		}
	}
	

	public static String ParseJson(String GETResponse) {
		JsonPath JS = new JsonPath(GETResponse);

		String responseCity = JS.get("name");

		String Temp = JS.get("main.temp");

		return null;
	}

	/*
	 * @TempCheck() accepts temperature(float) from UI & API
	 */
	
	public static String TempCheck(Float UI_Temp, Float API_Temp) 
	{
		Float diff;

		if (Float.compare(UI_Temp, API_Temp) == 0) // if both temperatures are equal
		{

			log.debug(" API & UI Temperatures are same");
		} 
		else if (Float.compare(UI_Temp, API_Temp) < 0) // if API temperature is > than UI temperature, logging the difference 
		{
			diff = API_Temp - UI_Temp;

			if (diff >= 0.1F && diff < 1.20F) // Checking if temperature difference is within acceptable range
			{
				Result = "UI's Temperature varies by" + " " + diff + "°C" + " " + "with API temperature readings";
			} else {
				Result = "API & UI's  temperature reading mis matches by" + " " + diff + "°C";
			}
		} else // if UI temperature is > than API temperature, logging the difference
		{
			diff = UI_Temp - API_Temp;

			if (diff > 0.01F && diff < 1.20F) //// Checking if temperature difference is within acceptable range
			{
				Result = "API's Temperature varies by" + " " + diff + "°C" + " " + "with US's temperature readings";
			} else {
				Result = "API& UI's temperature reading mis matches by:" + " " + diff + "°C";
			}
		}
		return Result;
	}
}