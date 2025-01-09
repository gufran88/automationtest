package project;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class Api {

    public static void main(String[] args) {
        // Step 1: Set the Base URI
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice.json";

        try {
            // Step 2: Send the GET Request and capture the response
            Response response = RestAssured
                    .given()
                    .when()
                    .get()
                    .then()
                    .statusCode(200) // Assert status code is 200
                    .extract().response();

            // Step 3: Verify the response contains the BPI data
            // Parse response as JSON and extract the BPI object
            var bpi = response.jsonPath().getMap("bpi");

            // Assert that BPI contains 3 currencies: USD, GBP, EUR
            if (!bpi.containsKey("USD")) {
                throw new AssertionError("BPI does not contain USD.");
            }
            if (!bpi.containsKey("GBP")) {
                throw new AssertionError("BPI does not contain GBP.");
            }
            if (!bpi.containsKey("EUR")) {
                throw new AssertionError("BPI does not contain EUR.");
            }
            if (bpi.size() != 3) {
                throw new AssertionError("BPI does not contain exactly 3 currencies.");
            }

            // Step 4: Verify the GBP description
            var gbpDetails = (Map<String, Object>) bpi.get("GBP");
            String gbpDescription = (String) gbpDetails.get("description");
            if (!"British Pound Sterling".equals(gbpDescription)) {
                throw new AssertionError("GBP description mismatch.");
            }

            // Step 5: Print success message
            System.out.println("All validations passed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
