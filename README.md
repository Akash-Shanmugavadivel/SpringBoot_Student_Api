SpringBoot_Student_Api

A Spring Boot RESTful API for managing student data, including profiles, courses, marks, and fees. This project allows CRUD operations, filtering of students, and provides documentation and testing via Swagger UI.

 Features
-> CRUD Operations: Create, Read, Update, Delete student profiles.
-> Filtering: Filter students by course, fees paid, or marks range.
-> Swagger UI: Interactive documentation and testing interface.
-> RESTful Endpoints: Organized API endpoints for ease of access.

 Technology Stack
-> Java: Programming language
-> Spring Boot: Framework for building REST APIs
-> Maven: Dependency management
-> Swagger/OpenAPI: API documentation
-> H2 Database: In->memory database (can be replaced with other databases)

 Build the Project:
->mvn clean install

 Run the Application:
->mvn spring-boot:run

The application will start on http://localhost:8080

Swagger UI
http://localhost:8080/swagger-ui.html


Endpoints
Get All Students: GET /api/students
Get Student by ID: GET /api/students/{id}
Add a New Student: POST /api/students
Update a Student: PUT /api/students/{id}
Delete a Student: DELETE /api/students/{id}
Get Top Rankers: GET /api/students/top-rankers?n={number}
Filter by Marks Range: GET /api/students/marks?min={min}&max={max}

Json to adding and updating students

for testing 
->Postman
->Swagger Ui
->Web browser for get
