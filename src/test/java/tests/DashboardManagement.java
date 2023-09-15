package tests;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CommonPage;
import pages.DashboardPage;
import utils.BrowserUtils;

public class DashboardManagement {
	BrowserUtils utils = new BrowserUtils();
	DashboardPage dashboard = new DashboardPage();
	CommonPage common = new CommonPage();

	@Then("I should be on the Dashboard home page")
	public void i_should_be_on_the_dashboard_home_page() {
	    utils.waitForElementToBeVisible(common.CommonPage_Dashboard);
	    Assert.assertTrue(common.CommonPage_Dashboard.isDisplayed());
	}
	@Then("I should see the header {string}")
	public void i_should_see_the_header(String string) {
		Assert.assertEquals(dashboard.Dashboard_SalesAndExpenses_Header.getText(), string);
	}
	
	@Then("I should see buttons Amount Due, Customers, Invoices and Estimates.")
	public void i_should_see_buttons_amount_due_customers_invoices_and_estimates() {
	   utils.waitForElementToBeVisible(dashboard.AmountDue);
	   Assert.assertTrue(dashboard.AmountDue.isDisplayed());
	   Assert.assertTrue(dashboard.Customers.isDisplayed());
	   Assert.assertTrue(dashboard.Invoices.isDisplayed());
	   Assert.assertTrue(dashboard.Estimates.isDisplayed());
	}
	
	@Then("I should see buttons {string}, {string}, {string} and {string}.")
	public void i_should_see_buttons_and(String amountDue, String customers, String invoices, String estimates) {
		utils.waitForElementToBeVisible(dashboard.AmountDue);
	    Assert.assertTrue(dashboard.AmountDue.getText().contains(amountDue));
	    Assert.assertTrue(dashboard.Customers.getText().contains(customers));
	    Assert.assertTrue(dashboard.Invoices.getText().contains(invoices));
	    Assert.assertTrue(dashboard.Estimates.getText().contains(estimates)); 
	}
	
	@Then("I should see headers {string} and {string}.")
	public void i_should_see_headers_and(String dueInvoices, String recentEstimates) {
		utils.waitForElementToBeVisible(dashboard.Dashboard_DueInvoices_Header);
		Assert.assertTrue(dashboard.Dashboard_DueInvoices_Header.isDisplayed());
		Assert.assertTrue(dashboard.Dashboard_RecentEstimates_Header.isDisplayed());
		Assert.assertTrue(dashboard.Dashboard_DueInvoices_Header.getText().contains(dueInvoices));
		Assert.assertTrue(dashboard.Dashboard_RecentEstimates_Header.getText().contains(recentEstimates));
		
	}
	
	@Then("I should Verify that you see the graph under Sales & Expenses section and a dropdown with {string} and {string} options.")
	public void i_should_verify_that_you_see_the_graph_under_sales_expenses_section_and_a_dropdown_with_and_options(String string, String string2) {
	    utils.waitForElementToBeClickable(dashboard.Dashboard_Table);
	    Assert.assertTrue(dashboard.Dashboard_Table.isDisplayed());
	    Assert.assertTrue(dashboard.Dashboard_DropDown.isDisplayed());
	    utils.waitForElementToBeVisible(dashboard.Dashboard_DropDown);
	    dashboard.Dashboard_DropDown.click();
	    utils.waitForElementToBeVisible(dashboard.Dashboard_DropDownYear_This_Year);
	    Assert.assertTrue(dashboard.Dashboard_DropDownYear_This_Year.isDisplayed());
	    Assert.assertTrue(dashboard.Dashboard_DropDownYear_Previous_Year.isDisplayed());
	}
}










