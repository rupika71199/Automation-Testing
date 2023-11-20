package cawAutomation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSDETProject {
	String jsonvalue = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\" },{\"name\" : \"George\", \"age\" : 42, \"gender\": \"male\" },{\"name\" : \"Sara\", \"age\" : 42, \"gender\": \"female\" },{\"name\" : \"Conor\", \"age\" : 40, \"gender\": \"male\" },{\"name\" : \"Jennifer\", \"age\" : 42, \"gender\": \"female\" }]";
	static WebDriver driver;
	
	@BeforeAll
	public static void openBrowserWithUrl() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		// Instantiating the driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Opening the url in browser
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void validateJSONData() {
		
		driver.findElement(By.tagName("details")).click();
		//Finding the web element of the text area
		WebElement text = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
		
		// Clearing the existing text and sending the data 
		text.clear();
		text.sendKeys(jsonvalue);
		
		// Finding the element of the Refresh and clicking on it 
		driver.findElement(By.id("refreshtable")).click();
		
		//Waiting for the page to get refreshed 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Checking both the values
		assertEquals(SDETProject.deriveTextsFromTable(driver),jsonvalue);
	}
	@AfterAll
	public static void endSession() {
		driver.quit();
	}

}
