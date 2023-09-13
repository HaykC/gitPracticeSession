package tests;

import java.time.Duration;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.CraterLoginPage;
import pages.DashboardPage;
import pages.ItemsPage;
import utils.BrowserUtils;
import utils.Driver;
import utils.TestDataReader;


public class ItemsManagement_steps {
	BrowserUtils utils = new BrowserUtils();
	CraterLoginPage login = new CraterLoginPage();
	CommonPage common = new CommonPage();
	DashboardPage dashboard = new DashboardPage();
	ItemsPage items = new ItemsPage();
	
	@Given("As an entity user, I am logged in")
	public void as_an_entity_user_i_am_logged_in() {
	    Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	    Driver.getDriver().manage().window().maximize();
	    Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    utils.waitForElementToBeVisible(login.emailBox);
	    utils.sendKeysWithActionClass(login.emailBox, TestDataReader.getProperty("email"));
	    utils.sendKeysWithActionClass(login.passwordBox, TestDataReader.getProperty("password"));
	    login.loginBtn.click();
	    
	  
	
	}

	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
		items.Items.click();
		utils.waitForElementToBeVisible(items.ItemHeaderText);
		Assert.assertTrue(items.ItemHeaderText.isDisplayed());
	}

	@When("I click on the Add Item button")
	public void i_click_on_the_add_item_button() {
	   items.AddItem.click();
	}

	@When("I should be on item input page")
	public void i_should_be_on_item_input_page() {
	   utils.waitForElementToBeVisible(items.AddItem_NewItem_Text);
	   Assert.assertTrue(items.AddItem_NewItem_Text.isDisplayed());
	}

	@When("I provide item information name {string}, price {string}, unit {string}, and description {string}")
	public void i_provide_item_information_name_price_unit_and_description(String itemName, String price, String unit, String description) {
		utils.waitForElementToBeVisible(items.AddItem_NameBox);
		utils.sendKeysWithActionClass(items.AddItem_NameBox, itemName);
	    utils.sendKeysWithActionClass(items.AddItem_PriceBox, price);
	    items.AddItem_UnitDrop.click();
	    utils.waitForElementToBeVisible(items.AddItem_UnitDrop_pc_Option);
	    items.AddItem_UnitDrop_pc_Option.click();
	    
	    utils.sendKeysWithActionClass(items.AddItem_DescriptionBox, description);
	   
	}

	@When("I click Save Item button")
	public void i_click_save_item_button() {
	    items.AddItem_SaveItemBtn.click();
	}

	@Then("The Item is added to the Item list table")
	public void the_item_is_added_to_the_item_list_table() {
	   utils.waitForElementToBeVisible(items.ItemHeaderText);
	   Assert.assertTrue(items.ItemHeaderText.isDisplayed());
	}

	@Then("I delete the item")
	public void i_delete_the_item() throws InterruptedException {
	    items.AddItem_FilterBtn.click();
	    utils.waitForElementToBeVisible(items.AddItem_Filter_Name_Box);
	    utils.sendKeysWithActionClass(items.AddItem_Filter_Name_Box, "Coffee mugs");
	    utils.waitForElementToBeVisible(items.AddItem_Filter_3_Dots_Btn);
	    items.AddItem_Filter_3_Dots_Btn.click();
	    utils.waitForElementToBeVisible(items.AddItem_Filter_DeleteItem);
	    items.AddItem_Filter_DeleteItem.click();
	    utils.waitForElementToBeVisible(items.AddItem_Filter_Delete_AreYouSure_Text);
	    items.AddItem_Filter_Delete_Ok_Btn.click();
	    
	    Thread.sleep(2000);
	    Driver.driver.quit();
	}

	
	@When("I click on Edit button")
	public void i_click_on_edit_button() {
		utils.waitForElementToBeVisible(items.AddItem_Filter_3_Dots_Btn);
	    items.AddItem_Filter_3_Dots_Btn.click();
	    utils.waitForElementToBeVisible(items.AddItem_Filter_EditItem);
	    items.AddItem_Filter_EditItem.click();
	}
	
	@Then("I should be on Edit Items page")
	public void i_should_be_on_edit_items_page() {
	    utils.waitForElementToBeVisible(items.EditItem_Header_Text);
	    Assert.assertTrue(items.EditItem_UpdateItem_BTN.isDisplayed());
	}
	
	@When("I update the items price to {string}")
	public void i_update_the_items_price_to(String newPrice) {
		utils.waitForElementToBeVisible(items.AddItem_PriceBox);
	    items.AddItem_PriceBox.clear();
	    utils.sendKeysWithActionClass(items.AddItem_PriceBox, newPrice);
	}
	@When("I click on update item button")
	public void i_click_on_update_item_button() {
	    utils.waitForElementToBeVisible(items.EditItem_UpdateItem_BTN);
	    items.EditItem_UpdateItem_BTN.click();
	}
	@Then("Items price should be updated to {string}")
	public void items_price_should_be_updated_to(String newPrice) {
		utils.waitForElementToBeVisible(items.EditItem_UpdateItem_Price);
	    Assert.assertTrue(items.EditItem_UpdateItem_Price.isDisplayed());
	}

	

}
