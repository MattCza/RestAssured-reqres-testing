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
   - [📊 Posting Users from Excel](#-posting-users-from-excel)
   - [🧹 Cleaning Up Imported Data](#-cleaning-up-imported-data)
5. [🔍 JSON Schema Validation](#-json-schema-validation)
6. [⚙️ Test Execution Order with @Priority](#️-test-execution-order-with-priority) 
7. [📌 Example Request](#-example-request)  
8. [🚀 Next Steps](#-next-steps)  
9. [⚙️ Running the Local API Server](#️-running-the-local-api-server)  

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
- **Apache POI** – for reading and writing Excel files

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

### 📊 Posting Users from Excel  
✔️ Reads user data from an Excel file.  
✔️ Dynamically generates unique user IDs.  
✔️ Posts users to the API using data-driven testing.  

### 🧹 Cleaning Up Imported Data  
✔️ Deletes all users that were **imported from the Excel file** to maintain data consistency.  
✔️ Ensures that the test environment remains clean after execution.  

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

## ⚙️ Test Execution Order with @Priority

To ensure that every test method executes in the correct order, the @Test(priority = X) annotation is used.

✔️ GET requests run first to check API status and fetch data.
✔️ POST requests execute after validation steps, ensuring new data is created properly.
✔️ DELETE requests run last to clean up the test environment.
Example:  

```
@Test(priority = 0)
public void getIsStatusCode200() { 
    get().then().statusCode(200).log().status();
}

@Test(priority = 1)
public void getAllUsers() { 
    get(apiUsersPath).then().log().body();
}

@Test(priority = 4)
public void postAddNewUser() { 
    // Adds a new user to the database
}

@Test(priority = 7)
public void deleteUserImportedFromExcelFile() { 
    // Deletes users imported from Excel to clean the environment
}
```

By structuring tests with priorities, we make sure:  
✅ API is running before modifying data.  
✅ POST requests do not run before necessary checks.  
✅ DELETE methods execute only after data has been inserted.  




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
✅ Enhance Excel data reading by supporting multiple sheets & dynamic input.  
✅ Improve data cleanup mechanisms for better test environment stability.  
✅ Continue leveraging priority annotations to refine test execution order.    
🔄 Add PUT requests to update user data and validate responses.  
🛠 Extend JSON Schema & XML validation for more structured testing.  


## ⚙️ Running the Local API Server  

### To run the JSON Server locally:

Install json-server if not already installed:

    npm install -g json-server

Start the server with the database file:

    npx json-server db.json

The API will be available at http://localhost:3000
