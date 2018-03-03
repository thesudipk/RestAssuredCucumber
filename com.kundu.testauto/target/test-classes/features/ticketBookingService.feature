Feature: Testing Rest Webservice 
@test
Scenario Outline: Data retrieval from a ticket bookin webservice 
	When user wants to get info by passing content type is"<content_type>"  
	And base url "<baseurl>" 
	Then expected status is "<status>" 
	
	Examples: 
	
		| baseurl | content_type | status |
		|ticket/booking | json | 201 |