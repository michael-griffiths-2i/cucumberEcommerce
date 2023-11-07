

Feature: Checking the order number and discounts on the edgewords site

  Scenario: Applying a Discount Code
    Given I am logged in
    And I am on the shop page
    When I add an item to the cart
    Then the discount is applied correctly

  Scenario: Matching Order Numbers
    Given I am logged in
    And I am on the shop page
    When I add an item to the cart
    Then the order numbers match
