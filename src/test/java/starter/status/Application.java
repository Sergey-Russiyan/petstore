package starter.status;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static starter.WebServiceEndPoints.*;

public class Application {

    public AppStatus currentStatus() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        int statusCode = RestAssured.get(STATUS.getUrl()).statusCode();
        return (statusCode == 200) ? AppStatus.RUNNING : AppStatus.DOWN;
    }

    @Step("Get current status message")
    public void readStatusMessage() {
        SerenityRest.get(STATUS.getUrl());
    }

    // pet
    @Step("Get pet wit id")
    public void getPetWithId(String petId) {
        SerenityRest.get(PET_ID.getUrl() + petId);
    }

    @Step("Post pet wit id, name and status")
    public void postPetWithId(String petId, String petName, String petStatus) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        SerenityRest.
                with().
                param("name", petName).
                param("status", petStatus).
                post(PET_ID.getUrl() + petId);
    }
    public void deletePetWithId(String petId) {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        SerenityRest.delete(PET_ID.getUrl() + petId);
    }
    // store
    @Step("Get store inventory")
    public void getStoreInventory() {
        SerenityRest.get(GET_STORE_INVENTORY.getUrl());
    }

    // user
    @Step("Get user valid Login")
    public void getUserLogin(String userName, String userLogin) {
        SerenityRest.get(String.format(GET_USER_LOGIN.getUrl(), userName, userLogin));
    }
    @Step("Get user logout")
    public void getUserLogout() {
        SerenityRest.get(GET_USER_LOGOUT.getUrl());
    }
    @Step("Delete user")
    public void deleteUserWithName(String userName) {
        SerenityRest.delete(GET_USER_LOGOUT.getUrl() + userName);
    }

}
