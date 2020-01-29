  Feature: Update existing user

  The PUT `/user/{username}` end allows to update existing user

  Scenario: Update existing user
    Given the application is running
    When I update existing user "qwe"
    Then the API should return response with status code 200
