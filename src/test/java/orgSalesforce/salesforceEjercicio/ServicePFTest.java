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
	
	

	@BeforeMethod // Probar BeforeTest
	public void setup() {
						
		driver = initializeDriver();
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	
	@Test
	public void navegarPorServicios() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		navigateServiceTabs();
	}

	
	@Test
	public void creoDosCuentas() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		for (int i = 0; i < 2; i++) {
			createAccount(i);
		}

	}

	@Test
	public void errorCrearCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		ErrorAccountCreation();
	}

	@Test
	public void crearContacto() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		String n = getLastAccountNameCreated();
		createContactTab(n);

	}

	@Test
	public void modificarCamposCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		modifyLastAccount();
	}

	@Test
	public void modificarEmpleadoDeCuenta() throws InterruptedException, IOException {

		loginSalesforce(driver);
		moveToService(driver);
		modifyEmployeeInputAccount();
	}

	

	
	

	

	

}
