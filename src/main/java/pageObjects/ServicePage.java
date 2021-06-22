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
	
	

	public WebElement getInicio() {
		
		return inicio;
		
	}

	public WebElement getChatter() {
		
		return chatter;
		
	}

	public WebElement getCuentas() {
		
		return cuentas;
		
	}

	//This is for  - Cuenta / contacto / caso
	public WebElement nuevoRegistro() {
		
		return nuevo;
	}
	
	//This is for - Cuenta / contacto 
	public WebElement cancelarElemento() {
		return cancelar;
	}

	public WebElement getContacto() {
		
		return contacto;
	}

	public WebElement getCasos() {
		
		return casos;
	}
	
	public WebElement cancelarCasoElemento() {
		return cancelarCaso;
	}

	public WebElement getInformes() {
		
		return informes;
	}
	
	public WebElement nuevoInforme() {
		
		return nuevoInforme;
	}

	//I click on "Cancelar informe" and switch to default content
	public void cancelarInforme() {
		
		js.executeScript("arguments[0].click();", cancelarInforme);
		driver.switchTo().defaultContent();
	}
	
	public WebElement frameInformes() {
		return frameInformes;
	}
	
	public WebElement getPaneles() {
		
		return paneles;
	}

	public WebElement nuevoPanel() {
		
		return nuevoPanel;
	}

	//I click on "Cancelar panel" and switch to default content
	public void cancelarPanel() {
		
		js.executeScript("arguments[0].click();", cancelarPanel);
		driver.switchTo().defaultContent();
	}
	
	public WebElement framePaneles() {
		return framePaneles;
	}
		
	//Open contact in a new tab
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
