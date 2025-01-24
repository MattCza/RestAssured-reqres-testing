import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    protected String DEMOQA_URL = "https://demoqa.com/";


    @BeforeClass
    public void setUp(){
        baseURI = "https://reqres.in/api";
    }


}