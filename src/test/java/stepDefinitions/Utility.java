package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.Gson;

public class Utility {

	public String readFile(String filepath) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");
	//	System.out.println("Body is "+str);
		return str;
	}
	
	public <T> T unmarshallingResponse(String response, Class name)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper jsonmapper = new ObjectMapper();

		jsonmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonmapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

		T pojo = (T) jsonmapper.readValue(response, name);

		return pojo;

	}

	public <T> T gsonParser(String response, Class name) throws JsonMappingException, JsonProcessingException {
		StringReader reader = new StringReader(response);
		Gson gson = new Gson();
		T res = (T) gson.fromJson(response, name);

		return res;

	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	
	public void javaScriptExecutorClick(WebDriver driver, WebElement ele) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

}
