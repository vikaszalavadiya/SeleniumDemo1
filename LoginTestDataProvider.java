package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.JsonDataReader;

public class LoginTestDataProvider extends BaseTest {
	
	
	DataFormatter formatter = new DataFormatter();
	
	@Test(dataProvider ="drivenTest")
	public void loginTest1(String username, String pass) throws IOException, InterruptedException {
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication(username , pass );
		
	}
	
	@DataProvider (name = "drivenTest")
	public Object[][] getData() throws IOException  {
		
		// path of your Excel file// path of your Excel file
		FileInputStream fis = new FileInputStream("/home/local/CORPORATE/vikas/eclipse-workspace/SeleniumPro2/login.xlsx");
		
		//Create XSSFWorkbook instance (for .xlsx files)
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Get the "Sheet1" from multiple sheets (o sheet index he) 
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Get the count of Rows in sheet
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("rowCount"+ rowCount);
		
		// Get first row
		XSSFRow row = sheet.getRow(0);
		
		// Get last column of first row
		int colCount = row.getLastCellNum();
		
		// Create array base on excel ki row and column count
		Object[][] data = new Object [rowCount -1][colCount];
		
		// For loop for row - get all row
		for (int i =0; i<rowCount -1 ;i++) {
			
			// get the row one by one 
			row = sheet.getRow(i +1);
			
			// for loop for column - get all column
			for (int j = 0; j<colCount; j++) {		
				// Store to variable
				XSSFCell cell = row.getCell(j);	
				// store to array
				// jo bhi type of data he in excel usko string me convert kar ke array me store karega
				data [i][j] = formatter.formatCellValue(cell);
			}
		}
 		return data;
		
	}
}
