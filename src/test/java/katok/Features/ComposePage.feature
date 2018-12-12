@All_Tests
@ComposePage_Tests

Feature: Test send letter from mail.ru

  Scenario: Send letter to one adressee
    Given I am logged in https://mail.ru/ page
    When I click button Написать письмо
    When I enter adresse to Кому line
    When I enter Subject to Тема line
    When I enter message text to e-mail body field
    When I click Отправить button
    Then I see confirmation message

  Scenario: Send letter to one adressee and information message to VK
    Given I am logged in https://mail.ru/ page
    When I click button Написать письмо
    When I enter adresse to Кому line
    When I enter Subject to Тема line
    When I enter message text to e-mail body field
    When I click Отправить button
    When I send confirmation message via VK API
    Then I receive confirmation from API

  Scenario: Send letter to one adressee
    Given I am logged in https://mail.ru/ page
    When I click button Написать письмо
    When I enter all of adresses to Кому line
    When I enter Subject to Тема line
    When I enter message text to e-mail body field
    When I click Отправить button
    Then I see confirmation message