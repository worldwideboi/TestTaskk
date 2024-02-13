package gender_specific_user_creation;

import dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.GetController;

import static utils.RandomDataCreation.*;
import static utils.RandomDataCreation.generateRandomScreenName;

public class UserCreationWithInvalidGenderTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider(){
        return new Object[][]{
                { 21, "Female", generateRandomScreenName(), generateRandomPassword(), "user", generateRandomScreenName() },
                { 30, "feemale", generateRandomScreenName(), generateRandomPassword(), "user", generateRandomScreenName() },
                { 58, "1", generateRandomScreenName(), generateRandomPassword(), "admin", generateRandomScreenName() },
                { 53, "ауьфду", generateRandomScreenName(), generateRandomPassword(), "admin", generateRandomScreenName() },
        };
    }
    @Test(testName = "Creation of user with invalid gender",dataProvider = "Data for user creation")
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
