package tests;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.PaymentsPage;
import utils.BrowserUtils;

public class PaymentsManagement {
	
	BrowserUtils utils = new BrowserUtils();
	CommonPage common = new CommonPage();
	PaymentsPage payment = new PaymentsPage();

	@When("I navigate to {string} tab")
	public void i_navigate_to_tab(String string) {
	    utils.waitForElementToBeClickable(common.CommonPage_Payments);
	    common.CommonPage_Payments.click();
	}
	@Then("I should see {string} button displayed")
	public void i_should_see_button_displayed(String string) {
	    utils.waitForElementToBeVisible(payment.Payments_AddPayment_BTN);
	    Assert.assertTrue(payment.Payments_AddPayment_BTN.isDisplayed());
	}
	
}
