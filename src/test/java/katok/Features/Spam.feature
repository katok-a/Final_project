@All_Tests
@Spam_Tests

Feature: Test send letter from mail.ru

  Scenario: Send letter to spam
    Given I am logged in https://mail.ru/ page
    When I check email at Inbox
    When I click Спам
    Then I see Добавлено в спам message

  Scenario: Remove letter from spam
    Given I am logged in https://mail.ru/ page
    When I go spam page
    When I check email at Spam
    When I click Не спам
    Then I see Письмо перемещено в папка Входящие


