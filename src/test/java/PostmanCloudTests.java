import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostmanCloudTests {

    @BeforeClass
    public void setupPostmanServer() {
        baseURI = "https://postman-echo.com";  // Postman Mock Server URL
    }

    @Test
    public void postLoginWithCorrectCredentials() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200).log().status();
    }

    @Test(priority = 1)
    public void postLoginWithWrongCredentials() {
        given()
                .auth().basic("postman", "nope")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(401).log().status();
    }

    @Test(priority = 2)
    public void postLoginWithDigestCorrectCredentials() {
        given()
                .auth().digest("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200).log().status();
    }


}
