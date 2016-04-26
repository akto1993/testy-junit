Scenario: Test Practiceform page

Given user is on Home page
When user click some link
Then some page is shown

Given user is on Home page
When user click Submit
Then This field is required alert validator is shown

Given user is on Home page
When user types 12 in field
Then user pass a validation

Given user is on Home page
When user click alert button
Then alert is shown

When user click on /a/span/ link
Then create PrSc

Given user is on Home page
When user types 1111 in field
Then msg validation show Please enter no more than 2 characters.