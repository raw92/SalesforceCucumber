package orgSalesforce.salesforceEjercicio;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AccountForm;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.ServicePage;
import resources.base;
import resources.dataDriven;

import org.apache.logging.log4j.*;

public class ServicePFTest extends base {

	public WebDriver driver;
	
	
	//This will trigger everytime before a testcase will start initializing the driver and opening the browser
	@BeforeMethod
	public void setup() {
						
		driver = initializeDriver("https://login.salesforce.com");
	}

	//This will close my browser after test finish
	@AfterMethod
	public void close() {
		driver.quit();
	}

	//Test - Logs in and move to services then navigates to all services tabs
	@Test
	public void navegarPorServicios() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		navigateServiceTabs();
	}

	//Test - logs in and move to services then create 2 accounts one after the other filling the required fields and some dropdowns
	@Test
	public void creoDosCuentas() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		for (int i = 0; i < 2; i++) {
			createAccount(i);
		}

	}

	//Test - logs in and move to services then try to create an account in blank should trigger an error
	@Test
	public void errorCrearCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		ErrorAccountCreation();
	}

	//Test - logs in and move to services then try to create a contact using the last account created name
	@Test
	public void crearContacto() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		String n = getLastAccountNameCreated();
		createContactTab(n);

	}

	//Test - logs in and move to services then goes to account select the last account created and try to modify it
	@Test
	public void modificarCamposCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		modifyLastAccount();
	}

	//Test - logs in and move to services then goes to account select the last account created and try to modify the Employee input
	//with a given number and try to save it, should throw an error and validates if it is the required error.
	@Test
	public void modificarEmpleadoDeCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		modifyEmployeeInputAccount();
	}

	

	
	

	

	

}
