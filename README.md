# Getting Started
Copy project and open in IDE(taking example of eclipse or STS)
do mvn clean install to install all the dependencies in the environment

server port is mentioned as 8080 in application.properties file (If need to change port, change in this file)

using H2 database for book storage (username and password are part of application.properties file itself. I have removed the password before submission but left username as default one)

created a Response.java class for custom response by rest api endpoints

endpoints:
1. add book(POST Request) ->http://localhost:8080/lms/books	
				Request Body : 					
{
	"title": "The Rainbow",
	"author":"M J Naik",
	"genre":"comedy"
}

2. search books(GET Request) -> http://localhost:8080/lms/books

3. search books based on query parameters (GET Request) -> http://localhost:8080/lms/books?title=rainbow 
										 http://localhost:8080/lms/books?title=rainbow&genre=comedy
										 http://localhost:8080/lms/books?title=shi&borrowed=true
						 
										 
4. borrow a book(PATCH Request) -> http://localhost:8080/lms/books/{book_id}	
			Request Body : 					
{
	"isBorrowed": true,
    "borrowedBy": "shivani"
}							 


This solution is a bare minimum implementation of required features and can be improved using user authentication and authorization modules, implementation of handling multiple copies of books, returning a book, Pagination and more.