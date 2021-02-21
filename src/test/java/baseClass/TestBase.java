package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static String propertyfile = "TestData.properties";
	public File src;
	public FileInputStream fis;
	public static Properties prop;
	public static Logger logger;
	
	
	/*
	 * Setting up propery files and Logger
	 * 
	 */

	public TestBase() throws IOException {
		src = new File(propertyfile);
		fis = new FileInputStream(src);
		prop = new Properties();

		prop.load(fis);

		logger = Logger.getLogger("logg");
		PropertyConfigurator.configure("Log4j.properties");

	}

	/*
	 * Setting up webdriver
	 * Impicit wait and pageload time out setup
	 */

	public static void getDriver() {

		
		if (prop.getProperty("browser").equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver",
				//	System.getProperty("user.dir") + "\\src\\main\\java\\driver\\chromedriver.exe");

			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\driver\\geckodriver.exe");
			
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")), TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwaittimeout")), TimeUnit.SECONDS);

	
}
	/*
	 * Method to get values from the property file
	 */

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

}