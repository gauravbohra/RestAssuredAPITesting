import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRequest_Demo5 {

	@Test
	public void getWeatherDetails() {
		// Specify base URI
		RestAssured.baseURI = "http://api.openweathermap.org";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		httpRequest.queryParam("q", "Mumbai");
		httpRequest.queryParam("appid", "6c6d08354f6cb5f2d0f63aee609f8c64");

		// Response Object
		Response response = httpRequest.request(Method.GET, "/data/2.5/weather");

		// Response Body
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is: " + responseBody);

		// Validating all field in request body
		JsonPath jsonPath = response.jsonPath();

		System.out.println("Coord: " + jsonPath.get("coord"));
		System.out.println("Weather: " + jsonPath.get("weather"));
		System.out.println("Base: " + jsonPath.get("base"));
		System.out.println("main: " + jsonPath.get("main"));
		System.out.println("Visibility: " + jsonPath.get("visibility"));
		System.out.println("Wind: " + jsonPath.get("wind"));
		System.out.println("Clouds: " + jsonPath.get("clouds"));
		System.out.println("dt: " + jsonPath.get("dt"));
		System.out.println("sys: " + jsonPath.get("sys"));
		System.out.println("timezone: " + jsonPath.get("timezone"));
		System.out.println("id: " + jsonPath.get("id"));
		System.out.println("name: " + jsonPath.get("name"));
		System.out.println("cod: " + jsonPath.get("cod"));

		Assert.assertEquals(jsonPath.get("name"), "Mumbai");
		Assert.assertEquals(jsonPath.get("sys.country"), "IN");
	}
}
