  Feature: Update existing user

  The PUT `/user/{username}` end allows to update existing user

#    @issue #always return 500 when user are valid
  Scenario: Update existing user
    Given the application is running
    When I update existing user "qwe"
    Then the API should return response with status code 200
    # TODO  add  more advanced response verify added, but i not receive any response except 500 (issue/bug)