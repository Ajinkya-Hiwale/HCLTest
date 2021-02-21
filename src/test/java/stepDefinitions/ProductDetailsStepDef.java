package stepDefinitions;

import java.util.LinkedHashMap;

import org.testng.Assert;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import baseClass.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.AutomationPractice;

import pages.ServiceWeatherPage;



public class ProductDetailsStepDef {

	ServiceWeatherPage service = new ServiceWeatherPage();
	Utility utility = new Utility();
	AutomationPractice autopractice = new AutomationPractice();
	
	LinkedHashMap<String, String> weathermap;
	Logger log=Hooks.logger;
	String message;
	

	@Given("Launch {string} automation practice website")
	public void launch_automation_practice_website(String url) {
		log.info("Launching Website...");
		autopractice.getUrl(TestBase.getPropertyValue(url));

	}

	@When("USer Enter the credentials as {string} and {string}")
	public void user_Enter_the_credentials_as_and(String userid, String password) {
		autopractice.loginToWebsite(userid, password);
	}

	@When("Select the category {string},{string} and add to cart and proceed to checkout")
	public void select_the_category_and_add_to_cart_and_proceed_to_checkout(String category, String product) {
	    autopractice.selectProduct(category,product);
	    message=autopractice.getProductAddedMessage();
	    System.out.println("Message is "+message);
	    autopractice.proceedTocheckout();
	}

	
	
	@Then("verify the message {string} and other product details such as product name,color,quantity and totoal price")
	public void verify_the_message_and_other_product_details_such_as_product_name_color_quantity_and_totoal_price(String msg) {
		autopractice.verifyProductDetails();
//		Assert.assertEquals(message, TestBase.getPropertyValue(msg));
	}
}