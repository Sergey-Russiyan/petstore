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

    @Step("Get store inventory")
    public void getStoreInventory() {
        SerenityRest.get(GET_STORE_INVENTORY.getUrl());
    }

    @Step("Get user valid Login")
    public void getUserLogin(String userName, String userLogin) {
        SerenityRest.get(String.format(GET_USER_LOGIN.getUrl(), userName, userLogin));
    }


}
