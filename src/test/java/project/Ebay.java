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
			WebElement searchBox = driver
					.findElement(By.xpath("//table[@class='gh-tbl2']/tbody/tr/td/div/div/input[@type='text']"));
			searchBox.sendKeys("book");

			// Step 4: Click on Search
			WebElement searchButton = driver.findElement(By.xpath("//input[@id='gh-btn']"));
			searchButton.click();

			Thread.sleep(10000);

			// Step 5: Click on the first item in the results
			WebElement firstItem = driver.findElement(By.xpath(
					"//ul[@class='srp-results srp-list clearfix']/li[1]/div/div[contains(@class,'s-item__image-section')]"));
			firstItem.click();

			Thread.sleep(7000);
			ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newTb.get(1));
			// Step 6: Click 'Add to cart' on the item listing page
			WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(@id,'atcBtn')]"));
			addToCartButton.click();

			Thread.sleep(5000);

			// Step 7: Verify the cart has been updated
			WebElement cartIcon = driver.findElement(By.xpath("//a[contains(@class,'menu gh-cart-count-1')]"));
			String cartItemCount = cartIcon.getText();
			Assert.assertEquals(cartItemCount, "1", "Cart count did not update correctly.");

			System.out.println("Item successfully added to cart.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step 8: Close browser
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
