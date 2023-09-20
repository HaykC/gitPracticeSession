#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@userManagement @Regression
Feature: Login attempt without credentials

  Scenario: 2 field required messages
    Given As a user, I am on the login page
    And click on the login button
    Then I should see two errors with Field is required messsage
    Then I enter valid email and invalid password
    And I click the Login button
    Then I should see credentials do not match error displayed
    
    
 @ScenarioOutlineHomeWork   
 Scenario Outline: Invalid login attempts
    Given As a user, I am on the login page
    Then I enter valid "<email>" and invalid "<password>"
    And click on the login button
    Then I should see credentials do not match error displayed
    And I should not be logged in
    
    Examples: login credentials data
    | email                          | password          | 
    | helil@primetechschool.com      | standardhhfvajk   | 
    | nothing@primetechschool.com    | Testing123        |
    |                                | Testing123        |
    | nothing@primetechschool.com    |                   |
    