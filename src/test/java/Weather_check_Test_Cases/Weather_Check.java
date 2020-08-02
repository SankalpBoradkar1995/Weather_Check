
/*
 *  This class includes main flow for the assignment:
 *  
 * 			i.e to fetch any given city from Pin Your City option from 
 * 			[https://social.ndtv.com/static/Weather/report/?pfrom=home-topsubnavigation]
 * 
 * 			Fetch temperature for selected city from UI
 * 
 * 			Make API call to fetch temperature details of the same city as from UI
 * 
 * 			Compare temperature received from UI & API response
 * 
 * 			Verify temperature ranges if in case of difference between values of UI & API
 * 
 * Test methods used are:
 * 
 * 			@NavigateToWeatherPage()

 * 			@CityCheck()
 * 
 * 			@CityWeatherCheck()
 * 
 * 			@API_Call()
 * 
 * 			@tempCompare()
 * 
 * Custom utilities used:
 * 
 * 			PageTitle() -------------------> Used in @NavigateToWeatherPage()
 * 
 * 			uncheckDefaultCity() ----------> Used in @CityCheck()
 * 
 * 			City_DD() ---------------------> Used in @CityCheck()
 * 
 * 			cityDetails() -----------------> Used in @CityCheck()
 * 
 * 			Fetch_City_Weather_Details() --> Used in @CityWeatherCheck()
 * 
 * 			TempCheck() -------------------> Used in @tempCompare()
 * 
 * PageFactory class used:
 * 
 * 			Pin_Your_City_Menu
 * 
 * 			City_Details_On_Map
 * 
 * 			HomePage_Elements
 * 
 * 			
 */




package Weather_check_Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import API_Function.Weather_Info_API;
import Weather_check_Base.Base_Class;
import Weather_check_Elements.City_Details_On_Map;
import Weather_check_Elements.HomePage_Elements;
import Weather_check_Elements.Pin_Your_City_Menu;
import Weather_check_Utilities.Utilities;

public class Weather_Check extends Base_Class {
	@Test(priority = 1)
	public void NavigateToWeatherPage() throws Exception {
		log.debug("Browser initilized");

		HomePage_Elements HomePage_Elements_Elements = new HomePage_Elements(driver); //---------------> Initializing home page elements
																					 //----------------> from HomePage_Elements class

		WebElement NextPane = HomePage_Elements_Elements.SubMenu; //-----------------------------------> Accessing the initialized element from
																 //------------------------------------> HomePage_Elements class

		WebElement Weather = HomePage_Elements_Elements.Weather;

		HomePage_Elements_Elements.Permission(); //----------------------------------------------------> Allowing the update permission on home page using Permission()
												//-----------------------------------------------------> method of HomePage_Elements class

		HomePage_Elements.clickOperations(driver, NextPane); //----------------------------------------> Clicking button to expand the pane

		HomePage_Elements.clickOperations(driver, Weather); //-----------------------------------------> Clicking weather option from next pane

		String title = Utilities.PageTitle(); //-------------------------------------------------------> Fetching the expected page's title using PageTitle() utility
		
		String ExpectedTitle = OR.getProperty("LandingURL");
		
		if(title.equalsIgnoreCase(ExpectedTitle)) //---------------------------------------------------> Checking if navigating to correct page
		{
			log.debug("Landing on intended page"+" "+title);
		}
		else
		{
			Assert.assertTrue(false, "Incorrect page landing"+" "+title); //--------------------------------------> Assert = true if incorrect page landing
		}
	}

	/*
	 * @CityCheck() depends on status of NavigateToWeatherPage, for its execution
	 * with priority as 2nd
	 * 
	 */

	@Test(dependsOnMethods = { "NavigateToWeatherPage" }, priority = 2)

