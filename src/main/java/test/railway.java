package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import utils.ExcelUtils;
//import utils.ExtentManager;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class railway {
    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver = null;
//        ExtentReports extent = null;
//        ExtentTest test = null;

       // try {
            // Setup
            System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
            WebDriver driver  = new ChromeDriver();
//            extent = ExtentManager.createInstance("reports/ErailTestReport.html");
//            test = extent.createTest("Erail Automation Test");

            // Step 1: Open URL
            driver.get("https://erail.in/");
           // test.pass("Opened Erail URL.");

            // Step 2–3: Handle "From" field
            WebElement fromField = driver.findElement(By.id("txtStationFrom"));
            fromField.click();
            fromField.clear();
          //  test.pass("Cleared 'From' field.");

            // Step 4: Enter "DEL" in the "From" field
            fromField.sendKeys("DEL");
            Thread.sleep(1000); // Wait for dropdown to load
           // test.pass("Entered 'DEL' in the 'From' field.");

            // Step 5: Select the 4th station and print
            List<WebElement> dropdownItems = driver.findElements(By.cssSelector(".ui-menu-item"));
            WebElement fourthStation = dropdownItems.get(3);
            String fourthStationName = fourthStation.getText();
            System.out.println("4th Station: " + fourthStationName);
            fourthStation.click();	
           // test.pass("Selected the 4th station: " + fourthStationName);

//            // Step 6: Write expected stations to Excel
//            List<String> expectedStations = List.of("DELHI", "NEW DELHI", "DELHI CANTT", "DELHI SARAI ROHILLA");
//            String expectedFilePath = "expectedStations.xlsx";
//            ExcelUtils.writeToExcel(expectedFilePath, "Stations", expectedStations);
//           // test.pass("Written expected stations to Excel.");
//
//            // Step 7: Write dropdown items to Excel and compare
//            List<String> actualStations = new ArrayList<>();
//            for (WebElement item : dropdownItems) {
//                actualStations.add(item.getText());
//            }
//            String actualFilePath = "actualStations.xlsx";
//            ExcelUtils.writeToExcel(actualFilePath, "Stations", actualStations);
//           // test.pass("Written dropdown stations to Excel.");
//
//            List<String> mismatches = new ArrayList<>(expectedStations);
//            mismatches.removeAll(actualStations);
//
//            if (mismatches.isEmpty()) {
//                test.pass("All expected stations are present in the dropdown.");
//            } else {
//                test.fail("Mismatches found: " + mismatches);
//            }

            // Step 8: Set dynamic date
            WebElement dateField = driver.findElement(By.id("txtDate"));
            WebElement selectDateOnly = driver.findElement(By.id("chkSelectDateOnly"));
            selectDateOnly.click();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date targetDate = new Date(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
            String formattedDate = sdf.format(targetDate);

            dateField.clear();
            dateField.sendKeys(formattedDate);
          //  test.pass("Selected date 30 days from now: " + formattedDate);

//        } catch (Exception e) {
//            if (test != null) {
//                test.fail("Test failed: " + e.getMessage());
//            }
//        } finally {
//            if (driver != null) {
//                driver.quit();
//            }
//            if (extent != null) {
//                extent.flush();
            }
        
    }

