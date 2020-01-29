  Feature: Check on user login with valid credentials

  The `/user/login` end point returns

  Scenario: Application user login with valid credentials
    Given the application is running
    When I check user login with valid credentials:
      | login   | password  |
      | name    | 123       |
      | 123     | asd       |
      | -!@#!@$ | '""'      |
    Then the API should return valid logged in response