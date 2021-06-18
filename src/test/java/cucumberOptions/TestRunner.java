package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//falta importar el cucumber options de junit si quiero usarlo con eso

//import io.cucumber.junit.Cucumber;




//feature file
//stepDefinitions file
//@RunWith(Cucumber.class) -- PARA USAR CON JUNIT
//DE ESTA FORMA ES PARA USAR TESTNG
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinitions")



public class TestRunner extends AbstractTestNGCucumberTests{

}
