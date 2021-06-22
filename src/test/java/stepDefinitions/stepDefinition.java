package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import pageObjects.AccountForm;
import pageObjects.LandingPage;
import pageObjects.ServicePage;
import resources.base;

@RunWith(Cucumber.class)
public class stepDefinition extends base {

	String n;
	int i = 0;
	String valueValOld;
	String valueTypeOld;
	String valueValNew;
	String valueTypeNew;
	String valueUpsellOld;
	String valueUpsellNew;

	//will execute before every scenario setting up the driver and login in
	@Before
	public void setup() throws IOException, InterruptedException {
		driver = initializeDriver();
		loginSalesforce(driver);
	}

	//will execute after every scenario closing the browser
	@After
	public void close() {
		driver.quit();
	}

	//Displays a message if we are at home page
	@Given("^Home page is displayed$")
	public void home_page_is_displayed() throws Throwable {
		LandingPage lp = new LandingPage(driver);
		Assert.assertTrue(lp.getCuadradito().isDisplayed());
		if (lp.getCuadradito().isDisplayed()) {
			System.out.println("llegamos al home");
		}
	}

	//navigates to services tabs
	@Then("^User reach Service page it starts to click each tab one by one$")
	public void user_reach_service_page_it_starts_to_click_each_tab_one_by_one() throws Throwable {
		navigateServiceTabs();
	}

	//Moves to service page
	@When("^User moves to Service$")
	public void user_moves_to_service() throws Throwable {
		moveToService(driver);
	}

	//Fill all the required fields from Account form and click save and new so a second form will open
	@And("^User set all the required fields and click save and new$")
	public void user_set_all_the_required_fields_and_click_save_and_new() throws Throwable {
		AccountForm af = new AccountForm(driver);

		af.setNombre("nombreRandom", i);

		// Mando opt. de dropdown VALORACION
		// af.setValoracion();
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

		guardarCuenta(i);

		i++;
	}

	
	@And("^User click in Accounts$")
	public void user_click_in_accounts() throws Throwable {
		ServicePage sp = new ServicePage(driver);
		// sp.getCuentas().click();
		jsClick(sp.getCuentas());
	}

	@And("^Click in new$")
	public void click_in_new() throws Throwable {
		ServicePage sp = new ServicePage(driver);
		sp.nuevoRegistro().click();
		// jsClick(sp.nuevoRegistro());
	}

	//2nd form opened then filled with required fields then click save and both account will successfully be created
	@Then("^second form should be visible and filled click save and both account will be successfully created$")
	public void second_form_should_be_visible_and_filled_click_save_and_both_account_will_be_successfully_created()
			throws Throwable {
		AccountForm af = new AccountForm(driver);

		af.setNombre("nombreRandom", i);

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

		guardarCuenta(i);

		i++;
	}

	
	//Try to save an account without the required fields filled then an error is displayed
	@When("^User try to save it without filling the fields required and an error should be displayed$")
	public void user_try_to_save_it_without_filling_the_fields_required_and_an_error_should_be_displayed()
			throws Throwable {
		AccountForm af = new AccountForm(driver);
		af.getGuardarCuenta().click();
		// jsClick(af.getGuardarCuenta());
	}

	//An error is displayed to trying to save an empty account form then will click at cancel button
	@Then("^The error is displayed and click cancel in the form$")
	public void the_error_is_displayed_and_click_cancel_in_the_form() throws Throwable {
		ServicePage sp = new ServicePage(driver);
		AccountForm af = new AccountForm(driver);
		Assert.assertTrue(af.getErrorVacio().isDisplayed());
		sp.cancelarElemento().click();
		// jsClick(sp.cancelarElemento());
	}

	//Take the last account name
	@And("^Grab the last created account name to use it for the contact name$")
	public void grab_the_last_created_account_name_to_use_it_for_the_contact_name() throws Throwable {
		n = getLastAccountNameCreated();
	}

	//Create a new contact using the name from the last account created
	@Then("^Goes to contact and click new it should use the name we grab before and save the contact$")
	public void goes_to_contact_and_click_new_it_should_use_the_name_we_grab_before_and_save_the_contact()
			throws Throwable {
		createContactTab(n);
	}

	//Modify (values, type and upsell dropdowns fields) and save it
	@When("^User modifies Values Type and Upsell fields and click save$")
	public void user_modifies_values_type_and_upsell_fields_and_click_save() throws Throwable {
		AccountForm af = new AccountForm(driver);
		// DATOS YA GUARDADOS
		// Valoracion dropdown
		valueValOld = af.getValoracion().getText();

		// dropdown TIPO
		valueTypeOld = af.getTipo().getText();

		// DATOS NUEVOS A GUARDAR/MODIFICAR
		// dropdown VALORACION NUEVO
		af.setDropdown(af.getValoracion(), "2");

		valueValNew = af.getValoracion().getText();

		// dropdown TIPO NUEVO
		af.setDropdown(af.getTipo(), "2");

		valueTypeNew = af.getTipo().getTagName();

		// Creo objeto del dropdown para hacer scroll hasta el elemento y luego ejecutar
		// acciones
		af.moveBottomAccountForm();

		// dropdown Upsell Opportunity viejo
		valueUpsellOld = af.getUpsell().getText();

		// dropdown Upsell Opportunity NUEVO
		af.setDropdown(af.getUpsell(), "2");

		valueUpsellNew = af.getUpsell().getText();

		// click guardar
		af.getGuardarCuenta().click();
		// jsClick(af.getGuardarCuenta());
	}

	//Validate changes between old and new values from the dropdowns - value - type - upsell
	@Then("^Validate the changes comparing the old values with the new$")
	public void validate_the_changes_comparing_the_old_values_with_the_new() throws Throwable {
		Assert.assertTrue(validateAccountChanges(valueValOld, valueTypeOld, valueUpsellOld, valueValNew, valueTypeNew,
				valueUpsellNew));

	}

	//Go to accounts and clicks the arrow and modify to modify the account
	@And("^User click in last account little arrow and click modify$")
	public void user_click_in_last_account_little_arrow_and_click_modify() throws Throwable {
		AccountForm af = new AccountForm(driver);
		af.modificarCuenta();
	}

	//Enters given number to Employee input and save it
	@When("^User enters 1431655766 in Employee field and click save$")
	public void user_enters_1431655766_in_employee_field_and_click_save() throws Throwable {
		AccountForm af = new AccountForm(driver);

		af.getNumeroEmpleados().sendKeys("1431655766");
		af.getGuardarCuenta().click();
		// jsClick(af.getGuardarCuenta());
	}

	//Capture the error and validate if its correct
	@Then("^It should give an specific error which we will validate$")
	public void it_should_give_an_specific_error_which_we_will_validate() throws Throwable {
		AccountForm af = new AccountForm(driver);

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