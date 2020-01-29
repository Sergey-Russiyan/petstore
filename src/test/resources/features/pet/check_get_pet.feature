  Feature: pet endpoint

  The `/pet/{petId}` provides pet by id

    @issue
    Scenario: Application delete pet
      Given the application is running
      When I delete pet with id "555"
      Then the API should return response with status code 200

    Scenario: Application post pet
      Given the application is running
      When I post pet with id "555" name "sobaken" status "sold"
      Then the API should return response with status code 200

    Scenario: Application get pet by id
      Given the application is running
      When I check pet with id "555"
      Then the API should return response with status code 200
      And the API should return pet with id "555"