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



public class LandingPage {

	public WebDriver driver;
	JavascriptExecutor js;

	@FindBy(css = ".slds-icon-waffle")
	private WebElement cuadradito;

	@FindBy(xpath = "//input[@class = 'slds-input']")
	private WebElement inputSearch;

	@FindBy(xpath = "//p[@class='slds-truncate']")
	private WebElement servicios;

	
	
	// constructor de la clase
	public LandingPage(WebDriver driver) throws IOException {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
			
	}

	

	public void getServicios() {
		js.executeScript("arguments[0].click();", cuadradito);
		inputSearch.sendKeys("Servicio");
		js.executeScript("arguments[0].click();", servicios);
	}

	
}
