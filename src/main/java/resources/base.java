package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class base {
	
	public  WebDriver driver;
	public Properties prop;

	
	public void loginSalesforce(WebDriver driver) throws IOException {
		//pulleo info del excel
		LoginPage lp = new LoginPage(driver);
		lp.getUsername();
		lp.getPassword();
		lp.getLogin();
	}
	
	public void moveToService(WebDriver driver) throws InterruptedException, IOException {
		
		LandingPage lp = new LandingPage(driver);
		lp.getServicios();

	}
	
	
	
}
