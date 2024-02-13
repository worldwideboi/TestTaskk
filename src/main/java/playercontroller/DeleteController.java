package playercontroller;

import io.restassured.response.Response;

import java.util.function.Function;

public class DeleteController extends BaseController {
    public static final String DELETE_BY_ID = BASE_URI + "/player/delete/{editor}";

    protected Response deleteById(String uri) {
        Response response =  requestSpecification.expect().log().ifError()
                .when().delete(DELETE_BY_ID);
        return response;
    }

    public DeleteController() {
        super();
        setCommonParams();
    }
}
