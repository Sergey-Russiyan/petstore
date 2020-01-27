Feature: Get Pet with ID

  Scenario: Each pet has a unique ID
    Given the following trade:
    | security | buySell | quantity | priceInCents |
    | APPL     | BUY     | 10       | 10000        |
    When I record the trade
    Then the recorded trade should include the following details:
      | security | buySell | quantity | priceInCents | totalCostInCents |
      | APPL     | BUY     | 10       | 10000        | 100000           |


