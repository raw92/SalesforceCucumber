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
	public Properties prop;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	
	
	public WebDriver initializeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		// timeout implicito
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("Window Maximized");
		log.debug("Now hitting Salesforce server");
		driver.get("https://d5e0000019twseai-dev-ed.my.salesforce.com");
		log.info("Landed on Salesforce home page");
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 20);
		
		return driver;
	}
	
		
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

	
	
	public void navigateServiceTabs() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);

		sp.getInicio();
		log.info("I am at Inicio");

		sp.getChatter();
		log.info("I am at Chatter");

		sp.getCuentas();
		log.info("I am at Cuentas");

		wait.until(ExpectedConditions.urlContains("/Account/list?filterName=Recent"));
		sp.nuevoRegistro();

		wait.until(ExpectedConditions.visibilityOf(sp.cancelarElemento()));
		sp.cancelarElemento().click();

		wait.until(ExpectedConditions.urlContains("/Account/list?filterName=Recent"));
		sp.getContacto();
		log.info("I am at Contacto");

		wait.until(ExpectedConditions.urlContains("/Contact/list?filterName=Recent"));
		sp.nuevoRegistro();

		wait.until(ExpectedConditions.visibilityOf(sp.cancelarElemento()));
		sp.cancelarElemento().click();

		wait.until(ExpectedConditions.urlContains("/Contact/list?filterName=Recent"));
		sp.getCasos();
		log.info("I am at Casos");

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Cambiar propietario']")));
		sp.nuevoRegistro();

		wait.until(ExpectedConditions.visibilityOf(sp.cancelarCasoElemento()));
		sp.cancelarCasoElemento().click();

		wait.until(ExpectedConditions.urlContains("/Case/list?filterName=Recent"));
		sp.getInformes();
		log.info("I am at Informes");

		wait.until(ExpectedConditions.urlContains("/Report/home?queryScope=mru"));
		sp.nuevoInforme();

		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sp.frameInformes()));
		sp.cancelarInforme();
		log.info("Changed from IFrame");
		log.info("Changed back to default content");

		wait.until(ExpectedConditions.urlContains("/Report/home?queryScope=mru"));
		sp.getPaneles();
		log.info("I am at Paneles");

		wait.until(ExpectedConditions.urlContains("/Dashboard/home?queryScope=mru"));
		sp.nuevoPanel();

		// wait.until(ExpectedConditions.visibilityOf(sp.cancelarPanelElemento()));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sp.framePaneles()));
		sp.cancelarPanel();
		log.info("Changed IFrame again");
		log.info("Changed back again to default content");

	}
	
	public void createAccount(int i) throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		if (i < 1) {
			sp.getCuentas();
			// Click crear cuenta
			sp.nuevoRegistro();

		}
		
		
		
		// Escribo nombre en form.
		af.setNombre("nombreRandom", i);
		

		// Mando opt. de dropdown VALORACION
		af.setValoracion();
		

		// Mando opt. de dropdown TIPO
		af.setTipo();
		

		// Mando opt. de dropdown PROPIEDAD
		af.setPropiedad();
		

		// Mando opt. de dropddown Sector
		af.setSector();
		

		// Mando opt de dropdown Customer Priority
		af.setCustomerPrio();
		
		// Mando opt de dropdown SLA
		af.setSLA();
		

		// Mando opt de dropdown Upsell Opportunity
		af.setUpsell();
		

		// Mando opt de dropdown Active
		af.setActive();
		

		// CALENDARIO click input
		af.pickCalendarDate();
		

		if (i < 1) {
			af.getGuardarCuentaNuevo();
			wait.until(ExpectedConditions.urlContains("count=2"));
		} else {
			af.getGuardarCuenta();
			sp.getCuentas();
		}
		
		
	}
	
	public void guardarCuentaCucumber(int i) throws IOException {
		AccountForm af = new AccountForm(driver);
		ServicePage sp = new ServicePage(driver);
		if (i < 1) {
			af.getGuardarCuentaNuevo();
			wait.until(ExpectedConditions.urlContains("count=2"));
		} else {
			af.getGuardarCuenta();
			sp.getCuentas();
		}
	}

	public void ErrorAccountCreation() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);
		// click en cuenta
		sp.getCuentas();
		

		// Click crear cuenta
		sp.nuevoRegistro();
		

		// intento guardar cuenta
		af.getGuardarCuenta();
		

		// valido si esta el error
		Assert.assertTrue(af.getErrorVacio().isDisplayed());
		

		// cancelo la cuenta
		sp.cancelarElemento();
		
	}
	
	public String getLastAccountNameCreated() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuenta
		sp.getCuentas();
		

		// capturo nombre de cuenta
		String name = af.getNombreCuenta().getText();

		return name;
	}

	public void createContactTab(String name) throws InterruptedException, IOException {

		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// Abro contactos en otra pestana
		sp.abrirContactoTab();
		//Thread.sleep(timeout);

		// creo lista de ventanas
		Set<String> windows = sp.listWindows(); // [parentId , childId]

		// Creo objeto iterator para obtener el id del padre e hijo
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();

		// cambio a la pestana nueva/ hija
		sp.switchWindows(childId);

		// Click nuevo contacto
		sp.nuevoRegistro();
		
		// seteo el nombre
		sp.setNombreContacto(name);
		
		// guardo contacto
		af.getGuardarCuenta();
		
		// cambio la ventana a la original
		sp.switchWindows(parentId);
		

	}
	
	public void modifyLastAccount() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuentas
		sp.getCuentas();
		

		// ingreso a modificar cuenta
		af.modificarCuenta();

		// DATOS YA GUARDADOS
		// Valoracion dropdown
		String valueValOld = af.getValoracion().getText();
		

		// dropdown TIPO
		String valueTypeOld = af.getTipo().getText();
		

		// DATOS NUEVOS A GUARDAR/MODIFICAR
		// dropdown VALORACION NUEVO
		af.setValoracionOpcion2();

		String valueValNew = af.getValoracion().getText();
		

		// dropdown TIPO NUEVO
		af.setTipoOpcion2();

		String valueTypeNew = af.getTipo().getTagName();
		

		// Creo objeto del dropdown para hacer scroll hasta el elemento y luego ejecutar
		// acciones
		af.moveBottomAccountForm();
		

		// dropdown Upsell Opportunity viejo
		String valueUpsellOld = af.getUpsell().getText();
		

		// dropdown Upsell Opportunity NUEVO
		af.setUpsellOpcion2();
		

		String valueUpsellNew = af.getUpsell().getText();
		

		// click guardar
		af.getGuardarCuenta();

		// Valido modificaciones
//		
		Assert.assertTrue(validateAccountChanges(valueValOld,valueTypeOld,valueUpsellOld,valueValNew,valueTypeNew,valueUpsellNew));
		

	}
	
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
	
	public void modifyEmployeeInputAccount() throws InterruptedException, IOException {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);

		// click cuentas
		sp.getCuentas();
		

		// accedo a modificar cuenta
		af.modificarCuenta();

		// Mando los numeros al campo Empleados

		af.getNumeroEmpleados().sendKeys("1431655766");
		af.getGuardarCuenta();
		// driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

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
