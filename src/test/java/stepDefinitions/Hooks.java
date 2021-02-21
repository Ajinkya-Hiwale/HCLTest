package stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
/*  @Webdriver used to perform operation on browser
 * 
 *  @setup method will get executed before each test case 
 *  it will set the driver,maximize the windows browser and set implicit wait
 * 
 * @After execute after end of of each test case
 * 
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import baseClass.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends TestBase {

	
	Logger log=Hooks.logger;
	public Hooks() throws IOException {
		super();

	}

	Scenario scenario;
	public TestBase testbase;

	@Before("@verifyproductdetails")
	public void setUP(Scenario scenario) throws Throwable {
		log.info("Launching Browser..");
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Starting Test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		getDriver();

		this.scenario = scenario;
		log.info("Scenario name is " + scenario.getName());

	}

	@After("@verifyproductdetails")
	public void tearDown() throws InterruptedException {
		if (scenario.isFailed()) {
			final byte[] screens = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			 //scenario.embed(screens,"image/png");
		}
		Thread.sleep(1000);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Ending Test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		log.info("Closing Browser..");
		driver.quit();
	}

	/*@Before("@UITest")
	public void setUP2(Scenario scenario) throws Throwable {
		log.info("Launching Browser..");
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Starting Test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		getDriver();

		this.scenario = scenario;
		log.info("Scenario name is " + scenario.getName());

	}

	@After("@UITest")
	public void tearDown2() {
		if (scenario.isFailed()) {
			final byte[] screens = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			 //scenario.embed(screens,"image/png");
		}
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Ending Test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		log.info("Closing Browser..");
		driver.quit();
	}*/

}
