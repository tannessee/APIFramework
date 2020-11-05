package stepDefinitions;

import pojo.Location;
import pojo.Place_Google_map;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class placeValidationSteps extends Utils { // Utils - parent class

	ResponseSpecification respSpec;
	RequestSpecification resGiven;
	Response response;
	static String place_id; // static чтобы переменная не обнулялась из фукнции в функцию
	

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String lang, String address) throws IOException {

		resGiven = given().spec(requestSpecification()).body(data.addPlacePayload(name, lang, address)); // requestSpecification() method
																						// from Utils

	}

	@When("User calls {string} with {string} http request")
	public void user_calls_something_with_some_http_request(String resource, String methodName) throws Throwable {
		
		//constructor APIresources will be called with the value of resource that we pass
		APIresources resourceApi = APIresources.valueOf(resource); // создаем объкет класса enum 
		System.out.println(resourceApi.getResource());
		
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (methodName.equalsIgnoreCase("POST")) 
			response = resGiven.when().post(resourceApi.getResource());
		else if (methodName.equalsIgnoreCase("GET")) 
			response = resGiven.when().get(resourceApi.getResource());
		
	}

	@Then("Api call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@And("{string} in response body is {string}")
	public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
		
		assertEquals(getJsonPath(response, keyValue),expectedValue);
	}
	
	@And("Verify place_id created maps to  {string} using {string}")
		public void verify_place_id_created_maps_to_using(String expectedPlaceName, String resource) throws Throwable {
		// preperare request Spec, get place id
		 place_id = getJsonPath(response,"place_id");
		resGiven = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		// hit getApi
		user_calls_something_with_some_http_request(resource, "GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedPlaceName);

	}

		@Given("DeletePlace Payload")
		public void delete_place_payload() throws IOException {
			resGiven = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

			
		}







}