	public void CityCheck() throws Exception {

		Pin_Your_City_Menu Pin_Your_City_Menu_Obj = new Pin_Your_City_Menu(driver); //-------------------> Initializing elements of
																					//-------------------> Weather_Page class

		/*
		 * @uncheckDefaultCity() from Utilities takes web element of city input field
		 * from Pin Your City option on weather page as parameter
		 */

		Utilities.uncheckDefaultCity(Pin_Your_City_Menu_Obj.CityInputBox); //----------------------------> Un checking default checked cities -->

		/*
		 * @City_DD() returns random city picked from the Pin Your City option on
		 * weather page
		 * 
		 */

		String RandomCity = Utilities.City_DD(Pin_Your_City_Menu_Obj.CityInputBox); // Fetching all the available cities
																					// from the Pin Your city option on
																					// the Weather page

		WebElement cityField = Pin_Your_City_Menu_Obj.CityInputBox; //------------------------------------> Mapped City input field from Pin Your City option
																	//------------------------------------> to a WebElement

		Pin_Your_City_Menu_Obj.clickOperations(cityField); //---------------------------------------------> Click operation on City input field

		/*
		 * @cityDetails(@param1,@Param2) expets 2 parameters 1. City input box
		 * WebELement 2. Randomly picked city form City_DD() utility
		 */

		Utilities.cityDetails(Pin_Your_City_Menu_Obj.CityInputBox, RandomCity); // cityDetails(), adds randomly picked
																				// city to input box and fetches
																				// WebElement of the generated city
																				// check box
	}

	/*
	 * @CityWeatherCheck() depends on [ NavigateToWeatherPage() , CityCheck() ] for
	 * its execution with priority as 3rd
	 */

	@Test(dependsOnMethods = { "NavigateToWeatherPage", "CityCheck" }, priority = 3)

	public void CityWeatherCheck() {

		City_Details_On_Map City_Details_On_Map_Obj = new City_Details_On_Map(driver); //--------------------> Initializing elements of
																					  //---------------------> City_Details_On_Map class

		// Click operation on city checkbox element generated by cityDetails() utility

		Utilities.SearchedCity.click(); // It opens the weather details pellet of selected city on map

		/*
		 * @Fetch_City_Weather_Details(param1) expects WebELement parameter
		 */

		Utilities.Fetch_City_Weather_Details(City_Details_On_Map_Obj.TemperaturePalate); // Sending City temperature
																							// field element from city
																							// weather pellet
	}

	@Test(dependsOnMethods = { "NavigateToWeatherPage", "CityCheck", "CityWeatherCheck" }, priority = 4)

	public void API_Call() throws Exception {
		// Taking fetched city on map
		String checkedCity = Utilities.newCity;

		// Calling api to fetch temp response for fetched city
		Weather_Info_API.cityTemp(checkedCity);

		// Comparing temperatures from UI & API
	}

	@Test(dependsOnMethods = { "NavigateToWeatherPage", "CityCheck", "CityWeatherCheck", "API_Call" }, priority = 5)

	public void tempCompare() 
	{
		// Fetching temperature from UI
		Float UI_Temperature = Utilities.Temperateure;

		// Fetching temperature from API response
		Float API_Temp = Weather_Info_API.Temp;

		// Comparing two temperature elements

		Float temperatureDifference = Utilities.TempCheck(UI_Temperature, API_Temp);
		
		SoftAssert softAssert = new SoftAssert();
		
		if (temperatureDifference >= 0.1F && temperatureDifference < 1.20F) // Checking if temperature difference is within acceptable range
		{
			log.debug("UI's Temperature varies by" + " " + temperatureDifference + "°C" + " " + "with API temperature readings");
		} else 
		{
			
			softAssert.assertTrue(false, "API & UI's  temperature reading mis matches by" + " " + temperatureDifference + "°C");
		}
		
		if (temperatureDifference > 0.01F && temperatureDifference < 1.20F) //// Checking if temperature difference is within acceptable range
		{
			log.debug("API Temperature varies by" + " " + temperatureDifference + "°C" + " " + "with UI temperature readings");
		} 
		else 
		{	
			softAssert.assertTrue(false, "API& UI's temperature reading mis matches by:" + " " + temperatureDifference + "°C");
		}
		
		softAssert.assertAll("API & UI temperature value for selected city differes by:"+" "+temperatureDifference);
	}
}
