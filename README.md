# 📌 API Testing with Rest Assured  

## 📑 Table of Contents  

1. [🎯 Objective](#-objective)  
2. [🛠 Tools & Technologies Used](#-tools--technologies-used)  
3. [🔗 API Endpoints Tested](#-api-endpoints-tested)  
4. [✅ Test Scenarios](#-test-scenarios)  
   - [🟢 Status Code Verification](#-status-code-verification)  
   - [🔍 Fetching User Data](#-fetching-user-data)  
   - [➕ Adding a New User](#-adding-a-new-user)  
   - [🗑 Deleting a User](#-deleting-a-user)  
5. [🔍 JSON Schema Validation](#-json-schema-validation) 
6. [📌 Example Request](#-example-request)  
7. [🚀 Next Steps](#-next-steps)  
8. [⚙️ Running the Local API Server](#️-running-the-local-api-server)  

---

## 🎯 Objective  
The purpose of this module is to test API endpoints using **Rest Assured** to validate their functionality, response codes, and data integrity. The tests cover **GET, POST, and DELETE** requests to verify that the API behaves as expected.  

🚀 The API server used for testing is hosted **locally** using **JSON Server**.  

---

## 🛠 Tools & Technologies Used  
- **Rest Assured** – for API testing  
- **TestNG** – for test execution  
- **JSON Simple** – for request body creation  
- **Hamcrest Matchers** – for response validation  
- **JSON Server** – for running a mock API locally
- **JSON Schema Validator** – for validating API responses  

---

## 🔗 API Endpoints Tested  
- **GET** `/users` – Fetch all users  
- **GET** `/users/{id}` – Fetch a user by ID  
- **POST** `/users` – Add a new user  
- **DELETE** `/users/{id}` – Remove a user  

---

## ✅ Test Scenarios  

### 🟢 Status Code Verification  
✔️ Ensures that the API returns **200 OK** for valid requests.  
✔️ Verifies that invalid requests return expected errors (e.g., **404 Not Found**).  

### 🔍 Fetching User Data  
✔️ Retrieves all users and logs the response.  
✔️ Fetches a specific user and **verifies that it is not named "Mark"** (data validation).  

### ➕ Adding a New User  
✔️ Generates a **unique user ID** dynamically.  
✔️ Sends a **POST** request to add a new user with predefined details.  
✔️ Asserts that the API returns **201 Created** upon success.  

### 🗑 Deleting a User  
✔️ Sends a **DELETE** request to remove the last added user.  
✔️ Confirms that the deletion was successful (**200 OK**).  
✔️ Updates `lastUserID` to ensure test consistency.  

---
## 🔍 JSON Schema Validation
To ensure API responses follow the expected format, JSON Schema validation is implemented using matchesJsonSchemaInClasspath().  
```
   @Test
   public void validateUserSchema() {
    get("/users/1")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
}
```
The schema file (user-schema.json) should be located in src/test/resources/schemas/.   
Content of the user-schema.json file:  
```
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "id": { "type": "string" },
    "firstName": { "type": "string" },
    "lastName": { "type": "string" },
    "age": { "type": "integer" },
    "departmentId": { "type": "string" }
  },
  "required": ["id", "firstName", "lastName", "age", "departmentId"]
}

```


---
## 📌 Example Request
### **POST /users – Adding a New User**  

📩 **Request Body:**  
```json
{
  "id": "101",
  "firstName": "Thomas",
  "lastName": "Edison",
  "age": 18,
  "departmentId": 3
}
```
## 🚀 Next Steps  

✅ Expand test coverage with more complex assertions.  
✅ Implement parameterized tests for dynamic data validation.  
✅ Integrate tests with CI/CD pipelines to automate execution.  
🔄 Add PUT requests to update user data and validate responses.  
🛠 Extend JSON Schema & XML validation for more structured testing.  


## ⚙️ Running the Local API Server  

### To run the JSON Server locally:

Install json-server if not already installed:

    npm install -g json-server

Start the server with the database file:

    npx json-server db.json

The API will be available at http://localhost:3000
