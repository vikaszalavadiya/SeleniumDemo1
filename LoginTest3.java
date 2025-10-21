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

public class LoginTest3 extends BaseTest {
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		
		String path = System.getProperty("user.dir") + "/reports/index.html";
		
		// Create Spark Reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Automation Test Results");
		reporter.config().setReportName("Central Application");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("UTF-8");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setOfflineMode(true);
		reporter.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");
		reporter.config().setJs("console.log('Extent Report Loaded');");
		reporter.config().setCss(".badge-primary { background-color: #00bcd4; }");
		
		
		// Create ExtentReports instance
		extent = new ExtentReports();
		
		// Attach reporter
		extent.attachReporter(reporter);
		
		
		// Set system/environment info
		extent.setSystemInfo("Tester", "Vikas Zalavadiya");
		extent.setSystemInfo("Environment", "Staging");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));

		// Add custom runner output (optional)
		extent.addTestRunnerOutput("Test execution started...");
	}

	@Test()
	public void loginTest1() throws IOException, InterruptedException {
	
		
		ExtentTest test = extent.createTest("Initial Demo");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication( String.valueOf(ConfigReader.get("username")),  String.valueOf(ConfigReader.get("password")));
		// Verify
		Thread.sleep(3000);
		String header = driver.findElement(By.cssSelector("h6.oxd-topbar-header-breadcrumb-module")).getText();
		AssertJUnit.assertEquals(header, "Dashboard", "Page title does not match!");	
	}
	@AfterTest
    public void tearDownReport() {
        extent.flush();
    }

	
}