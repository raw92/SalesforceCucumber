package pageObjects;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.dataDriven;



public class LoginPage {

	public WebDriver driver;
	ArrayList<String> data;

	@FindBy(id = "username")
	WebElement username;

	@FindBy(id = "password")
	WebElement Password;

	@FindBy(id = "Login")
	WebElement Login;

	
	// constructor de la clase
	public LoginPage(WebDriver driver) throws IOException {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		dataDriven d= new dataDriven();
		data = d.getData("Login");
		
	}

	public void getUsername() {
		username.sendKeys(data.get(1));;
	}

	public void getPassword() {
		Password.sendKeys(data.get(2));;
	}

	public void getLogin() {
		Login.click();
	}

	
}
