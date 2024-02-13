package playercontroller;

import dto.PlayerGetByPlayerIdRequestDto;
import dto.PlayerGetByPlayerIdResponseDto;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import dto.PlayerCreateResponseDto;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GetController extends BaseController {

   public static final String BASE_GET_URL = BASE_URI + "/player/create/{editor}";
   public static final String BASE_GET_ALL_URL = BASE_URI + "/player/get/all";

    public GetController() {
        super();
        setCommonParams();
    }

    public PlayerCreateResponseDto getCreateRequest(PlayerCreateResponseDto parameters, int expectedStatusCode) {
        Response response = this.requestSpecification
                .pathParam("editor", "supervisor")
                .queryParam("age", parameters.getAge())
                .queryParam("gender", parameters.getGender())
                .queryParam("login", parameters.getLogin())
                .queryParam("password", parameters.getPassword())
                .queryParam("role", parameters.getRole())
                .queryParam("screenName", parameters.getScreenName())
                .when()
                .get(GetController.BASE_GET_URL)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response.as(PlayerCreateResponseDto.class);
    }
}