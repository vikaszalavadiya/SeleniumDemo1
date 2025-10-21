package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.InteractiveReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.JsonDataReader;

public class LoginTest4 extends BaseTest {

	
	@Test()
	public void loginTest1() throws IOException, InterruptedException {
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication( String.valueOf(ConfigReader.get("username")),  String.valueOf(ConfigReader.get("password")));
	
		//Thread.sleep(3000);
		//String header = driver.findElement(By.cssSelector("h6.oxd-topbar-header-breadcrumb-module")).getText();
		//AssertJUnit.assertEquals(header, "Dashboard", "Page title does not match!");	
	}
	
	@Test()
	public void loginTest2() throws IOException, InterruptedException {
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication( String.valueOf(ConfigReader.get("username")),  String.valueOf(ConfigReader.get("password")));
		
	}
}


