package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.AccountForm;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.ServicePage;

public class base {
	
	private static Logger log = LogManager.getLogger(base.class.getName());
	public  WebDriver driver;
	WebDriverWait wait;
	static JavascriptExecutor js;
	
	
	//Method to initialize Driver / js Exe / Implicit and Explicit Wait / windows management etc
	public WebDriver initializeDriver(String url) {
		//Generic dir to be able to open it from another machine
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("Window Maximized");
		log.debug("Now hitting Salesforce server");
		//driver.get("https://d5e0000019twseai-dev-ed.my.salesforce.com");
		driver.get(url);
		log.info("Landed on Salesforce home page");
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 20);
		
		return driver;
	}
	
	//Method to login in calls for the dataDriven class when i search for the data inside the excel	
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

	//JS Generic Click
	public static void jsClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
			
	
	public void navigateServiceTabs() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);

		jsClick(sp.getInicio());
		log.info("I am at Inicio");

		jsClick(sp.getChatter());
		log.info("I am at Chatter");

		jsClick(sp.getCuentas());
		log.info("I am at Cuentas");

		//It will wait to the url contains that and then will click at new from Account 
		wait.until(ExpectedConditions.urlContains("/Account/list?filterName=Recent"));
		jsClick(sp.nuevoRegistro());

		//It will wait to the element to be visible and then will click at cancel from Account form
		wait.until(ExpectedConditions.visibilityOf(sp.cancelarElemento()));
		sp.cancelarElemento().click();

		//It will wait to the url contains that and then will click at Contact
		wait.until(ExpectedConditions.urlContains("/Account/list?filterName=Recent"));
		jsClick(sp.getContacto());
		log.info("I am at Contacto");

		//It will wait to the url contains that and then will click at new from Contact
		wait.until(ExpectedConditions.urlContains("/Contact/list?filterName=Recent"));
		jsClick(sp.nuevoRegistro());

		//It will wait to the element to be visible and then will click at cancel from Contact form
		wait.until(ExpectedConditions.visibilityOf(sp.cancelarElemento()));
		sp.cancelarElemento().click();

		//It will wait to the url contains that and then will click at Casos
		wait.until(ExpectedConditions.urlContains("/Contact/list?filterName=Recent"));
		jsClick(sp.getCasos());
		log.info("I am at Casos");

		//It will wait to the element to be visible and then will click at new from Cases
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Cambiar propietario']")));
		jsClick(sp.nuevoRegistro());

		//It will wait to the element to be visible and then will click at cancel from Cases form
		wait.until(ExpectedConditions.visibilityOf(sp.cancelarCasoElemento()));
		sp.cancelarCasoElemento().click();

		//It will wait to the url contains that and then will click at Informes
		wait.until(ExpectedConditions.urlContains("/Case/list?filterName=Recent"));
		jsClick(sp.getInformes());
		log.info("I am at Informes");

		//It will wait to the url contains that and then will click at new Informes
		wait.until(ExpectedConditions.urlContains("/Report/home?queryScope=mru"));
		jsClick(sp.nuevoInforme());

		//It will wait to the iframe to change to informes frame then will click at cancel informe and change back to the default content
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sp.frameInformes()));
		sp.cancelarInforme();
		log.info("Changed from IFrame");
		log.info("Changed back to default content");

		//It will wait to the url contains that and then will click at Paneles
		wait.until(ExpectedConditions.urlContains("/Report/home?queryScope=mru"));
		jsClick(sp.getPaneles());
		log.info("I am at Paneles");

		//It will wait to the url contains that and then will click at new Panel
		wait.until(ExpectedConditions.urlContains("/Dashboard/home?queryScope=mru"));
		jsClick(sp.nuevoPanel());

		//It will wait to the iframe to change to paneles frame then will click at cancel panel and change back to the default content
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sp.framePaneles()));
		sp.cancelarPanel();
		log.info("Changed IFrame again");
		log.info("Changed back again to default content");

	}
	
	public void createAccount(int i) throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		//if is the first account of the run it will enter and click at cuentas and then in new Account
		if (i < 1) {
			jsClick(sp.getCuentas());
			// Click crear cuenta
			jsClick(sp.nuevoRegistro());

		}
				
		// Escribo nombre en form.
		af.setNombre("nombreRandom", i);
		
		//Setting of all dropdowns using a default method which i give 2 parameters 1 is the dropdown itself and the 2nd one is the option as a string
		// Mando opt. de dropdown VALORACION
		af.setDropdown(af.getValoracion(), "1");
		
		// Mando opt. de dropdown TIPO
		af.setDropdown(af.getTipo(), "1");
		
		// Mando opt. de dropdown PROPIEDAD
		af.setDropdown(af.getPropiedad(), "");
		
		// Mando opt. de dropddown Sector
		af.setDropdown(af.getSector(), "");
	
		// Mando opt de dropdown Customer Priority
		af.moveBottomAccountForm();
		af.setDropdown(af.getCustomerPrio(), "");
		
		// Mando opt de dropdown SLA
		af.setDropdown(af.getSLA(), "");
	
		// Mando opt de dropdown Upsell Opportunity
		af.setDropdown(af.getUpsell(), "");
	
		// Mando opt de dropdown Active
		af.setDropdown(af.getActive(), "");
	
		// CALENDARIO click input
		af.pickCalendarDate();
		
		//Method to save the accounts i send the argument i to know if its the first or the second account i will create and save
		guardarCuenta(i);
	
		
	}
	
	//Method to save accounts 
	public void guardarCuenta(int i) throws IOException {
		AccountForm af = new AccountForm(driver);
		ServicePage sp = new ServicePage(driver);
		//If its the first account created in the run it will save it clicking at save and new and open a second account form to be filled / if not it will save it and go to accounts
		if (i < 1) {
			af.getGuardarCuentaNuevo();
			//I wait for the url to contains that after the first account is created, otherwise test will crash when try to start filling the fields from the 2nd account
			wait.until(ExpectedConditions.urlContains("count=2"));
		} else {
			af.getGuardarCuenta().click();
			jsClick(sp.getCuentas());
		}
	}

	public void ErrorAccountCreation() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);
		// click en cuenta
		jsClick(sp.getCuentas());
		
		// Click crear cuenta
		jsClick(sp.nuevoRegistro());
		
		// intento guardar cuenta
		af.getGuardarCuenta().click();
		
		// valido si esta el error
		Assert.assertTrue(af.getErrorVacio().isDisplayed());
		
		// cancelo la cuenta
		sp.cancelarElemento().click();
		
	}
	
	public String getLastAccountNameCreated() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuenta
		jsClick(sp.getCuentas());
		

		// capturo nombre de cuenta
		String name = af.getNombreCuenta().getText();

		return name;
	}

	public void createContactTab(String name) throws InterruptedException, IOException {

		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// Abro contactos en otra pestana
		sp.abrirContactoTab();
		
		// creo lista de ventanas
		Set<String> windows = sp.listWindows(); // [parentId , childId]

		// Creo objeto iterator para obtener el id del padre e hijo
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();

		// cambio a la pestana nueva/ hija
		sp.switchWindows(childId);

		// Click nuevo contacto
		jsClick(sp.nuevoRegistro());
		
		// seteo el nombre
		sp.setNombreContacto(name);
		
		// guardo contacto
		af.getGuardarCuenta().click();
		
		// cambio la ventana a la original
		sp.switchWindows(parentId);
		

	}
	
	public void modifyLastAccount() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuentas
		jsClick(sp.getCuentas());
		

		// ingreso a modificar cuenta
		af.modificarCuenta();

		// Capturo los DATOS YA GUARDADOS
		// Valoracion dropdown
		String valueValOld = af.getValoracion().getText();
		

		// dropdown TIPO
		String valueTypeOld = af.getTipo().getText();
		

		// Seteo y Capturo los DATOS NUEVOS A GUARDAR/MODIFICAR
		// dropdown VALORACION NUEVO
		af.setDropdown(af.getValoracion(), "2");

		String valueValNew = af.getValoracion().getText();
		

		// dropdown TIPO NUEVO
		af.setDropdown(af.getTipo(), "2");

		String valueTypeNew = af.getTipo().getTagName();
		

		// Creo objeto del dropdown para hacer scroll hasta el elemento y luego ejecutar
		// acciones
		af.moveBottomAccountForm();
		
		// dropdown Upsell Opportunity viejo
		String valueUpsellOld = af.getUpsell().getText();
		

		// dropdown Upsell Opportunity NUEVO
		af.setDropdown(af.getUpsell(), "2");
		

		String valueUpsellNew = af.getUpsell().getText();
		

		// click guardar
		af.getGuardarCuenta().click();

		// Valido modificaciones
		Assert.assertTrue(validateAccountChanges(valueValOld,valueTypeOld,valueUpsellOld,valueValNew,valueTypeNew,valueUpsellNew));
		

	}
	
	//Method to validate if some of the values from the account has been changed or not i send all the old values and compares it to the new one if at least 1 is different it will be true
	public boolean validateAccountChanges(String vOld, String tOld, String uOld, String vNew, String tNew, String uNew) {
		boolean valid= false;
		
		
		if (vOld != vNew || tOld != tNew || uOld != uNew) {
			System.out.println("Los datos fueron modificados con exito!");
			valid = true;
		} else {
			System.out.println("Los datos no fueron modificados");
		}
		
		
		return valid;
	}
	
	
	//I change the input "Employee" from the "Create new Account form"
	public void modifyEmployeeInputAccount() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuentas
		jsClick(sp.getCuentas());
		

		// accedo a modificar cuenta
		af.modificarCuenta();

		// Mando los numeros al campo Empleados
		af.getNumeroEmpleados().sendKeys("1431655766");
		af.getGuardarCuenta().click();
		

		// Capturo el mensaje del error para comparar
		String mensajeError = af.getErrorEmpleados().getText();
		boolean v = af.validacionErrorEmpleados(mensajeError);

		if (v) {
			System.out.println("El mensaje de error coincide");
			Assert.assertTrue(v);
		} else {
			System.out.println("Los mensajes de error no coinciden");
			Assert.assertTrue(v);
		}

	}
	
}
