  Feature: Check on user login with invalid credentials

  The `/user/login` end point allows user to log in with credentials

  Scenario: Application user login with invalid credentials
    Given the application is running
    When I check user login with invalid credentials:
      | login   | password  |
      |         | 123       |
      | 123     |           |
      |         |           |
    Then the API should return response which contains "Invalid username/password supplied"