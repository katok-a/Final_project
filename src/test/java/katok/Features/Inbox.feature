@All_Tests
@Inbox_Tests

  Feature: Test logout mechanics
    Scenario: Logout from inbox page
      Given I am logged in https://mail.ru/ page
      When I click выход button
      Then I see login form


