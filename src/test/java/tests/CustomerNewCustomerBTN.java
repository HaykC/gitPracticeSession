package tests;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.CustomersPage;
import utils.BrowserUtils;
import utils.Driver;

public class CustomerNewCustomerBTN {
	CommonPage common = new CommonPage();
	BrowserUtils utils = new BrowserUtils();
	CustomersPage customer = new CustomersPage();
	
	@When("I click on New Customer button")
	public void i_click_on_new_customer_button() {
	    utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BTN);
	    customer.Customers_NewCustomer_BTN.click();
	}

	@Then("I should see the Basic info form label")
	public void i_should_see_the_basic_info_form_label() {
	    utils.waitForElementToBeVisible(customer.Customers_BasicInfo_Header);
	    Assert.assertTrue(customer.Customers_BasicInfo_Header.isDisplayed());
	    
	}
	
	// start of the Basic info fields test
	@Then("I should see the fields in the Basic info section")
	public void i_should_see_the_fields_in_the_basic_info_section() {
	   utils.waitForElementToBeVisible(customer.Customers_BasicInfo_DisplayName_Box);
	   Assert.assertTrue(customer.Customers_BasicInfo_DisplayName_Box.isDisplayed());
	   Assert.assertTrue(customer.Customers_BasicInfo_PrimaryContact_Box.isDisplayed());
	   Assert.assertTrue(customer.Customers_BasicInfo_Email_Box.isDisplayed());
	   Assert.assertTrue(customer.Customers_BasicInfo_PrimaryCurrency.isDisplayed());
	   Assert.assertTrue(customer.Customers_BasicInfo_Website.isDisplayed());
	   Assert.assertTrue(customer.Customers_BasicInfo_Prefix.isDisplayed());
	// end of the Basic info fields test
	   
//	Driver.getDriver().quit();
	}
}

