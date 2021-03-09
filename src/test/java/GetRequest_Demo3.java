import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRequest_Demo3 {

	@Test
	public void getWeatherDetails() {

		// Specify the Base URI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Create request Object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Create response object
		Response response = httpRequest.request(Method.GET,
				"maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AlzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);

		// Printing all the headers
		Headers allHeaders= response.headers();
		for(Header header:allHeaders) {
			System.out.println(header.getName()+"="+header.getValue());
		}
		
	

	}

}
