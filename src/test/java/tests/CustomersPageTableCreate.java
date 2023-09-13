package tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CommonPage;
import pages.CustomersPage;
import utils.BrowserUtils;

public class CustomersPageTableCreate {
	CommonPage common = new CommonPage();
	BrowserUtils utils = new BrowserUtils();
	CustomersPage customer = new CustomersPage();
		
	@When("I create a new customer")
	public void i_create_a_new_customer() {
		utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BTN);
	    customer.Customers_NewCustomer_BTN.click();
	    utils.waitForElementToBeVisible(customer.Customers_BasicInfo_DisplayName_Box);
	    utils.sendKeysWithActionClass(customer.Customers_BasicInfo_DisplayName_Box, "Mike");
	    utils.sendKeysWithActionClass(customer.Customers_BasicInfo_PrimaryContact_Box, "847847847");
	    utils.sendKeysWithActionClass(customer.Customers_BasicInfo_Email_Box, "kauygfua@gmail.com");
	    utils.waitForElementToBeVisible(customer.Customers_SaveCustomer_BTN);
	    customer.Customers_SaveCustomer_BTN.click();
	    utils.waitForElementToBeVisible(common.CommonPage_Customers);
	    common.CommonPage_Customers.click();
	    
	}
	
	
	@Then("I should see the customers list table displayed")
	public void i_should_see_the_customers_list_table_displayed() {
		utils.waitForElementToBeVisible(customer.Customers_Table);
		Assert.assertTrue(customer.Customers_Table.isDisplayed());
	}


}
