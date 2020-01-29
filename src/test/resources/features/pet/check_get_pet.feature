Feature: USre can get pet

  The GET `/pet/{petId}` provides pet by id

  Scenario: Application get pet by id
    Given the application is running
    When I check pet with id "555"
    Then the API should return response with status code 200
    And the API should return pet with id "555"