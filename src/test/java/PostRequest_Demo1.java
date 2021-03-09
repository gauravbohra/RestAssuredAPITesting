import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostRequest_Demo1 {

	@Test
	public void registrationSuccess() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Request Payload along with post request
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("FirstName", "JohnCena");
		jsonBody.put("LastName", "CenaJohn");
		jsonBody.put("UserName", "JohnCena");
		jsonBody.put("Password", "JohnCena@WWE");
		jsonBody.put("Email", "johncena@gmail.com");

		httpRequest.header("Content-Type", "application/json");
		
		//Attach json body to the request
		httpRequest.body(jsonBody.toJSONString());

		// Response Object
		Response response = httpRequest.request(Method.POST, "/register");

		// Print response Body
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);

		//Status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status code is: " + responseBody);
		Assert.assertEquals(statusCode, 201);
		
		String successCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}
}
