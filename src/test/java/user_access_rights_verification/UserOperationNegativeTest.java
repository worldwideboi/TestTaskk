package user_access_rights_verification;

import dto.PlayerDeleteRequestDto;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import playercontroller.DeleteController;

import static admin_access_rights_verification.AllAdminOperations.getPlayer;

public class UserOperationNegativeTest {
    @Test(testName = "Deletion of user by user")
    public void testDeleteRequest() {
        DeleteController delete = new DeleteController();

        Response response = delete.requestSpecification
                .pathParam("editor", "supervisor")
                .body(new PlayerDeleteRequestDto(getPlayer().getId()))
                        .when()
                        .delete(DeleteController.DELETE_BY_ID)
                        .then()
                        .log().ifError()
                        .statusCode(400)
                        .extract().response();
    }
}
