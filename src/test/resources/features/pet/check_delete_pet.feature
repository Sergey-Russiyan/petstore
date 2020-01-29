Feature: User can delete pet

  The DELETE `/pet/{petId}` can delete pet by id

  @issue
  Scenario: Application delete pet
    Given the application is running
    When I delete pet with id "555"
    Then the API should return response with status code 200