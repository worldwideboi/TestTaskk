package playercontroller;

import io.restassured.response.Response;

import java.util.function.Function;

public class PatchController extends BaseController {
    public static final String BASE_PATCH_URL = BASE_URI + "/player/update/{editor}/{id}";

    protected Response patchRequest(String uri, Object body) {
        Response response = requestSpecification.body(body).expect().log().ifError()
                .when().patch(BASE_PATCH_URL);
        return response;
    }
}
