package project;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Ebay {

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Step 1: Setup WebDriver
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Step 2: Open eBay homepage
            driver.get("https://www.ebay.com");

            // Step 3: Search for 'book'
            WebElement searchBox = driver.findElement(By.xpath("//table[@class='gh-tbl2']/tbody/tr/td/div/div/input[@type='text']"));
            searchBox.sendKeys("book");
           // WebElement searchButton = driver.findElement(By.id("gh-btn"));
        //    searchButton.click();
            
            Thread.sleep(15000);

            // Step 4: Click on the first item in the results
            WebElement firstItem = driver.findElement(By.xpath("//ul[@id='ui-id-1']/li[1]/a[contains(@aria-label,'bookshelf speakers')]"));
            firstItem.click();
            
            //Step 5: Click on first item from the detail page
            
            WebElement article = driver.findElement(By.xpath("//span[contains(text(),'Dayton Audio Classic B65 Bookshelf Speaker Pair Wo')]"));
            article.click();
            
            Thread.sleep(5000);
            ArrayList<String> newTb = new
            ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(newTb.get(1));
            // Step 5: Click 'Add to cart' on the item listing page
            WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(@id,'atcBtn')]"));
            addToCartButton.click();
            
            Thread.sleep(5000);

            // Step 6: Verify the cart has been updated
            WebElement cartIcon = driver.findElement(By.xpath("//a[contains(@class,'menu gh-cart-count-1')]"));
            String cartItemCount = cartIcon.getText();
            Assert.assertEquals(cartItemCount, "1", "Cart count did not update correctly.");

            System.out.println("Item successfully added to cart.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 7: Close browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
