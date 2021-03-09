package dataDrivenTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenTest_AddNewEmployee {

	@Test(dataProvider = "employeeDataProvider")
	public void addNewEmployee(String eName, String eSalary, String eAge) {
		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// Create Request object
		RequestSpecification httpRequest = RestAssured.given().log().all();

		// Creating JSON Object to send data in POST request
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", eName);
		jsonObj.put("salary", eSalary);
		jsonObj.put("age", eAge);

		// Adding a header stating request body is in JSON
		httpRequest.header("Content-Type", "application/json");

		// Adding JSON to body of the request
		httpRequest.body(jsonObj.toJSONString());

		// Sending post request and getting response
		Response response = httpRequest.request(Method.POST, "/create");

		// Getting response body from response object
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is: " + responseBody);

		System.out.println(responseBody);
		// Validations on response body fields
		JsonPath jsonPath = response.jsonPath();

		String employeeName = jsonPath.getString("data.name");
		Assert.assertEquals("Name is not correct", eName, employeeName);

		String employeeSalary = jsonPath.getString("data.salary");
		Assert.assertEquals("Salary is not correct", eSalary, employeeSalary);

		String employeeAge = jsonPath.getString("data.age");
		Assert.assertEquals("Age is not correct", eAge, employeeAge);

		// Validating status code
		Assert.assertEquals("Status is incorrect", "200", response.getStatusCode());
	}

	@DataProvider(name = "employeeDataProvider")
	public String[][] getEmpData() {

		//Read data from Excel
		String filePath = System.getProperty("user.dir") + "/src/test/java/dataDrivenTesting/APITestData.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "EmployeeData");
		int colCount = ExcelUtils.getCellCount(filePath, "EmployeeData", 1);

		String empData[][] = new String[rowCount][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				empData[i - 1][j] = ExcelUtils.getCellData(filePath, "EmployeeData", i, j);
			}
		}

		return (empData);

	}
}
