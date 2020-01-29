  Feature: User log out from application

  The `/user/logout` end point log ou user from app

  Scenario: Application user logout
    Given the application is running
    When I check user logout
    Then the user should be log out from application