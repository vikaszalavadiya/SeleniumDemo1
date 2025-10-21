package testCases;

// Importing necessary libraries
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DownloadUpload2 {

    public static void main(String[] args) throws IOException {
        String fruitName = "Apple";                       // Name to search in Excel
        String updatedValue = "603";                      // New value to update
        String fileName = "/home/local/CORPORATE/vikas/Downloads/download.xlsx";  // Path to Excel file

        WebDriver driver = new ChromeDriver();            // Launch Chrome browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7)); // Set implicit wait
        driver.get("https://rahulshettyacademy.com/upload-download-test/index.html"); // Open web page

        driver.findElement(By.cssSelector("#downloadButton")).click(); // Click Download button

        // Get column index of "Price" and row index of "Apple" from Excel
        int col = getColumnNumber(fileName, "Price");     
        int row = getRowNumber(fileName, "Apple");        

        // Update cell value and verify success
        Assert.assertTrue(updateCell(fileName, row, col, updatedValue));

        // Locate Upload button and upload updated Excel file
        WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
        upload.sendKeys(fileName);

        // Wait for toast (success message) to appear
        By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

        // Capture and print the toast message
        String toastText = driver.findElement(toastLocator).getText();
        System.out.println(toastText);

        // Verify message text
        Assert.assertEquals(toastText, "Updated Excel Data Successfully.");

        // Wait until toast disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));

        // Find "Price" column’s ID in web table
        String priceColumn = driver.findElement(By.xpath("//div[text()='Price']"))
                                   .getAttribute("data-column-id");

        // Find actual price value of "Apple" in table after upload
        String actualPrice = driver.findElement(
                By.xpath("//div[text()='" + fruitName + "']/parent::div/parent::div/div[@id='cell-" 
                         + priceColumn + "-undefined']")
        ).getText();

        System.out.println(actualPrice);                  // Print updated price
        Assert.assertEquals(updatedValue, actualPrice);   // Verify value is updated

        driver.quit();                                   // Close browser
    }

    // Method to update a specific cell value in Excel
    private static boolean updateCell(String fileName, int row, int col, String updatedValue) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);     // Read Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);           // Create workbook object
        XSSFSheet sheet = workbook.getSheet("Sheet1");           // Open first sheet

        Row rowField = sheet.getRow(row - 1);                    // Get target row (index starts from 0)
        Cell cellField = rowField.getCell(col - 1);              // Get target cell
        cellField.setCellValue(updatedValue);                    // Update cell value

        FileOutputStream fos = new FileOutputStream(fileName);   // Open file for writing
        workbook.write(fos);                                     // Save updated Excel

        workbook.close();                                        // Close workbook
        fis.close();                                             // Close file input
        fos.close();                                             // Close file output

        return true;                                             // Return success
    }

    // Method to find the row number where text (like "Apple") is found
    private static int getRowNumber(String fileName, String text) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);     // Open Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);           // Create workbook object
        XSSFSheet sheet = workbook.getSheet("Sheet1");           // Get first sheet

        Iterator<Row> rows = sheet.iterator();                   // Get all rows
        int k = 1;                                               // Row counter
        int rowIndex = -1;                                       // Default row index

        while (rows.hasNext()) {                                 // Loop through all rows
            Row row = rows.next();                               // Get next row
            Iterator<Cell> cells = row.cellIterator();           // Get all cells in that row

            while (cells.hasNext()) {                            // Loop through all cells
                Cell cell = cells.next();
                // Check if cell has text and matches our search word
                if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
                    rowIndex = k;                                // Save matching row number
                }
            }
            k++;                                                 // Move to next row count
        }

        workbook.close();                                        // Close workbook
        fis.close();                                             // Close file
        return rowIndex;                                         // Return row index
    }

    // Method to find column number for given column name (like "Price")
    private static int getColumnNumber(String fileName, String colName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);     // Open Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);           // Create workbook object
        XSSFSheet sheet = workbook.getSheet("Sheet1");           // Get first sheet

        Iterator<Row> rows = sheet.iterator();                   // Get all rows
        Row firstRow = rows.next();                              // Get header row

        Iterator<Cell> ce = firstRow.cellIterator();             // Get all header cells
        int k = 1;                                               // Column counter
        int column = 0;                                          // Default column index

        while (ce.hasNext()) {                                   // Loop through header cells
            Cell value = ce.next();
            if (value.getStringCellValue().equalsIgnoreCase(colName)) { // Match with column name
                column = k;                                      // Save matching column index
            }
            k++;                                                 // Move to next column
        }

        workbook.close();                                        // Close workbook
        fis.close();                                             // Close file

        System.out.println("Column Index for " + colName + ": " + column); // Print result
        return column;                                           // Return column index
    }
}
