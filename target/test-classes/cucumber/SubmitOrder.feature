
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page

 
  @tag2
  Scenario Outline:Positive Test of submitiing the order
    Given Logged in with username<username> and password <password>
    When I add product <productName> to Cart
    And Checkout<productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
    Examples: 
      | username             | password   | productName    |
      | example007@gmail.com | Example@123| ADIDAS ORIGINAL |
      
