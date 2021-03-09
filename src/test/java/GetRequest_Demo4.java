import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRequest_Demo4 {

	@Test
	public void getWeatherDetails() {
		
		// Set base URI
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Response Object
		Response response = httpRequest.request(Method.GET, "?q=Delhi&appid=6c6d08354f6cb5f2d0f63aee609f8c64");

		// Validating response body
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is: " + responseBody);

		Assert.assertEquals(true, responseBody.contains("Delhi"));
	}
}
