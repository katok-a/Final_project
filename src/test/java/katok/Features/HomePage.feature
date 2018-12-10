@All_Tests
@MainPage_Tests

  Feature: Test login to mail.ru account with different credentials
    Scenario: Login using incorrect credentials
      Given I am on https://mail.ru/ page
      When I enter incorrect UserName to Login field
      When I enter incorrect Password to Password field
      When I click Войти
      Then I see Error message

    Scenario: Login using correct credentials
      Given I am on https://mail.ru/ page
      When I enter correct UserName to Login field
      When I enter correct Password to Password field
      When I choose correct domain name from dropdown
      When I click Войти
      Then I see Inbox page


