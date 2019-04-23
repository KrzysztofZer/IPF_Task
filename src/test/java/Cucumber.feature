Feature: Making transactions

  Scenario: Withdraw more money then there is in account
    When there is 200PLN in account
    Then want to withdraw 500 PLN from account
    Then check error message
    And check how much money you have withdraw
    And check how much money is on account

  Scenario Outline: Check correct withdraw for few amounts
    When there is 200PLN in account
    Then want to withdraw "<amount>" PLN
    Then check if you have withdraw "<amount>" PLN
    And check if there is "<amount_after_withdraw>" PLN in account after withdraw

    Examples:
    |amount|amount_after_withdraw|
    |0     |200                  |
    |50    |150                  |
    |100   |100                  |
    |150   |50                   |
    |200   |0                    |