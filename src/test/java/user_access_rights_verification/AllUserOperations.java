package user_access_rights_verification;

import dto.PlayerCreateResponseDto;
import dto.PlayerUpdateRequestDto;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playercontroller.GetController;
import playercontroller.PatchController;
import playercontroller.PostController;

import static utils.RandomDataCreation.*;

public class AllUserOperations {
    @BeforeClass
    public static PlayerCreateResponseDto getPlayer() {
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();
        requestDto.setAge(22);
        requestDto.setGender("female");
        requestDto.setLogin(generateRandomLogin());
        requestDto.setPassword(generateRandomPassword());
        requestDto.setRole("user");
        requestDto.setScreenName(generateRandomScreenName());
        return getController.getCreateRequest(requestDto,200);
    }

    @Test(testName = "User creation")
    public void testGetController() {
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();

        requestDto.setAge(22);
        requestDto.setGender("female");
        requestDto.setLogin(requestDto.getLogin());
        requestDto.setPassword(requestDto.getPassword());
        requestDto.setRole("user");
        requestDto.setScreenName(requestDto.getScreenName());

        PlayerCreateResponseDto response1 = getController.getCreateRequest(requestDto,200);
    }

    @Test(testName = "Get information about user by id")
    public void testPostRequest() {
        PostController post = new PostController();
        post.setCommonParams();
        Response receivedPlayer = post.getPlayerById(getPlayer().getId());
    }

    @Test(testName = "Update user's data")
    public void testPatchRequest() {
        PatchController patch = new PatchController();
        patch.setCommonParams();
        PlayerUpdateRequestDto updateRequest = new PlayerUpdateRequestDto();

        updateRequest.setAge(getPlayer().getAge());
        updateRequest.setGender(getPlayer().getGender());
        updateRequest.setLogin(getPlayer().getLogin());
        updateRequest.setPassword(getPlayer().getPassword());
        updateRequest.setRole(getPlayer().getRole());
        updateRequest.setScreenName("newName");

        Response response = patch.requestSpecification
                .pathParam("editor", "supervisor")
                .pathParam("id", getPlayer().getId())
                .body(updateRequest)
                .when()
                .patch(PatchController.BASE_PATCH_URL)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }
    @Test(testName = "Get information about all users")
    public void testGetAll() {
        GetController getAll = new GetController();
        getAll.setCommonParams();

        Response response = getAll.requestSpecification
                .when()
                .get(GetController.BASE_GET_ALL_URL)
                .then()
                .statusCode(200)
                .extract().response();
    }
}
