package Weather_check_Base;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Base_Class {
	/*
	 *                                         Initializing following points 
	 * 1.Web driver 
	 * 2.Properties file 
	 * 3.Browser 
	 * 4.Logs
	 * 5.Extent report --> Not finalized it
	 * 
	 * Copy appropriate verion of chrome driver to the path: 
	 * System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_Drivers\\chromedriver.exe
	 * 
	 */

	public static WebDriver driver;

	public static Properties config = new Properties();
	
	public static Properties OR = new Properties();
	
	public static Logger log = Logger.getLogger("ConsoleLog");

	public static FileInputStream Properties_File;

	public static WebDriverWait wait;

	public static String elementData;
	
	/*
	 * initializing browser,driver & url before executing any of the test methods
	 * 
	 */

	@BeforeSuite
	public void startUp() 
	{
		if(driver==null)
		{
			try 
			{
				Properties_File = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\"
						+ "Weather_check_Properties\\config.properties");

				config.load(Properties_File);
				
				Properties_File = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\"
						+ "Weather_check_Properties\\OR.properties");
				
				OR.load(Properties_File); 
			} 
			catch (Exception  e) 
			{
				
				e.printStackTrace();
			} 

			if (config.getProperty("browser").equalsIgnoreCase("chrome")) // config.getProperty("browser").equalsIgnoreCase("chrome")
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_Drivers\\chromedriver.exe");

				System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")+"\\target\\SeleniumLogs.txt");
				
			       // Create object of HashMap Class
				Map<String, Object> prefs = new HashMap<String, Object>();
		              
		                // Set the notification setting it will override the default setting
				prefs.put("profile.default_content_setting_values.notifications", 2);
		 
		                // Create object of ChromeOption class
				ChromeOptions options = new ChromeOptions();
		 
		                // Set the experimental option
				options.setExperimentalOption("prefs", prefs);
				
				        // Disable the notification bar
				options.setExperimentalOption("useAutomationExtension", false);
				
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));  
		                // pass the options object in Chrome driver

				driver = new ChromeDriver(options);
				
				driver.manage().window().maximize();
				
				driver.manage().deleteAllCookies();

				driver.get(config.getProperty("testurl"));

				driver.manage().deleteAllCookies();

				wait = new WebDriverWait(driver, 30);

				// driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
						TimeUnit.SECONDS);
			} 
			else {
				if (config.getProperty("broeser").equalsIgnoreCase("Firefox")) 
				{
					System.out.println("Not yet implemented...");

				} 
				else 
				{
					if (config.getProperty("browser").equalsIgnoreCase("IE")) 
					{
						System.out.println("Not yet implemented");

					}
				}
			}
		}
		
	}	

	@AfterSuite
	public void clouser() 
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}
}
