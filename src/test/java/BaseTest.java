import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;

public class BaseTest {
    protected String REQRES_URL = "https://reqres.in/api";
    protected String LOCAL_URL = "http://localhost:3000";


    @BeforeClass
    public void setUp(){
        baseURI = LOCAL_URL;
    }

}