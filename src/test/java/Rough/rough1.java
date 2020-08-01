package Rough;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Weather_check_Base.Base_Class;
import Weather_check_Utilities.Utilities;

public class rough1
{
	//@Test
	public static void TESTmain() throws Exception
	{
		//Utilities.SS();
		
		String one="New Delhi, Delhi";
		
		String two = "New Delhi";
		
		if(one.toLowerCase().contains(two.toLowerCase()))
		{
			System.out.println("Keyword matches");
		}
		else
		{
			System.out.println("Keyword doesnt match");
		}
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\Weather_check_Drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		
		//Utilities.SS();
		
		driver.quit();
	}
}
