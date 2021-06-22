package pageObjects;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
import resources.dataDriven;

public class AccountForm extends base{

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
	
	//Default Dropdown maker
	public void setDropdown(WebElement dropdown, String opt) {
		jsClick(dropdown);
		jsClick(getOptionDropdown(dropdown, opt));
	}
			
	public void moveBottomAccountForm() {
		js.executeScript("arguments[0].scrollIntoView();", customerPrioDropdown);
		
	}
	
	public void pickCalendarDate() {
		jsClick(calendario);
		jsClick(opcionCalendario);				
	}

	public void getGuardarCuentaNuevo() {
		jsClick(guardarNuevo);
	}
	
	public WebElement getGuardarCuenta() {
		return guardar;
	}
	
	public WebElement getErrorVacio() {
		return errorVacio;
	}
	
	public WebElement getNombreCuenta() {
		return nombreCuenta;
	}
	
	//This click at the arraow to the right of the account name at the account panel when you see all the accounts
	//Then clicks in modify when the little window opens
	public void modificarCuenta() {
		jsClick(opcionesCuenta);
		jsClick(modificarCuenta);
	}
////////////////////////////////////////////
	//Generic dropdown option selector / give the dropdown and option then searches for the option below
	public WebElement getOptionDropdown(WebElement dropdown, String opt) {
		WebElement retorno;
		
		if(dropdown.equals(dropdownValoracion)) {
			if(opt == "2") {
				retorno = opcionValoracion2;
			}else{
				retorno = opcionValoracion;
			}
		}else if(dropdown.equals(dropdownTipo)) {
			if(opt == "2") {
				retorno = opcionTipo2;
			}else{
				retorno = opcionTipo;
			}
		}else if(dropdown.equals(dropdownPropiedad)) {
			retorno = opcionPropiedad;
		}else if(dropdown.equals(dropdownSector)) {
			retorno = opcionSector;
		}else if(dropdown.equals(customerPrioDropdown)) {
			retorno = opcionCustomerPrio;
		}else if(dropdown.equals(dropdownSLA)) {
			retorno = opcionSLA;
		}else if(dropdown.equals(dropdownUpsell)) {
			if(opt == "2") {
				retorno = opcionUpsell2;
			}else{
				retorno = opcionUpsell;
			}
		}else {
			retorno = opcionActive;
		}
		
		return retorno;
	}
//////////////////////////////////////
	public WebElement getValoracion() {
		return dropdownValoracion;
	}
	
	public WebElement getTipo() {
		return dropdownTipo;
	}
	
	public WebElement getPropiedad() {
		return dropdownPropiedad;
	}

	public WebElement getSector() {
		return dropdownSector;
	}
	
	public WebElement getCustomerPrio() {
		return customerPrioDropdown;
	}
	
	public WebElement getSLA() {
		return dropdownSLA;
	}

	public WebElement getUpsell() {
		return dropdownUpsell;
	}
	
	public WebElement getActive() {
		return dropdownActive;
	}

///////////////////////////////////////////////////////	
	public WebElement getNumeroEmpleados() {
		return inputNumeroEmpleados;
	}
	
	//It gives me the error from the employee input when i send the number asked
	public WebElement getErrorEmpleados() {
		return errorEmpleados;
	}
	
	//Validates if the message from the Employee error is the same as below or not
	public boolean validacionErrorEmpleados(String mensaje) {
		boolean valido = false;
		if(mensaje.equalsIgnoreCase("Empleados: valor fuera del rango válido en campo numérico: 1431655766")){
			valido=true;
		}
		return valido;
	}
	
//	public void jsClick(WebElement element) {
//		js.executeScript("arguments[0].click();", element);
//	}

}
