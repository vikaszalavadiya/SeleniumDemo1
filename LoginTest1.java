package testCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
//import pageObjects.ProductCatalogue;

//==================================================
// extend the parent Class (BaseTest)
public class LoginTest1 extends BaseTest {
	
	
	@Test
	public void loginTest1() throws IOException, InterruptedException {
	String productName = "ZARA COAT 3";
	
	//==================================================
	// LandingPage = Login page
	// Create the Object for Lading Page
	LoginPage loginPage = new LoginPage(driver);
	// go to page url
	//landingPage.goToPage();
	// enter the email and password
	loginPage.loginApplication("Admin", "admin123");
	
	//==================================================
	// Create the Object for Product Catalogue page
	//ProductCatalogue productcatalogue = new ProductCatalogue(driver); 
	//List<WebElement> products = productcatalogue.getProductList();
	}
	
	
	
	



	
}




 

























