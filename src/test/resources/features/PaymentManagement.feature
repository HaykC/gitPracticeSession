@paymentTests @Regression @paymentManagement
Feature: Payments page

Background:
   Given As an entity user, I am logged in
   
   @addPaymentButtonVerification
   Scenario: Verifying Add Payment button on payments tab
   When I navigate to "Payments" tab
   Then I should see "Add Payment" button displayed
   