package admin_access_rights_verification;

import dto.PlayerCreateResponseDto;
import dto.PlayerDeleteRequestDto;
import dto.PlayerUpdateRequestDto;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playercontroller.DeleteController;
import playercontroller.GetController;
import playercontroller.PatchController;
import playercontroller.PostController;

import static utils.RandomDataCreation.*;

public class AllAdminOperations {
    @BeforeClass
    public static PlayerCreateResponseDto getPlayer() {
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();
        requestDto.setAge(32);
        requestDto.setGender("male");
        requestDto.setLogin(generateRandomLogin());
        requestDto.setPassword(generateRandomPassword());
        requestDto.setRole("user");
        requestDto.setScreenName(generateRandomScreenName());
        return getController.getCreateRequest(requestDto, 200);
    }
    @Test(testName = "User creation")
    public void testGetController() {
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();

        requestDto.setAge(32);
        requestDto.setGender("male");
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
    /*
    Description:
    Issue Type: Bug
    Summary: Missing user role in GetAll method response.
    Severity: High
    Priority: Low
    Steps to reproduce:
    1.Execute the test case below
    Expected result:

    "age": 0,
      "gender": "string",
      "id": 0,
      "role": "string",
      "screenName": "string"

    Actual result:

    "id": 0,
      "screenName": "string",
      "gender": "string",
      "age": 0

     */
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

    @Test(testName = "Delete user by id")
    public void testDeleteRequest() {
        DeleteController delete = new DeleteController();

        Response response = delete.requestSpecification
                .pathParam("editor", "supervisor")
                .body(new PlayerDeleteRequestDto(getPlayer().getId()))
                .when()
                .delete(DeleteController.DELETE_BY_ID)
                .then()
                .log().ifError()
                .statusCode(204)
                .extract().response();
    }
}