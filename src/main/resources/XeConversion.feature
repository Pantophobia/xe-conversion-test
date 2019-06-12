Feature: As a user I can convert values between two currencies

  Scenario Outline: As a user I can convert "<FROM_CURRENCY>" to "<TO_CURRENCY>"
    Given I am on the XE homepage
    When I convert "<AMOUNT>" from "<FROM_CURRENCY>" to "<TO_CURRENCY>"
    Then the result is correct
    Examples: of Conversion options
      | FROM_CURRENCY | TO_CURRENCY | AMOUNT |
      | EUR           | GBP         | 10.11  |
#      | GBP           | USD         | 10.22  |
#      | JPY           | INR         | 10.33  |
#      | CNY           | GBP         | 10.44  |
#      | KES           | GBP         | 10.55  |