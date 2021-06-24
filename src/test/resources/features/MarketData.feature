Feature: Market Data page

  Background:
    Given user has alrady logged into the application
    |      username     |   password  |
    | FirstTestLogin_12 | TestAbc_12! |

  Scenario: Validate current day market data
    And user selects finance from menu category
    When user hover on to market data and select calendar
    Then user gets the market data of the "24JunThu"

