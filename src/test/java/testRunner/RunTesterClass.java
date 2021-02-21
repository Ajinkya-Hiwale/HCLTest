package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/java/features", 
		glue = "stepDefinitions",
		monochrome = true,
		tags="@StationValidation",
		plugin = { "pretty", "html:target/cucumber" }

)
public class RunTesterClass extends AbstractTestNGCucumberTests {

}