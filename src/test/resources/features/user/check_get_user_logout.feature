  Feature: Check on inventory status

  The `/user/logout` end point returns

  Scenario: Application user login with valid credentials
    Given the application is running
    When I check user logout
    Then the user should be log out from application