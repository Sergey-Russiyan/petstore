  Feature: Delete existing user

  The delete `/user/{username}` end allows to delete user by username

  Scenario: Delete user with valid name
    Given the application is running
    When I delete user with name "Kevin Backspacey"
    Then the API should return response with status code 200

  Scenario: Delete user with invalid name
    Given the application is running
    When I delete user with name ""
    Then the API should return response with status code 405