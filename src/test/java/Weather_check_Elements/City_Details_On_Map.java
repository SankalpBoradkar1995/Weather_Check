package Weather_check_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class City_Details_On_Map 
{
	WebDriver driver;
	
	
	public static String CityWeather_First_Half = "//div[contains(text(),'";
	
	public static String cityFirstHalf = "//label[contains(text(),'";
	
	public static String CityPalletFirstHalf = "//span[contains(text(),'";
	
	public static String commonSecondHalf = "')]";
	
	@FindBy(how=How.XPATH, using="//*[@class='leaflet-pane leaflet-popup-pane']") public WebElement CityWeatherPalet;
	
	@FindBy(how=How.XPATH, using="//b[contains(text(),'Temp in Degrees:')]") public WebElement TemperaturePalate;
	
	//span[contains(text(),'New Delhi')]
	
	//b[contains(text(),'Temp in Degrees:')]
	
	public City_Details_On_Map(WebDriver driver)
	{
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
}
