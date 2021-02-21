@RegressionSuite
Feature: OpenWeather API

@StationValidation
Scenario Outline: To check the response upon registering station without api key
Given Setting up Rest APi ELements like "<baseURI>","<endPoint>","<apiKey>","<city>"
When Calling  Weather API to register to the station
Then Verify the error response

Examples:
|baseURI|endPoint|apiKey|city|
|baseURI|endPoint|apiKey|city1|


