package pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseClass.TestBase;

import stepDefinitions.Utility;
import stepDefinitions.Hooks;

public class AutomationPractice extends Utility {

	WebDriver driver;
	LinkedHashMap<String, String> data = new LinkedHashMap();
	Logger log=Hooks.logger;

	public AutomationPractice() {
		this.driver = TestBase.driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(id = "email")
	WebElement emailid;

	@FindBy(id = "passwd")
	WebElement password;
	
	
	@FindBy(id="SubmitLogin")
	WebElement signinbutton;
	
	@FindBy(xpath="//*[@id=\"block_top_menu\"]/ul/li[3]/a")
	WebElement category;
	
	
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signin;
	
	@FindBy(xpath="//h5[@itemprop='name']/a")
	WebElement product;
	
	@FindBy(xpath="//*[@id=\"add_to_cart\"]/button/span")
	WebElement addtocart;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")
	WebElement proceedtocheckout;
	
	@FindBy(xpath="//i[@class='icon-ok']//parent::h2/text()")
	WebElement productmsg;

	
	@FindBy(xpath="//td[@class='cart_description']//p[@class='product-name']")
	WebElement productname;
	
	@FindBy(xpath="//td[@class='cart_description']//small/a")
	WebElement color;
	
	@FindBy(xpath="//td[@class='cart_total']/span")
	WebElement price;
	
	@FindBy(xpath="//td[@class='cart_quantity text-center']/input[@class='cart_quantity_input form-control grey']")
	WebElement quantity;
	
	public void verifyProductDetails()
	{
		Assert.assertEquals(productname.getText().trim(), "Faded Short Sleeve T-shirts");
		Assert.assertEquals(color.getText().trim(), "Color : Orange, Size : S");
		Assert.assertEquals(price.getText().trim(), "$16.51");
		Assert.assertEquals(quantity.getAttribute("value"), "1");
	}
	
	public void getUrl(String url) {
		driver.get(url);

	}
	
	public void loginToWebsite(String userid,String pass) {
		signin.click();
		emailid.sendKeys(userid);
		password.sendKeys(pass);
		signinbutton.click();

	}
	
	public void selectProduct(String catego,String prod)
	{
		category.click();
		product.click();
		addtocart.click();
		
	}
	public String getProductAddedMessage()
	{
		String message= driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2")).getText().trim();
	
		return message;
	}
	public void proceedTocheckout()
	{
		proceedtocheckout.click();
	}
	
	/*
	*  Handling popup which as soon as you open NDTV weather website
	*  Once handle , entering city name
	*/

//	public void handlePopupAndEnterCity(String cityname) throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,60);
//		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='No Thanks']")));
//		Actions builder = new Actions(driver);
//		 builder.click(element).build().perform();
//		 citysearch.sendKeys(cityname);
//		
//	}
	
	/*
	*  Fetching Weather data from UI and storing it in one HashMap.
	*  As per our requirement we can fetch required value from the HashMap
	*/

	public LinkedHashMap<String, String> getWeatherData(String city) {

		
		WebElement weatherdata = driver.findElement(
				By.xpath("//*[@id='cities_list']//li//table//tbody//tr/td//b[text()='" + city + "']/parent::td"));
		
		
		log.info("Temperature: " + weatherdata.getText().split("[(]")[1].split("/")[0].trim());

		String weather = weatherdata.getText();
		log.info("City: "+weatherdata.getText().split(" ")[0].trim());
		data.put("City", weatherdata.getText().split(" ")[0].trim());
		
		data.put("Temperature", weatherdata.getText().split("[(]")[1].split("/")[0].trim());
		WebElement weathercondition = driver
				.findElement(By.xpath("//b[text()='" + city + "']/../preceding-sibling::td/div[@class='condition']"));
		log.info("Weather Condition is " + weathercondition.getText());
		data.put("WeatherCondition", weathercondition.getText().trim());
		log.info("Humidity: "+weather.substring(weather.indexOf("Humidity") + 10, weather.indexOf("%")));
		data.put("Humidity", weather.substring(weather.indexOf("Humidity") + 10, weather.indexOf("%")));
		log.info("Wind: "+weather.substring(weather.indexOf("Wind") + 5, weather.indexOf("KPH")));
		data.put("Wind", weather.substring(weather.indexOf("Wind") + 5, weather.indexOf("KPH")));
		return data;

	}
	
	/*
	* Verify each data whether it is blank 
	*/

	public void verifyWeatherData(LinkedHashMap<String, String> map) {
		for (Entry<String, String> map1 : map.entrySet()) {
			if (map1.getValue() == "") {
				Assert.assertTrue(false);
			}
		}
	}

//	public void getNoRecordMessage(String errormsg) {
//		log.info(norecordmsg.getText());
//		Assert.assertEquals(norecordmsg.getText(), errormsg);
//	}

}
