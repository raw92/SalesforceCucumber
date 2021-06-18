Feature: Create Accounts


Scenario: Create two accounts
Given Initialize the browser with chrome and goes to Salesforce landing page
And User login into app with username and password
And Home page is displayed
And User moves to Service
And User click in Accounts
And Click in new
When User set all the required fields and click save and new
And A new form should be visible and start to fill the fields again and click save
Then Move to accounts
And Close browser



Scenario: Error creating account empty
Given Initialize the browser with chrome and goes to Salesforce landing page
And User login into app with username and password
And Home page is displayed
And User moves to Service
And User click in Accounts
And Click in new
When User try to save it without filling the fields required and an error should be displayed
Then The error is displayed and click cancel in the form
And Close browser



Scenario: Modificar campos de la ultima cuenta
Given Initialize the browser with chrome and goes to Salesforce landing page
And User login into app with username and password
And Home page is displayed
And User moves to Service
And User click in Accounts
And User click in last account little arrow and click modify
When User modifies Value, Type and Upsell fields and click save
Then Validate the changes comparing the old values with the new
And Close browser



Scenario: Modificar campo empleados de la ultima cuenta
Given Initialize the browser with chrome and goes to Salesforce landing page
And User login into app with username and password
And Home page is displayed
And User moves to Service
And User click in Accounts
And User click in last account little arrow and click modify
When User enters 1431655766 in Employee field and click save
Then It should give an specific error which we will validate
And Close browser





