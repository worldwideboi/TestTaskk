package role_specific_user_creation;

import dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import playercontroller.GetController;

import static utils.RandomDataCreation.*;

public class UserCreationWithInvalidRoleTest {
    @DataProvider(name = "Data for user creation")
    public Object[][] dataProvider(){
        return new Object[][]{
                { 17, "male", generateRandomLogin(), generateRandomPassword(),"deqwds", generateRandomScreenName() },
                { 18, "male", generateRandomLogin(), generateRandomPassword(), "admin1", generateRandomScreenName() },
                { 30, "female", generateRandomLogin(), generateRandomPassword(), "users", generateRandomScreenName() },
                { 58, "female", generateRandomLogin(), generateRandomPassword(), "", generateRandomScreenName() },
        };
    }

    @Test(testName = "Validation of user creation with incorrect roles", dataProvider = "Data for user creation")
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
