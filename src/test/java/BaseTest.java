import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    protected String REQRES_URL = "https://reqres.in/api";
    protected String LOCAL_URL = "http://localhost:3000";


    @BeforeClass
    public void setUp() {
        baseURI = LOCAL_URL;
    }

}