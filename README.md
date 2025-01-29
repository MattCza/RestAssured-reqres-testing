#🛠️ API Testing with Rest Assured
##🎯 Objective

The purpose of this module is to test API endpoints using Rest Assured to validate their functionality, response codes, and data integrity. 
The tests cover GET, POST, and DELETE requests to verify that the API behaves as expected.

🚀 The API server used for testing is hosted locally using JSON Server.

## 🛠 Tools & Technologies Used

    Rest Assured – for API testing
    TestNG – for test execution
    JSON Simple – for request body creation
    Hamcrest Matchers – for response validation
    JSON Server – for running a mock API locally

## 🔗 API Endpoints Tested

    GET /users – Fetch all users
    GET /users/{id} – Fetch a user by ID
    POST /users – Add a new user
    DELETE /users/{id} – Remove a user

## ✅ Test Scenarios
🟢 Status Code Verification

✔️ Ensures that the API returns 200 OK for valid requests.
✔️ Verifies that invalid requests return expected errors (e.g., 404 Not Found).
🔍 Fetching User Data

✔️ Retrieves all users and logs the response.
✔️ Fetches a specific user and verifies that it is not named "Mark" (data validation).
➕ Adding a New User

✔️ Generates a unique user ID dynamically.
✔️ Sends a POST request to add a new user with predefined details.
✔️ Asserts that the API returns 201 Created upon success.
🗑 Deleting a User

✔️ Sends a DELETE request to remove the last added user.
✔️ Confirms that the deletion was successful (200 OK).
✔️ Updates lastUserID to ensure test consistency.
📌 Example Request & Response
POST /users – Adding a New User

## 📩 Request Body:

{
  "id": "101",
  "firstName": "Thomas",
  "lastName": "Edison",
  "age": 18,
  "departmentId": 3
}

## 📨 Expected Response:

{
  "message": "User added successfully"
}

## 🚀 Next Steps

✅ Expand test coverage with more complex assertions.
✅ Implement parameterized tests for dynamic data validation.
✅ Integrate tests with CI/CD pipelines to automate execution.
✅ Add PUT requests to update user data and validate responses.
🔄 Implement JSON Schema Validation for request/response verification.
🌍 Extend tests to run in multiple environments (local, staging, production).
