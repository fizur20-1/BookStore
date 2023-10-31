# BookStore-NITEX
Project Name: Simple Book Management System  Objective: Develop a RESTful web service using Spring Boot that enables users to manage a collection of books.


Steps to Run the Application:

Configure Database:

Ensure that you have a database configured. You can specify the database properties in your application.properties or application.yml file.
Create Entity Classes:

You have already created entity classes such as User and Book. These classes represent the data model for your application.
Create Repository Interfaces:

You have created repository interfaces for your entities (e.g., UserRepository, BookRepository) that extend JpaRepository`.
Create Service Classes:

Implement service classes for your entities (e.g., UserService, `BookService) to encapsulate business logic.
Configure JWT:

Create a JwtConfig class in your config package to configure JWT settings.
Implement JWT Authentication:

Create a JwtUtil class to handle JWT token generation, parsing, and validation.
Create a JwtRequestFilter class to intercept incoming requests and validate JWT tokens.
Configure Spring Security:

Configure Spring Security to require authentication for specific endpoints.
Add the JwtRequestFilter to the security filter chain.
Implement Controllers:

Create controllers for your entities (e.g., UserController, `BookController) to handle HTTP requests.
Run the Application:

Run your Spring Boot application using your preferred method (e.g., via an IDE, Gradle, or Maven).
Basic Documentation of the API:

Here's some basic documentation for your API, including endpoints and request/response formats. You can expand and refine this documentation as needed:

User Management Endpoints:

Register a New User:

URL: POST /users/register
Request:
Content-Type: application/json
Body: User details (username, password, fullName, email)
Response:
HTTP Status: 201 Created
Body: Newly created user details (excluding password)
Authenticate a User:

URL: POST /users/login
Request:
Content-Type: application/json
Body: User credentials (username, password)
Response:
HTTP Status: 200 OK
Body: JWT token
Retrieve User Profile:

URL: GET /users/profile
Request:
Authorization Header: Bearer Token (JWT)
Response:
HTTP Status: 200 OK
Body: User profile details (excluding password)
Update User Profile:

URL: PUT /users/profile
Request:
Authorization Header: Bearer Token (JWT)
Content-Type: application/json
Body: Updated user details (fullName, email)
Response:
HTTP Status: 200 OK
Body: Updated user profile details (excluding password)
Book Management Endpoints:

Retrieve All Books:

URL: GET /books
Request:
Authorization Header: Bearer Token (JWT) if authentication is required
Response:
HTTP Status: 200 OK
Body: List of books
Retrieve Book by ID:

URL: GET /books/{id}
Request:
Authorization Header: Bearer Token (JWT) if authentication is required
Response:
HTTP Status: 200 OK
Body: Book details
Create a New Book:

URL: POST /books
Request:
Authorization Header: Bearer Token (JWT) if authentication is required
Content-Type: application/json
Body: Book details (title, author, description)
Response:
HTTP Status: 201 Created
Body: Newly created book details
Update Book by ID:

URL: PUT /books/{id}
Request:
Authorization Header: Bearer Token (JWT) if authentication is required
Content-Type: application/json
Body: Updated book details (title, author, description)
Response:
HTTP Status: 200 OK
Body: Updated book details
Delete Book by ID:

URL: DELETE /books/{id}
Request:
Authorization Header: Bearer Token (JWT) if authentication is required
Response:
HTTP Status: 204 No Content
This documentation provides an overview of your API endpoints, their URLs, expected request formats, and sample responses. You can further expand this documentation by specifying additional details about error handling, authentication requirements, and any other relevant information.
