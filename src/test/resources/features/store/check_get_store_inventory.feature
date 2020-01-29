  Feature: Check on user can logout

  The `/user/logout` allows user to logout

  Scenario: Application user logout end-point
    Given the application is running
    When I check the store inventory
    Then the API should return valid JSON
