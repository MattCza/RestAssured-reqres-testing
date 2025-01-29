import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SelfHostedApiTests {

    private String apiUsersPath = "/users";
    private String apiDepartmentsPath = "/departments";
    private int departmentTesting = 1;
    private int departmentDev = 2;
    private int departmentManager = 3;
    private String lastUserID;

    @BeforeClass()
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @BeforeClass(dependsOnMethods = "setUp")
    public void fetchLastUserId() {
        System.out.println("Fetching the last user ID before running tests...");

        Response response = io.restassured.RestAssured.get(apiUsersPath);
        List<String> userIds = response.jsonPath().getList("id");

        if (!userIds.isEmpty()) {
            lastUserID = userIds.getLast(); // Get last element
            System.out.println("Last User ID: " + lastUserID);
        } else {
            throw new RuntimeException("No users found in the API response.");
        }
    }

    @Test
    public void getIsStatusCode200() {
        get()
                .then()
                .statusCode(200)
                .log().status();
    }

    @Test
    public void getAllUsers() {
        get(apiUsersPath)
                .then().log().body();
    }

    @Test
    public void getIs2ndUserNotMark() {
        get(apiUsersPath + "/2")
                .then()
                .body("firstName", not(equalTo("Mark")))
                .log().body();
    }

    @Test
    public void getUserNotFound() {
        get(apiUsersPath + "/2112")
                .then()
                .statusCode(404)
                .log().status();
    }

    @Test
    public void postAddNewUser() {
        JSONObject request = new JSONObject();

        int incrementedId = Integer.parseInt(lastUserID) + 1;
        lastUserID = String.valueOf(incrementedId);

        request.put("id", lastUserID);
        request.put("firstName", "Thomas");
        request.put("lastName", "Edison");
        request.put("age", 18);
        request.put("departmentId", departmentManager);

        given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(apiUsersPath)
                .then()
                .statusCode(201).log().body();
    }

    @Test
    public void deleteUserByLastID() {
        given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(apiUsersPath + "/" + lastUserID)
                .then()
                .statusCode(200).log().body();

        int incrementedId = Integer.parseInt(lastUserID) - 1;
        lastUserID = String.valueOf(incrementedId);
    }

    @Test
    public void validateUserSchema() {
        get("/users/1")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }




}
