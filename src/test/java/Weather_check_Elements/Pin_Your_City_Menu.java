package Weather_check_Elements;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pin_Your_City_Menu 
{
	WebDriver driver;
	
	public String elements=null;
	
	String city=null;
	
	@FindBy(how=How.XPATH, using="//div[@id='messages']") private  WebElement DropDown;
	
	@FindBy(how=How.CLASS_NAME, using="searchBox") public WebElement CityInputBox;
	
	public Pin_Your_City_Menu (WebDriver driver)
	{
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickOperations(WebElement element )
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		element.click();
	}
}
