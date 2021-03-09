import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRequest_Demo2 {

	@Test
	public void googleMapTest() {

		// Specify the Base URI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Request object creation
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Response object creation
		Response response = httpRequest.request(Method.GET,
				"maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AlzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is" + responseBody);

		// Capture Headers details from response
		String contentType = response.header("Content-Type");
		System.out.println("Content Type is: " + contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UTF-8");

		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding is: " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

}
