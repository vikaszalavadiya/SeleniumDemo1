package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;
import utils.JsonDataReader;

public class LoginTest2 extends BaseTest {
	
	@DataProvider
	public Object[][] getData() throws IOException {
		// Call JsonDataReader to read JSON
		List<HashMap<String, String>> data = JsonDataReader.getJsonData();

		// Convert List → Object[][] for TestNG
		Object[][] result = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			result[i][0] = data.get(i);
		}

		return result;
	}

	// Test Method
	@Test(dataProvider = "getData")
	public void loginTest1(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginApplication(input.get("username"), input.get("password"));

		// Verify
		Thread.sleep(3000);
		String header = driver.findElement(By.cssSelector("h6.oxd-topbar-header-breadcrumb-module")).getText();
		AssertJUnit.assertEquals(header, "Dashboard", "Page title does not match!");
	}	
}

////Test Method 
//	@Test (dataProvider="getData")
//	public void loginTest1(String name, String psk ) throws IOException, InterruptedException {
//
//	LoginPage loginPage = new LoginPage(driver);
//
//	loginPage.loginApplication(name, psk);
//	
//	// Verify
//	Thread.sleep(3000);
//	String header = driver.findElement(By.cssSelector("h6.oxd-topbar-header-breadcrumb-module")).getText();
//	Assert.assertEquals(header, "Dashboard", "Page title does not match!");
//}
////Data Provide to Login Method
//@DataProvider
//public Object[] [] getData(){
//		return new Object[] [] {
//			{"testuser","12345"},
//			{"Admin","admin123"}
//			};
//	}
