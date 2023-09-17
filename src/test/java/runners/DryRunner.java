package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="./src/test/resources/features",
		glue="tests",
		tags="@addPaymentButtonVerification",
		dryRun=true
		)
public class DryRunner {

}

