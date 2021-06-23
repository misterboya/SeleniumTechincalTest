Feature: Login page

  Background:
    Given user is on login page
    When user accepts cookies
    Then page title should be "Yahoo UK | News, email and search"
    When user clicks on sign in button
    Then user login form is displayed

  Scenario: Login with correct credentials from feature parameterised
    When user enters username "FirstTestLogin_12"
    And user clicks on next button
    Then password form is displayed
    And user enters password "TestAbc_12!"
    And user clicks on next button
    Then page title should be "Yahoo UK | News, email and search"

    Scenario Outline: Login with correct credentials from excel file
      When user enters username from "<SheetName>" and <RowNumber>
      And user clicks on next button
      Then password form is displayed
      And  user clicks on next button
      When user enters password from "<SheetName>" and <RowNumber>
      And user clicks on next button
      Then page title should be "Yahoo UK | News, email and search"

      Examples:
      |     SheetName   |RowNumber|
      |    Credentials  |    0    |

  Scenario Outline: Login with incorrect username from excel file
    When user enters incorrect username from "<SheetName>" and <RowNumber>
    And user clicks on next button
    Then error message is displayed as "Sorry, we don't recognise this email address."

    Examples:
      |     SheetName   |RowNumber|
      |    Credentials  |    1    |
      |    Credentials  |    2    |

