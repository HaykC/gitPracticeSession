package tests;

import java.time.Duration;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.CraterLoginPage;
import utils.BrowserUtils;
import utils.Driver;
import utils.TestDataReader;

public class UserManagementSteps {
	
	BrowserUtils utils = new BrowserUtils();
	CraterLoginPage login = new CraterLoginPage();
	CommonPage common = new CommonPage();
	
	@Given("As a user, I am on the login page")
	public void as_a_user_i_am_on_the_login_page() {
	    Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	    Driver.getDriver().manage().window().maximize();
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
	}

	@Given("click on the login button")
	public void click_on_the_login_button() {
	   login.loginBtn.click();
	}

	@Then("I should see two errors with Field is required messsage")
	public void i_should_see_two_errors_with_field_is_required_messsage() {
		Assert.assertTrue(login.requiredFieldMessages.size() == 2);
	}

	@Then("I enter valid email and invalid password")
	public void i_enter_valid_email_and_invalid_password() {
		utils.waitForElementToBeVisible(login.emailBox);
	    utils.sendKeysWithActionClass(login.emailBox, TestDataReader.getProperty("email"));
	    utils.sendKeysWithActionClass(login.passwordBox, TestDataReader.getProperty("invalidPassword"));
	}

	@Then("I click the Login button")
	public void i_click_the_login_button() {
	    login.loginBtn.click();
	}

	@Then("I should see credentials do not match error displayed")
	public void i_should_see_displayed() {
		utils.waitForElementToBeVisible(login.wrongCredentialsError);
	   Assert.assertTrue(login.wrongCredentialsError.isDisplayed());
	   
	   Driver.getDriver().quit();
	}
	
	


}
