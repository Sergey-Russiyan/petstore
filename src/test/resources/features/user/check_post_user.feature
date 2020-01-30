  Feature: Post new single user

  The POST `/user` end allows to create 1 new user per request

#    @issue #always return 500 status
  Scenario: Post new user with all valid data filled
    Given the application is running
    When I post new valid user
    Then the API should return response with status code 200
    # TODO  add  more advanced response verify added, but i not receive any response except 500 (issue/bug)
