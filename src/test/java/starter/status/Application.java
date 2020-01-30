package starter.status;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.entity.OrderTestObject;
import starter.entity.UserTestObject;

import static io.restassured.http.ContentType.JSON;
import static starter.WebServiceEndPoints.*;

public class Application {

    public AppStatus currentStatus() {
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
        SerenityRest.
                with().
                param("name", petName).
                param("status", petStatus).
                post(PET_ID.getUrl() + petId);
    }
    @Step("Delete pet with id")
    public void deletePetWithId(String petId) {
        SerenityRest.delete(PET_ID.getUrl() + petId);
    }
    @Step("Find pet with status")
    public void findPetByStatus(String petId) {
        SerenityRest.delete(PET_ID.getUrl() + petId);
    }
    // store
    @Step("Get store inventory")
    public void getStoreInventory() {
        SerenityRest.get(GET_STORE_INVENTORY.getUrl());
    }
    @Step("Post new store order")
    public void postStoreOrder(OrderTestObject order) {
        String test = order.creationTime;
        SerenityRest.
                given().log().all().
                contentType(JSON).
                body(order.asString()).
                post(GET_STORE_ORDER.getUrl());
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
        SerenityRest.delete(USER.getUrl() +"/"+ userName);
    }

    @Step("Post new user")
    public void postUser(UserTestObject user) {
        SerenityRest.
                given().log().all().
                contentType(JSON).
                body(user.asFlatJson()).
                post(USER.getUrl());
    }

    @Step("Post array of users")
    public void postUsersArray(int usersQty) {
        String body = new UserTestObject().asArrayOf(usersQty);
        SerenityRest.
                given().log().all().
                contentType(JSON).
                body(body).
                post(USER_CREATE_ARRAY.getUrl());
    }

    @Step("Post list of users")
    public void postUsersList(int usersQty) {
        String body = new UserTestObject().asListOf(usersQty);
        SerenityRest.
                given().log().all().
                contentType(JSON).
                body(body).
                post(USER_CREATE_LIST.getUrl());
    }

    @Step("Update user")
    public void updateUserWithName(String userName, UserTestObject user) {
        SerenityRest.
                given().log().all().
                contentType(JSON).
                body(user.asFlatJson()).
                put(USER.getUrl() +"/"+ userName);
    }


}
