import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestTest extends BaseTest{

    private String apiGetListUsersPath = "/users?page=2";
    private String apiGetUserNotFoundPath = "/users/23";

    @Test
    public void getRequestIsStatusCode200() {
        get(apiGetListUsersPath).then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void getRequestIs3rdUsersID9andNameTobias() {
        get(apiGetListUsersPath).then()
                .body("data[2].id", equalTo(9));
        get(apiGetListUsersPath).then()
                .body("data[2].first_name", equalTo("Tobias"))
                .log().body();
    }

    @Test
    public void getRequestUserNotFound() {
        get(apiGetUserNotFoundPath).then()
                .statusCode(404)
                .log().status();
    }

    @Test
    public void postRequest() {
        JSONObject request = new JSONObject();
        request.put("name", "Isaac");
        request.put("job", "Scientist");
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when().post("/users")
                .then().statusCode(201).log().status();

    }





}
