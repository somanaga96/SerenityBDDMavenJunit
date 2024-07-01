Feature: Order products in Swag Labs

  Background:
    Given <user> tries to launch the Swag Labs website and logs in

  @green
  Scenario: Order in Swag Labs
#    Given "som" tries to launch the Swag Labs website and logs in
    When "he" adds product to cart
    And "he" adds address details
    Then "he" should able to see the message as "Thank you for your order!"

  @red
  Scenario Outline: Order in Swag Labs
#    Given <user> tries to launch the Swag Labs website and logs in
    When "he" adds product to cart
    And "he" adds address details
    Then "he" should able to see the message as "Thank you for your order!"
    Examples:
      | user |
      | som  |
      | jeni |
      | jai  |