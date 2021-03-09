import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRequest_Demo1 {

	@Test
	public void getWeatherDetails() {

		// Specify the Base URI
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";

		// Request object creation
		RequestSpecification httpRequest = RestAssured.given().log().all();
		httpRequest.queryParam("q", "London");
		httpRequest.queryParam("appid","6c6d08354f6cb5f2d0f63aee609f8c64");
		// Response object creation
		
		Response response = httpRequest.request(Method.GET);

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);

		// Response code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(200, statusCode);

		// Status line validation
		String statusLine = response.getStatusLine();
		System.out.println("Status line is: " + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);

	}

}
