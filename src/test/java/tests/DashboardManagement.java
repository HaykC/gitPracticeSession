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
	
}
