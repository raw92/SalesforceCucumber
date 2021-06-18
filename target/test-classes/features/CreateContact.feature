Feature: Create Contact


Scenario: Create a contact
Given Initialize the browser with chrome and goes to Salesforce landing page
And User login into app with username and password
And Home page is displayed
And User moves to Service
And Grab the last created account name to use it for the contact name
When Goes to contact and click new it should use the name we grab before and save the contact
And Close browser


