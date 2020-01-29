  Feature: User can update pet

  The POST `/pet/{petId}` provides pet by id

    Scenario: Application post pet
      Given the application is running
      When I post pet with id "555" name "sobaken" status "sold"
      Then the API should return response with status code 200