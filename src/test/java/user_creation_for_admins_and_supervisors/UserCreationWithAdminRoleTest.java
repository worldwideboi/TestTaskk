package user_creation_for_admins_and_supervisors;

import dto.PlayerCreateResponseDto;
import dto.PlayerDeleteRequestDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.DeleteController;
import playercontroller.GetController;

import static user_access_rights_verification.AllUserOperations.getPlayer;
import static utils.RandomDataCreation.*;
import static utils.RandomDataCreation.generateRandomScreenName;

public class UserCreationWithAdminRoleTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider(){
        return new Object[][]{
                { 17, "male", generateRandomScreenName(), generateRandomPassword(), "user", generateRandomScreenName() },
                { 18, "male", generateRandomScreenName(), generateRandomPassword(), "admin", generateRandomScreenName() },
                { 30, "female", generateRandomScreenName(), generateRandomPassword(), "user", generateRandomScreenName() },
                { 58, "female", generateRandomScreenName(), generateRandomPassword(), "admin", generateRandomScreenName() },
        };
    }
    /*
    Issue Type: Bug #1
    Summary: Issue during user creation with "admin" in pathParam.
    Severity: High
    Priority: Low
    Preconditions: Admin access
    Steps to reproduce:
    1.Open webpage example.com;
    2.Click on Sign Up
    3.Fill all fields with valid data;
    4.Click on "Done" button;
    Expected result: User successfully created;
    Actual result: 403 error.
    */
    @Test(testName = "Validation of user creation by supervisor and admin roles",dataProvider = "Data for user creation")
    public void testGetController(int age, String gender, String login, String password, String role, String screenName){
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
                .pathParam("editor", "admin")
                .body(new PlayerDeleteRequestDto(getPlayer().getId()))
                .when()
                .delete(DeleteController.DELETE_BY_ID)
                .then()
                .log().all()
                .statusCode(204)
                .extract().response();
    }
}
