Feature: Application Login


Scenario: Home page default login
Given Initialize the browser with chrome and goes to Salesforce landing page
When User login into app with username and password
Then Home page is displayed
And Close browser