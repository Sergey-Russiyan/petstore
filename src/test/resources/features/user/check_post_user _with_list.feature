Feature: Post new list of users

  The POST `/user/createWithList` end allows to create list of users

#  @issue #always return 500 status
  Scenario: Post new list of users with all valid data filled
    Given the application is running
    When I post list of valid users 4
    Then the API should return response with status code 200
    # TODO  add  more advanced response verify added, but i not receive any response except 500 (issue/bug)
