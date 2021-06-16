package pageObjects;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.dataDriven;

public class AccountForm {

	public WebDriver driver;
	JavascriptExecutor js;

	@FindBy(xpath = "//input[@name='Name']")
	private WebElement inputNombre;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[2]")
	private WebElement dropdownValoracion;

	@FindBy(xpath = "//div/lightning-base-combobox-item[2]")
	private WebElement opcionValoracion;
	
	@FindBy(xpath = "//div/lightning-base-combobox-item[3]")
	private WebElement opcionValoracion2;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[4]")
	private WebElement dropdownTipo;

	@FindBy(xpath = "//div/lightning-base-combobox-item[6]")
	private WebElement opcionTipo;
					 
	@FindBy(xpath = "//div/lightning-base-combobox-item[5]")
	private WebElement opcionTipo2;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[5]")
	private WebElement dropdownPropiedad;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[3])[3]")
	private WebElement opcionPropiedad;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[6]")
	private WebElement dropdownSector;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[4])[4]")
	private WebElement opcionSector;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[7]")
	private WebElement customerPrioDropdown;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[2])[5]")
	private WebElement opcionCustomerPrio;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[8]")
	private WebElement dropdownSLA;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[3])[6]")
	private WebElement opcionSLA;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[9]")
	private WebElement dropdownUpsell;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[2])[7]")
	private WebElement opcionUpsell;
	
	@FindBy(xpath = "(//div/lightning-base-combobox-item[2])[3]")
	private WebElement opcionUpsell2;

	@FindBy(xpath = "(//lightning-base-combobox/div/div[1]/input)[10]")
	private WebElement dropdownActive;

	@FindBy(xpath = "(//div/lightning-base-combobox-item[2])[8]")
	private WebElement opcionActive;

	@FindBy(xpath = "//input[@name='SLAExpirationDate__c']")
	private WebElement calendario;

	@FindBy(xpath = "(//td/span[contains(text(), '1')])[2]")
	private WebElement opcionCalendario;

	@FindBy(xpath = "//button[@name='SaveAndNew']")
	private WebElement guardarNuevo;
					 
	@FindBy(xpath = "//button[@name='SaveEdit']")
	private WebElement guardar;
	
	@FindBy(xpath = "//lightning-icon[@title='Error']")
	private WebElement errorVacio;
	
	@FindBy(xpath = "//div[@class='slds-form-element__help']")
	private WebElement errorEmpleados;
	
	@FindBy(xpath = "(//tr/th/span)[1]")
	private WebElement nombreCuenta;
	//
	@FindBy(xpath = "//span/div/a/span/span[1]")
	private WebElement opcionesCuenta;
	
	@FindBy(xpath = "//a[@title='Modificar']")
	private WebElement modificarCuenta;
	
	@FindBy(xpath = "//input[@name='NumberOfEmployees']")
	private WebElement inputNumeroEmpleados;

	//Constructor de clase
	public AccountForm(WebDriver driver) throws IOException {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;

	}
	
	public void setNombre(String s, int i) {
		inputNombre.sendKeys(s + i);
	}
	
	public void setValoracion() {
		js.executeScript("arguments[0].click();", dropdownValoracion);
		js.executeScript("arguments[0].click();", opcionValoracion);
	}
	
	public void setValoracionOpcion2() {
		js.executeScript("arguments[0].click();", dropdownValoracion);
		js.executeScript("arguments[0].click();", opcionValoracion2);
	}

	public void setTipo() {
		js.executeScript("arguments[0].click();", dropdownTipo);
		js.executeScript("arguments[0].click();", opcionTipo);
	}
	
	public void setTipoOpcion2() {
		js.executeScript("arguments[0].click();", dropdownTipo);
		js.executeScript("arguments[0].click();", opcionTipo2);
	}

	public void setPropiedad() {
		js.executeScript("arguments[0].click();", dropdownPropiedad);
		js.executeScript("arguments[0].click();", opcionPropiedad);
	}

	public void setSector() {
		js.executeScript("arguments[0].click();", dropdownSector);
		js.executeScript("arguments[0].click();", opcionSector);
	}

	public void setCustomerPrio() {
		js.executeScript("arguments[0].scrollIntoView();", customerPrioDropdown);
		js.executeScript("arguments[0].click();", customerPrioDropdown);
		js.executeScript("arguments[0].click();", opcionCustomerPrio);

	}
	
	public void moveBottomAccountForm() {
		js.executeScript("arguments[0].scrollIntoView();", customerPrioDropdown);
	}

	public void setSLA() {
		js.executeScript("arguments[0].click();", dropdownSLA);
		js.executeScript("arguments[0].click();", opcionSLA);
	}

	public void setUpsell() {
		js.executeScript("arguments[0].click();", dropdownUpsell);
		js.executeScript("arguments[0].click();", opcionUpsell);
	}
	
	public void setUpsellOpcion2() {
		js.executeScript("arguments[0].click();", dropdownUpsell);
		js.executeScript("arguments[0].click();", opcionUpsell2);
	}

	public void setActive() {
		js.executeScript("arguments[0].click();", dropdownActive);
		js.executeScript("arguments[0].click();", opcionActive);
	}

	public void pickCalendarDate() {
		js.executeScript("arguments[0].click();", calendario);
		js.executeScript("arguments[0].click();", opcionCalendario);
	}

	public void getGuardarCuentaNuevo() {
		js.executeScript("arguments[0].click();", guardarNuevo);
	}
	
	public void getGuardarCuenta() {
		guardar.click();
	}
	
	public WebElement getErrorVacio() {
		return errorVacio;
	}
	
	public WebElement getNombreCuenta() {
		return nombreCuenta;
	}
	
	public void modificarCuenta() {
		js.executeScript("arguments[0].click();", opcionesCuenta);
		js.executeScript("arguments[0].click();", modificarCuenta);
	}
	
	public WebElement getValoracion() {
		return dropdownValoracion;
	}
	
	public WebElement getTipo() {
		return dropdownTipo;
	}
	
	public WebElement getUpsell() {
		return dropdownUpsell;
	}
	
	public WebElement getNumeroEmpleados() {
		return inputNumeroEmpleados;
	}
	
	public WebElement getErrorEmpleados() {
		return errorEmpleados;
	}
	
	public boolean validacionErrorEmpleados(String mensaje) {
		boolean valido = false;
		if(mensaje.equalsIgnoreCase("Empleados: valor fuera del rango válido en campo numérico: 1431655766")){
			valido=true;
		}
		return valido;
	}

}
