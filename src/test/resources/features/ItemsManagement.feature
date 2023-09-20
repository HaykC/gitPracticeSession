@itemsManagement
Feature: Items Management

	Background:
	 Given As an entity user, I am logged in

  @create&DeleteItem
  Scenario: As a user, I am able to create an item or a service
    And I navigate to Items tab
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "Coffee mugs", price "1500", unit "pc", and description "a nice book"
    And I click Save Item button
    Then The Item is added to the Item list table
    And I delete the item

  @editItem
  Scenario: As a user, I am able to update an item or a service
    And I navigate to Items tab
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "Coffee mugs", price "1500", unit "pc", and description "a nice book"
    And I click Save Item button
    Then The Item is added to the Item list table
    When I click on Edit button
    Then I should be on Edit Items page
    When I update the items price to "2200"
    And I click on update item button
    Then Items price should be updated to "2200"
    And I delete the item
    
 @ScenarioOutlineDemo
  Scenario Outline: As a user, I am able to create items with data table
    And I navigate to Items tab
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "<itemName>", price "<itemPrice>", unit "<unitType>", and description "<itemDes>"
    And I click Save Item button
    Then The Item is added to the Item list table
    And I delete the item

    Examples: Items Data
    | itemName        | itemPrice | unitType | itemDes                |
    | Water bottle    | 3800      | pc       | Very good water bottle |
    | Chair           | 5800      | pc       | Nice chair             |
    | Ugly coffee mug | 4500      | pc       | Very ugly coffee mug   |
    | Air conditioner | 28800     | pc       | Reliable AC            |
    
