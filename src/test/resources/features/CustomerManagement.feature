@CustomerManagementTests
Feature: Customer management

  Background: 
    Given As an entity user, I am logged in
    When I navigate to the customers tab

  @newCustomerBTNTest
  Scenario: As a user when I navigate to the Customers Tab, I should see New customer button.
    Then I should see the New Customer button displayed

  @newCustomerBasicInfoFormLabel
  Scenario: As a user when I navigate to the Customers Tab and Click on New customer button, I should see the basic information form label.
    And I click on New Customer button
    Then I should see the Basic info form label

  @NewCustomerBasicInfoSection
  Scenario: As a user when I navigate to the Customers Tab and Click on New customer button, I should see the basic information form label.
    And I click on New Customer button
    Then I should see the Basic info form label
    Then I should see the fields in the Basic info section

  @CustomerListDisplayedTest
  Scenario: As a user when I navigate to the Customers Tab I should see the customers list table displayed.
    And I click on New Customer button
    And I create a new customer
    Then I should see the customers list table displayed

  @CustomerTableDisplayedTest
  Scenario: As a user when I navigate to the Customers Tab I should see the customers list table displayed.
    Then I should see four columns are present in the table: Name, Phone, Amount Due, Added On

  @CustomerMoreOptionsDisplayedTest
  Scenario: As a user when I navigate to the Customers Tab I should see the customers list table displayed.
    Then I should see the More link with Edit, View and Delete options

  @CreateCustomer
  Scenario: As a user I am on the customers tab page
    And I click on New Customer button
    Then I enter a display name
    And click save
    Then I should see the message "Success!" appears
    And I delete the customer

  @CreateCustomerWithoutName
  Scenario: Creating a customer without name
    And I click on New Customer button
    And click save
    Then I should see an error message "Field is required"

  @CreatingAndDeletingCustomersWithData
  Scenario Outline: creating a customer with data
    And I click on New Customer button
    Then Enter a valid "<Display Name>", "<Email>", "<Billing Address>", and "<Shipping Address>".
    And click save
    Then I should see the message "Success!" appears
    And I delete the customers

    Examples: 
      | Display Name | Email             | Billing Address | Shipping Address |
			| person1      | person1@gmail.com | 123 hello rd    | 132165           |
			| person2      | person2@gmail.com | 124 hello rd    | 60025            |
			| person3      | person3@gmail.com | 125 hello rd    | 60098            |
      
      
      
      
      