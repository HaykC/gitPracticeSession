package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class DashboardPage {
	public DashboardPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
		
	
	
	@FindBy(xpath = "//span[text()='Amount Due']")
	public WebElement AmountDue;

	@FindBy(xpath = "//span[text()='Customers']")
	public WebElement Customers;

	@FindBy(xpath = "//span[text()='Invoices']")
	public WebElement Invoices;

	@FindBy(xpath = "//span[text()='Estimates']")
	public WebElement Estimates;

	@FindBy(xpath = "//div[text()='This year']")
	public WebElement ThisYearDrop;
	
	@FindBy (xpath = "//p[text()='Logged in successfully.']")
	public WebElement loggedInSuccessMessage;
	
	@FindBy (xpath = "//h6[contains(text(), 'Sales &')]")
	public WebElement Dashboard_SalesAndExpenses_Header;

	
	

}
