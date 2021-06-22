Feature: Create Accounts


Scenario: Create two accounts
Given Home page is displayed
When User moves to Service
And User click in Accounts
And Click in new
And User set all the required fields and click save and new
Then second form should be visible and filled click save and both account will be successfully created





Scenario: Error creating account empty
Given Home page is displayed
And User moves to Service
And User click in Accounts
And Click in new
When User try to save it without filling the fields required and an error should be displayed
Then The error is displayed and click cancel in the form





Scenario: Modificar campos de la ultima cuenta
Given Home page is displayed
And User moves to Service
And User click in Accounts
And User click in last account little arrow and click modify
When User modifies Values Type and Upsell fields and click save
Then Validate the changes comparing the old values with the new






Scenario: Modificar campo empleados de la ultima cuenta
Given Home page is displayed
And User moves to Service
And User click in Accounts
And User click in last account little arrow and click modify
When User enters 1431655766 in Employee field and click save
Then It should give an specific error which we will validate






