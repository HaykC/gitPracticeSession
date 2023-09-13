package tests;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.CustomersPage;
import utils.BrowserUtils;
import utils.Driver;


public class CustomerManagementSteps {
	CommonPage common = new CommonPage();
	BrowserUtils utils = new BrowserUtils();
	CustomersPage customer = new CustomersPage();
	String displayName, email, billingAddress, shippingAddress;
	Actions action = new Actions(Driver.getDriver());
	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(7));
	
	@When("I navigate to the customers tab")
	public void i_navigate_to_the_customers_tab() {
		utils.waitForElementToBeVisible(common.CommonPage_Customers);
	    common.CommonPage_Customers.click();
	    utils.waitForElementToBeVisible(customer.Customers_CustomersHeaderText);
	    Assert.assertTrue(customer.Customers_CustomersHeaderText.isDisplayed());
	}

	@Then("I should see the New Customer button displayed")
	public void i_should_see_the_new_customer_button_displayed() throws InterruptedException {
		utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BTN);
	    Assert.assertTrue(customer.Customers_NewCustomer_BTN.isDisplayed());
	    
	    
	    Driver.getDriver().quit();
	}
	
	
	@Then("I should see four columns are present in the table: Name, Phone, Amount Due, Added On")
	public void i_should_see_four_columns_are_present_in_the_table_name_phone_amount_due_added_on() {
	    utils.waitForElementToBeVisible(customer.Customers_Table_Name);
	    Assert.assertTrue(customer.Customers_Table_Name.isDisplayed());
	    Assert.assertTrue(customer.Customers_Table_Phone.isDisplayed());
	    Assert.assertTrue(customer.Customers_Table_Amount_Due.isDisplayed());
	    Assert.assertTrue(customer.Customers_Table_Added_On.isDisplayed());
	    
	}
	
	@Then("I should see the More link with Edit, View and Delete options")
	public void i_should_see_the_more_link_with_edit_view_and_delete_options() {
	    utils.waitForElementToBeVisible(customer.Customers_Table_More_Options);
	    Assert.assertTrue(customer.Customers_Table_More_Options.isDisplayed());
	    customer.Customers_Table_More_Options.click();
	    
	    utils.waitForElementToBeVisible(customer.Customers_Table_More_View);
	    Assert.assertTrue(customer.Customers_Table_More_View.isDisplayed());
	    Assert.assertTrue(customer.Customers_Table_More_Edit.isDisplayed());
	    Assert.assertTrue(customer.Customers_Table_More_Delete.isDisplayed());
		
	}
	
	@Then("I enter a display name")
	public void i_enter_a_display_name() {
	    utils.waitForElementToBeVisible(customer.Customers_BasicInfo_DisplayName_Box);
	    utils.sendKeysWithActionClass(customer.Customers_BasicInfo_DisplayName_Box, "nurlan");
	    utils.waitForElementToBeVisible(customer.Customers_Table_Phone);
	    utils.sendKeysWithActionClass(customer.Customers_Table_Phone, "5455454");
	}
	@Then("click save")
	public void click_save() {
	    utils.waitForElementToBeVisible(customer.Customers_SaveCustomer_BTN);
	    action.moveToElement(customer.Customers_SaveCustomer_BTN).build().perform();;
	    action.click().build().perform();;
	}
	@Then("I should see the message {string} appears")
	public void i_should_see_the_message_appears(String string) {
	    utils.waitForElementToBeVisible(customer.Customers_Created_Success_Header);
	    Assert.assertTrue(customer.Customers_Created_Success_Header.getText().equals(string));
	}
	
	@Then("I delete the customer")
	public void i_delete_the_customer() throws InterruptedException {
	    utils.waitForElementToBeVisible(common.CommonPage_Customers);
	    Thread.sleep(2000);
	    common.CommonPage_Customers.click();
	    utils.waitForElementToBeVisible(customer.Customers_CustomersHeaderText);
	    utils.waitForElementToBeVisible(customer.Customers_Table_FIlter_BTN);
	    customer.Customers_Table_FIlter_BTN.click();
	    utils.waitForElementToBeVisible(customer.Customers_Table_FIlter_DisplayName);
	    utils.sendKeysWithActionClass(customer.Customers_Table_FIlter_DisplayName, "nurlan");
	    utils.waitForElementToBeVisible(customer.Customers_Table);
	    utils.waitForElementToBeVisible(customer.Customers_Table_3Dots_Options_BTN);
	    customer.Customers_Table_3Dots_Options_BTN.click();
	    utils.waitForElementToBeVisible(customer.Customers_Table_More_Delete);
	    customer.Customers_Table_More_Delete.click();
	    utils.waitForElementToBeVisible(customer.Customers_Table_Delete_OK_BTN);
	    customer.Customers_Table_Delete_OK_BTN.click();
	}


	@Then("I should see an error message {string}")
	public void i_should_see_an_error_message(String string) {
	   utils.waitForElementToBeVisible(customer.Customers_NewCustomer_FieldIsRequired_error_message);
	   Assert.assertTrue(customer.Customers_NewCustomer_FieldIsRequired_error_message.isDisplayed());
	   Assert.assertEquals(customer.Customers_NewCustomer_FieldIsRequired_error_message.getText(), string);
	}
	
	
	@Then("Enter a valid {string}, {string}, {string}, and {string}.")
	public void enter_a_valid_and(String displayName, String email, String billingAddress, String shippingAddress) {
		this.displayName = displayName;
		this.email = email;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		utils.waitForElementToBeVisible(customer.Customers_BasicInfo_DisplayName_Box);
		utils.sendKeysWithActionClass(customer.Customers_BasicInfo_DisplayName_Box, displayName);
		utils.waitForElementToBeVisible(customer.Customers_BasicInfo_Email_Box);
		utils.sendKeysWithActionClass(customer.Customers_BasicInfo_Email_Box, email);
		utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BillingAddress_Street1_Box);
		utils.sendKeysWithActionClass(customer.Customers_NewCustomer_BillingAddress_Street1_Box, billingAddress);
		utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BillingAddress_Street2_Box);
		utils.sendKeysWithActionClass(customer.Customers_NewCustomer_BillingAddress_Street2_Box, shippingAddress);
		utils.waitForElementToBeVisible(customer.Customers_NewCustomer_BillingAddress_SelectCountry_Drop);
		customer.Customers_NewCustomer_BillingAddress_SelectCountry_Drop.click();
		customer.Customers_NewCustomer_BillingAddress_SelectCountry_Armenia.click();
		
	}
	
	@Then("I delete the customers")
	public void i_delete_the_customers() throws InterruptedException {
		 utils.waitForElementToBeVisible(common.CommonPage_Customers);
		    Thread.sleep(2000);
		    common.CommonPage_Customers.click();
		    utils.waitForElementToBeVisible(customer.Customers_CustomersHeaderText);
		    utils.waitForElementToBeVisible(customer.Customers_Table_FIlter_BTN);
		    customer.Customers_Table_FIlter_BTN.click();
		    utils.waitForElementToBeVisible(customer.Customers_Table_FIlter_DisplayName);
		    utils.sendKeysWithActionClass(customer.Customers_Table_FIlter_DisplayName, this.displayName);
		    utils.waitForElementToBeVisible(customer.Customers_Table);
		    utils.waitForElementToBeVisible(customer.Customers_Table_3Dots_Options_BTN);
		    utils.waitForElementToBeVisible(customer.Customers_Table);
		    wait.until(ExpectedConditions.elementToBeClickable(customer.Customers_Table_3Dots_Options_BTN));
		    customer.Customers_Table_3Dots_Options_BTN.click();
		    utils.waitForElementToBeVisible(customer.Customers_Table_More_Delete);
		    customer.Customers_Table_More_Delete.click();
		    utils.waitForElementToBeVisible(customer.Customers_Table_Delete_OK_BTN);
		    customer.Customers_Table_Delete_OK_BTN.click();
		    
		    Driver.getDriver().quit();
	}
	
	
	
	
	
	
	
	

}
