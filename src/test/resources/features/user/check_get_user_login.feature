  Feature: Check on inventory status

  The `/store/inventory` end point returns inventory .

  Scenario: Application user login with valid credentials
    Given the application is running
    When I check user login with valid credentials:
      | login   | password  |
      | name    | 123       |
      | 123     | asd       |
      | -!@#!@$ | '""'      |
    Then the API should return response which matches "logged in user session:\d{13}"
    And the API should return header "X-Rate-Limit" with value "5000"
    And the API should return header "X-Expires-After" which matches "\w{3}\s\w{3}([\s|:]\d{2}){4}\sUTC\s20[\d]{2}"