import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest_Demo6 {

	@Test
	public void testAutorization() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/autentication/CheckForAuthentication";

		// Base authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");

		RestAssured.authentication = authScheme;	

		// Request Object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/");

		// Print response body
		String responseBody = response.body().asPrettyString();
		System.out.println("Response body: " + responseBody);

	}
}
