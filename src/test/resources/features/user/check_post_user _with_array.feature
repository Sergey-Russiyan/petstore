Feature: Post new array of users

  The POST `/user/createWithArray` end allows to create array of users

#  @issue #always return 500 status
  Scenario: Post new array of users with all valid data filled
    Given the application is running
    When I post array of valid users 3
    Then the API should return response with status code 200
    # TODO  add  more advanced response verify added, but i not receive any response except 500 (issue/bug)
