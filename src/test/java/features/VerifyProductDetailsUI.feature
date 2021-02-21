@RegressionSuite
Feature: Verify the product details

@verifyproductdetails
Scenario Outline:To verify if all prodcut detaila are correct
Given Launch "<url>" automation practice website
When USer Enter the credentials as "<USerid>" and "<password>"
And Select the category "<Category>","<product>" and add to cart and proceed to checkout
Then verify the message "<message>" and other product details such as product name,color,quantity and totoal price

Examples:
|url|USerid|password|Category|product|message|
|url|jetblue@grr.la|jetblue|T-Shirts|Faded Short Sleeve T-shirts|message|

