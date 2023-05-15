Feature: Login Functionalities

  @smoke1
  Scenario: Valid Admin login
    #Given open the browser and launch HRMS application
    When user enters valid email and valid password
    And click on login button
    Then user is logged in successfully
    #And close the browser

  @smoke2
  Scenario: Valid Admin login
    #Given open the browser and launch HRMS application
    When user enters valid email "ADMIN" and valid password "Hum@nhrm123"
    And click on login button
    Then user is logged in successfully
    #And close the browser


  #Hooks defining pre  and post steps in every Cucumber framework irrespective of what goes in between the test steps
  #hooks are always created in step definition folder

  @scenarioOutline
  #Parameterization/ Data Driven
  Scenario Outline: Login with multiple credentials using Scenario Outline
    #Given open the browser and launch HRMS application
    When user enters valid email "<username>" and valid password "<password>"
    And click on login button
    Then user is logged in successfully
    #And Close the browser
    Examples:
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Jason    | Hum@nhrm123 |



  @dataTable
  Scenario: Login with multiple credentials using data table
    When user enters username and password and verifies login
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Jason    | Hum@nhrm123 |

#data table - it has to reach the start location after test execution

  #hard code
  #config file
  #Cucumber Expression
  #Scenario Outline
  #Data Table

  #hard Code
    # config file

    #Cucumber Expression [ limited set of test data]
    #Scenario Outline    [Parametrization],[Data Driven Testing][Browser will open and close number of times we have test data in Examples table]
    #data Table

    # Page Object Model : An object Repository [ It stores or holds all the webElements specific to a particular page]