package Weather_check_Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage_Elements 
{
	WebDriver driver;
	
	static WebDriverWait wait;
	
	@FindBy(how=How.ID, using="h_sub_menu") public  WebElement SubMenu;
	
	@FindBy(how=How.CLASS_NAME, using="allow") private static WebElement Permission;
	
	@FindBy(how=How.XPATH, using="//*[contains(text(),'WEATHER')]") public  WebElement Weather;
	
	public HomePage_Elements(WebDriver driver)
	{
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void Permission()
	{
		//wait = new WebDriverWait(driver,20);
		
		for(int i=0;i<=10;i++)
		{
			try
			{
				//wait.until(ExpectedConditions.elementToBeClickable(Permission));
				
				Permission.click();
				
				System.out.println("Clicked");
				
				break;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				i++;
			}
		}
	}
	
	public static void clickOperations(WebDriver driver,WebElement element)
	{
		
		Actions action = new Actions(driver);
		
		for(int i=0;i<=6;i++)
		{
			try
			{
				action.moveToElement(element);
				
				action.click(element).perform();
				
				break;
			}
			catch(Exception e2)
			{
				i++;
			}
		}
	}
}


