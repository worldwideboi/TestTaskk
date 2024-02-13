package login_and_screen_name_uniqueness_test;

import dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.GetController;

import static utils.RandomDataCreation.*;

public class LoginAndScreenNameUniquenessTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider(){
        return new Object[][]{
                { 18, "male", "Login", generateRandomPassword(), "admin", generateRandomScreenName() },
                { 30, "female", "Login", generateRandomPassword(), "admin", generateRandomScreenName() },
                { 33, "female", generateRandomLogin(), generateRandomPassword(), "user", "sameScreenName" },
                { 37, "male", generateRandomLogin(), generateRandomPassword(), "user", "sameScreenName" },
                { 19, "female", "sameLogin", generateRandomPassword(), "user", "sameScreenName1" },
                { 44, "female", "sameLogin", generateRandomPassword(), "admin", "sameScreenName1" }
        };
    }
    /*
    Description:
    Issue Type: Bug #1
    Summary: Unique Login validation doesn't work.
    Severity: High
    Priority: Low
    Preconditions: login: Login
    Steps to reproduce:
    1.Open webpage example.com;
    2.Click on Sign Up
    3.Fill "login" field with data from preconditions;
    4.Fill all other required fields with valid data;
    5.Click on "Done" button;
    6.Click on "Log out" button;
    7.Repeat steps 2 - 5;
    Expected result: Validation error: "This login already in use !";
    Actual result: The system allows the creation of a user with a duplicate login.

    /////////////////////////////////////////////////////////////////////////////////

    Issue Type: Bug #2
    Summary: Unique screenName validation doesn't work.
    Severity: High
    Priority: Low
    Preconditions: screenName: sameScreenName
    Steps to reproduce:
    1.Open webpage example.com;
    2.Click on Sign Up
    3.Fill "screenName" field with data from preconditions;
    4.Fill all other required fields with valid data;
    5.Click on "Done" button;
    6.Click on "Log out" button;
    7.Repeat steps 2 - 5;
    Expected result: Validation error: "This screenName already in use !";
    Actual result: The system allows the creation of a user with a duplicate screeName.
     */

    @Test(testName = "Validation of users logins and screen names uniqueness", dataProvider = "Data for user creation")
    public void testGetController(int age, String gender, String login, String password, String role, String screenName){
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();

        requestDto.setAge(age);
        requestDto.setGender(gender);
        requestDto.setLogin(login);
        requestDto.setPassword(password);
        requestDto.setRole(role);
        requestDto.setScreenName(screenName);

        PlayerCreateResponseDto response1 = getController.getCreateRequest(requestDto, 400);
    }
}
