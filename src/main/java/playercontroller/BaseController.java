package playercontroller;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
    public static final String BASE_URI = "http://3.68.165.45";
    public RequestSpecification requestSpecification;

    public void setCommonParams() {
        requestSpecification = RestAssured.given().log().all();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }
}
