package project;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class Api {

	public static void main(String[] args) {

		try {
			// Step 1: Setting base uri and hit the GET Request
			Response response = RestAssured.given().baseUri("https://api.coindesk.com").when()
					.get("/v1/bpi/currentprice.json").then().statusCode(200).extract().response();

			// Step 2: Verify and check the response for data

			var bpi = response.jsonPath().getMap("bpi");

			Assert.assertTrue(bpi.containsKey("USD"));
			Assert.assertTrue(bpi.containsKey("EUR"));
			Assert.assertTrue(bpi.containsKey("GBP"));
			Assert.assertTrue(bpi.size() == 3);

			// Step 3: Verify the description for GBP
			var gbpDetails = (Map<String, Object>) bpi.get("GBP");
			String gbpDescription = (String) gbpDetails.get("description");
			Assert.assertTrue("British Pound Sterling".equals(gbpDescription));

			// Step 4: Success message
			System.out.println("All validations passed successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
