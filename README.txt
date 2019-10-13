This is a Java 8 web application that uses Java Servlets and Tomcat (8.5) server and Maven for dependency management.

This application provides the backend services only (no UI), 
which are accessible by direct query to the API endpoint via Postman or other testing tool.
An example of a request is as follows:
POST request url: http://localhost:8080/CreditCardValidator/validate
Request body: {"number":"4788384538552446","expiration": "11/21"}
Response: 'Card Invalid' message or Credit Card object

The service layer of this application will remove any spaces in the card number String.
It does not check whether the only spaces are every 4 digits.
It also checks whether the card number String (after spaces are removed) has 16 characters 
and whether the first characters meet the requirement for Visa/MasterCard.
The card number is also validated against the Luhn formula.
The expiration date is validated to be the correct String format, and that it is a date in the future.

One limitation of this application is that there is no handling of incorrect request objects other
than to return a 500 error, for example if either card number or expiration date is not passed in the request.
Another limitation is that any blacklisted card numbers are hard-coded in the application service layer.
It would be better to store and retrieve these blacklisted numbers in a database so they can be persisted more dynamically.
