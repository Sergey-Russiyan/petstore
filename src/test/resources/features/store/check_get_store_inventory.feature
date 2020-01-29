  Feature: Check on inventory status

  The `/pet/{id}` end point returns pet with id

  Scenario: Application inventory end-point
    Given the application is running
    When I check the store inventory
    Then the API should return valid JSON
