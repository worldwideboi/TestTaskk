package playercontroller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import dto.PlayerGetByPlayerIdRequestDto;
import dto.PlayerGetByPlayerIdResponseDto;
import io.restassured.RestAssured;

import java.util.function.Function;

public class PostController extends BaseController {
    protected static final String POST_BY_ID = BASE_URI + "/player/get/";

    protected Response postRequest(String uri, Object body) {
        Response response = requestSpecification.body(body).expect().log().ifError()
                .when().get(POST_BY_ID);
        return response;
    }

    public Response getPlayerById(int playerId) {
        Response response = RestAssured.given().log().all()
                .when().body(playerId).post(POST_BY_ID);
        return response;
    }
}
