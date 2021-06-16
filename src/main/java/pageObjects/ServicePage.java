package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.dataDriven;

public class ServicePage {

	public WebDriver driver;
	JavascriptExecutor js;

	@FindBy(xpath = "//a[@title = 'Inicio']")
	private WebElement inicio;

	@FindBy(xpath = "//a[@title = 'Chatter']")
	private WebElement chatter;

	@FindBy(xpath = "//a[@title = 'Cuentas']")
	private WebElement cuentas;

	@FindBy(xpath = "//div[@title = 'Nuevo']")
	private WebElement nuevo;

	@FindBy(xpath = "//button[@name = 'CancelEdit']")
	private WebElement cancelar;

	@FindBy(xpath = "//a[@title = 'Contactos']")
	private WebElement contacto;

	@FindBy(xpath = "//a[@title = 'Casos']")
	private WebElement casos;

	@FindBy(css = "button[title='Cancelar']")
	private WebElement cancelarCaso;

	@FindBy(xpath = "//a[@title = 'Informes']")
	private WebElement informes;

	@FindBy(xpath = "//div[@title='Nuevo informe']")
	private WebElement nuevoInforme;

	@FindBy(css = "iframe[title = 'Generador de informes']")
	private WebElement frameInformes;

	@FindBy(xpath = "//button[contains(text(), 'Cancelar')]")
	private WebElement cancelarInforme;

	@FindBy(xpath = "//a[@title = 'Paneles']")
	private WebElement paneles;

	@FindBy(xpath = "//div[@title='Nuevo panel']")
	private WebElement nuevoPanel;

	@FindBy(css = "iframe[title = 'dashboard']")
	private WebElement framePaneles;

	@FindBy(xpath = "//button[@id='cancelBtn']")
	private WebElement cancelarPanel;

	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement nombreContacto;

	// constructor de la clase
	public ServicePage(WebDriver driver) throws IOException {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;

	}

	public void getInicio() {
		js.executeScript("arguments[0].click();", inicio);
		
	}

	public void getChatter() {
		js.executeScript("arguments[0].click();", chatter);
		
	}

	public void getCuentas() {
		js.executeScript("arguments[0].click();", cuentas);
		
	}

	//Cuenta / contacto / caso
	public void nuevoRegistro() {
		js.executeScript("arguments[0].click();", nuevo);
	}
	
	//Cuenta / contacto 
	public WebElement cancelarElemento() {
		return cancelar;
	}

	public void getContacto() {
		js.executeScript("arguments[0].click();", contacto);
	}

	public void getCasos() {
		js.executeScript("arguments[0].click();", casos);
	}
	
	public WebElement cancelarCasoElemento() {
		return cancelarCaso;
	}

	public void getInformes() {
		js.executeScript("arguments[0].click();", informes);
	}
	
	public void nuevoInforme() {
		js.executeScript("arguments[0].click();", nuevoInforme);
	}

	public void cancelarInforme() {
		//driver.switchTo().frame(frameInformes);
		js.executeScript("arguments[0].click();", cancelarInforme);
		driver.switchTo().defaultContent();
	}
	
	public WebElement frameInformes() {
		
		return frameInformes;
		
	}
	
	public void getPaneles() {
		js.executeScript("arguments[0].click();", paneles);
	}

	public void nuevoPanel() {
		js.executeScript("arguments[0].click();", nuevoPanel);
	}

	public void cancelarPanel() {
		//driver.switchTo().frame(framePaneles);
		js.executeScript("arguments[0].click();", cancelarPanel);
		driver.switchTo().defaultContent();
	}
	
	public WebElement framePaneles() {
		
		return framePaneles;
		
	}
		
	public void abrirContactoTab() {
		contacto.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
	}
	
	public Set<String> listWindows() {
		return driver.getWindowHandles();
	}
	
	public void switchWindows(String w) {
		driver.switchTo().window(w);
	}
	
	public void setNombreContacto(String nombre) {
		nombreContacto.sendKeys(nombre);
	}

}
