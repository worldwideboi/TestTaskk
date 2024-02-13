package password_validation_test;

import dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.GetController;

import static utils.RandomDataCreation.generateRandomLogin;
import static utils.RandomDataCreation.generateRandomScreenName;

public class PasswordValidationNegativeTest{
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider(){
        return new Object[][]{
                { 17, "male", generateRandomLogin(), "qwert6", "user", generateRandomScreenName() },
                { 59, "female", generateRandomLogin(), "qwertyuiopasdf16", "admin",generateRandomScreenName() }
        };
    }

    /*
    Issue Type: Bug #1
    Summary: Password validation issue during user creation.
    Severity: Low
    Priority: Low
    Preconditions:
    password1: qwert6;
    password2: qwertyuiopasdf16;

    Steps to reproduce:
    1.Open webpage example.com;
    2.Click on Sign Up
    3.Fill "Password" field with data from preconditions;
    4.Fill all other required fields with valid data;
    5.Click on "Done" button;
    Expected result: Validation error: "Password size should be from 7 to 15 symbols !";
    Actual result: The system accepts passwords with lengths outside the specified range (6 to 16 symbols).
    */
    @Test(testName = "Creation of users with invalid password", dataProvider = "Data for user creation")
    public void testGetController(int age, String gender, String login, String password, String role, String screenName){
        GetController getController = new GetController();
        PlayerCreateResponseDto requestDto = new PlayerCreateResponseDto();

        requestDto.setAge(age);
        requestDto.setGender(gender);
        requestDto.setLogin(login);
        requestDto.setPassword(password);
        requestDto.setRole(role);
        requestDto.setScreenName(screenName);

        PlayerCreateResponseDto response1 = getController.getCreateRequest(requestDto,400);
    }
}
