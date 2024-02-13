package age_range_validation_test;

import dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.GetController;

import static utils.RandomDataCreation.*;

public class AgeRangeValidationNegativeTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider() {
        return new Object[][]{
                { 16, "male", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName() },
                { 60, "female", generateRandomLogin(), generateRandomPassword(), "user", generateRandomScreenName() },
        };
    }
    /*
    Description:
    Issue Type: Bug
    Summary: Age validation issue during user creation.
    Severity: Low
    Priority: Low
    Steps to reproduce:
    1.Open webpage example.com;
    2.Fill "Age" field with value 16
    3.Fill all required fields
    Expected result: Validation error: "Incorrect age !"
    Actual result: The user with age 16 is being created.
     */
    @Test(testName = "Creation of users with invalid age", dataProvider = "Data for user creation")
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
