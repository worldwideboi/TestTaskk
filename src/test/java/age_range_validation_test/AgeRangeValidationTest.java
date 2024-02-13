package age_range_validation_test;

import dto.PlayerCreateResponseDto;
import dto.PlayerDeleteRequestDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.DeleteController;
import playercontroller.GetController;

import static admin_access_rights_verification.AllAdminOperations.getPlayer;
import static utils.RandomDataCreation.*;

public class AgeRangeValidationTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider() {
        return new Object[][]{
                {17, "male", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName()},
                {18, "female", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName()},
                {30, "female", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName()},
                {58, "male", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName()},
                {59, "male", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName()}
        };
    }

    @Test(testName = "Creation of users with valid age", dataProvider = "Data for user creation")
    public void testGetController(int age, String gender, String login, String password, String role, String screenName) {
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();

        requestDto.setAge(age);
        requestDto.setGender(gender);
        requestDto.setLogin(login);
        requestDto.setPassword(password);
        requestDto.setRole(role);
        requestDto.setScreenName(screenName);

        PlayerCreateResponseDto response1 = getController.getCreateRequest(requestDto,200);
    }
    @AfterMethod
    public void cleanupCreatedUsers(){
        DeleteController delete = new DeleteController();

        Response response = delete.requestSpecification
                .pathParam("editor", "supervisor")
                .body(new PlayerDeleteRequestDto(getPlayer().getId()))
                .when()
                .delete(DeleteController.DELETE_BY_ID)
                .then()
                .log().all()
                .statusCode(204)
                .extract().response();
    }
}