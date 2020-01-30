  Feature: Check on user can post valid order

  The `/store/order` allows user to post new valid order

  Scenario: User post new valid order
    Given the application is running
    When I post new valid store order
    Then the API should return response with status code 200
