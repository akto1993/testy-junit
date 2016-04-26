Scenario: Test Practiceform page

Given user is on Home page
When user click some link
Then some page is shown

When user click Submit
Then This field is required alert validator is shown

When user types 1234 in field
Then msg validation show Please enter no more than 2 characters.

When user types 12 in field
Then user pass a validation

When user click alert button
Then alert is shown

When user click on /a/span/ link
Then create PrSc 