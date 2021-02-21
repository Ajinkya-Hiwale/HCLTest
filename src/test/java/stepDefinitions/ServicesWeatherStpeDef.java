package stepDefinitions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import baseClass.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pages.ServiceWeatherPage;

public class ServicesWeatherStpeDef extends TestBase {

	ServiceWeatherPage serivcepage = new ServiceWeatherPage();
	Logger log=Hooks.logger;

	public ServicesWeatherStpeDef() throws IOException {
		super();
		
	}

	Response response;
	String baseURI;
	String endpoint;
	String apikey;
	String city;
	JsonPath jsonpath;

	@Given("Setting up Rest APi ELements like {string},{string},{string},{string}")
	public void setting_up_Rest_APi_ELements_like(String baseuri, String endPoint, String apikeyy, String cityy) {
		
		log.info("Setting up data to hit weather web service");
		baseURI = TestBase.getPropertyValue(baseuri);
		endpoint = TestBase.getPropertyValue(endPoint);
		apikey = TestBase.getPropertyValue(apikeyy);
		city = TestBase.getPropertyValue(cityy);
	}

	
	@When("Calling  Weather API to register to the station")
	public void calling_Weather_API_to_register_to_the_station() throws IOException {
		   
		log.info("Calling web service to register the station");
		response = serivcepage.postRegisterStation(baseURI, endpoint, apikey);
		System.out.println();
	}

	
	@Then("Verify the error response")
	public void verify_the_error_response() {
	    Assert.assertEquals(response.getStatusCode(), 401);
	    jsonpath=new JsonPath(response.getBody().asString());
	    String message=jsonpath.get("message");
	    Assert.assertTrue(message.contains(TestBase.getPropertyValue("errormessage")));
	}

	@When("Calling NDTV Weather API to get weather details")
	public void calling_NDTV_Weather_API_to_get_weather_details() throws JsonMappingException, JsonProcessingException {
		log.info("Calling NDTV web service to get weather data");
		response = serivcepage.getWeatherDataResponse(baseURI, endpoint, apikey, city);
	}

	@Then("Verify if weather data is getting fetched from the API")
	public void verify_if_weather_data_is_getting_fetched_from_the_API()
			throws JsonMappingException, JsonProcessingException {

		System.out.println("Response is " + response.getBody().asString());
		if (response.getBody().asString().equals(" ")) {
			log.info("No Data in Response");
			Assert.assertTrue(false);
			
		}
	}

	@Then("Verify status code should be {string}")
	public void verify_status_code_should_be(String statuscode) {

		Assert.assertEquals(String.valueOf(response.getStatusCode()), statuscode);
		
	}
	
	@Then("Verify content type of the response {string}")
	public void verify_content_type_of_the_response(String content_type) {
		log.info(response.getContentType());
		Assert.assertTrue(response.getContentType().contains(TestBase.getPropertyValue(content_type)));
	}

}
