  Feature: Check on swagger application status

  The `base url` provides bunch of endpoints available to manual api testing.

  Scenario: Application status end-point
    Given the application is running
    When I check the application status
    Then the API should return response which contains "<title>Swagger UI</title>"
