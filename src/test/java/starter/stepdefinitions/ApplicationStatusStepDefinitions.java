package starter.stepdefinitions;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.status.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static starter.status.AppStatus.RUNNING;

public class ApplicationStatusStepDefinitions {

    private String msg = "Response not contains field";

    @Steps
    Application theApplication;

    @Given("the application is running")
    public void the_application_is_running() {
        assertThat(theApplication.currentStatus()).isEqualTo(RUNNING);
    }

    @When("I check the application status")
    public void i_check_the_application_status() {
        theApplication.readStatusMessage();
    }
    //    pet
    @When("I check pet with id {string}")
    public void i_check_pet_by_id(String petID) {
        theApplication.getPetWithId(petID);
    }
    @When("I post pet with id {string} name {string} status {string}")
    public void i_post_pet_by_id(String petID, String name, String status) {
        theApplication.postPetWithId(petID, name, status);
    }

    @When("I delete pet with id {string}")
    public void i_delete_pet_by_id(String petID) {
        theApplication.deletePetWithId(petID);
    }

    //    store
    @When("I check the store inventory")
    public void i_check_store_inventory() {
        theApplication.getStoreInventory();
    }

    //    user
    @When("I check user login with valid credentials:")
    public void i_check_user_login_with_valid_credentials(List<Map<String, String>> credentials) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        for(Map<String, String> eachCred : credentials){
            theApplication.getUserLogin(eachCred.get("login"), eachCred.get("password"));
            assertEquals("Unexpected response code when user login with valid credentials",
                    SerenityRest.lastResponse().statusCode(), 200);
        }
    }
    @When("I check user with login:")
    public void i_check_user_login_with_name_and_password(DataTable dataTable) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        theApplication.getUserLogin(dataTable.toString(), dataTable.toString());
    }
    @When("I check user logout")
    public void i_check_user_logout() {
        theApplication.getUserLogout();
    }
    @When("I delete user with name {string}")
    public void i_delete_user_with_name(String userName) {
        theApplication.deleteUserWithName(userName);
    }



    @Then("the API should return {string}")
    public void the_API_should_return(String expectedMessage) {
        restAssuredThat(lastResponse -> lastResponse.body(equalTo(expectedMessage)));
    }
    @Then("the API should return header {string} with value {string}")
    public void the_API_should_return_header(String expectedHeader, String expectedValue) {
        restAssuredThat(lastResponse -> lastResponse.header(expectedHeader, expectedValue));
    }
    @Then("the API should return header {string} which matches {string}")
    public void the_API_should_return_header_matching(String expectedHeader, String expectedRegexpPattern) {
        assertTrue("Response header " + expectedHeader + " not matching pattern: "+ expectedRegexpPattern,
                SerenityRest.lastResponse().header(expectedHeader).matches(expectedRegexpPattern));
    }
    @Then("the API should return response which contains {string}")
    public void the_API_should_return_part_contains(String expectedMessage) {
        assertTrue("Response not contains: " + expectedMessage,
                SerenityRest.lastResponse().body().asString().contains(expectedMessage));
    }
    @Then("the API should return response with status code {int}")
    public void the_API_should_return_response_with_status_code(int statusCode) {
        assertEquals(statusCode, SerenityRest.lastResponse().statusCode());
    }
    @Then("the user should be log out from application")
    public void the_user_should_be_log_out_from_app() {
        the_API_should_return_response_with_status_code(200);
        assertEquals("Unexpected header value for logged in user",
                SerenityRest.lastResponse().header("connection"), "close");
    }
    @Then("the API should return pet with id {string}")
    public void the_API_should_return_response_with_pet_by_id(String expectedId) {
        String msg = "Response not contains pet with ";
        HashMap response = SerenityRest.lastResponse().path("");
        verifyGetPetSuccessResponseMandatoryFields(response);
        assertTrue(msg + " id: " + expectedId, response.get("id").toString().equals(expectedId));
    }


    private void verifyGetPetSuccessResponseMandatoryFields(HashMap<String, String> map){
        assertEquals(SerenityRest.lastResponse().statusCode(), 200);
        assertTrue(msg + "'id'", map.containsKey("id"));
        assertTrue(msg + "'name'", map.containsKey("name"));
        assertTrue(msg + "'photoUrl'", map.containsKey("photoUrl"));
        assertTrue(msg + "'tags'", map.containsKey("tags"));
        assertTrue(msg + "'status'", map.containsKey("status"));
        assertEquals(map.size(), 5);
    }
    private void verifyGetPetNotFoundResponseMandatoryFields(HashMap<String, ?> map){
        assertTrue(msg + "'code'", map.containsKey("code"));
        assertTrue(msg + "'type'", map.containsKey("type"));
        assertTrue(msg + "'message'", map.containsKey("message"));
        assertEquals(map.size(), 3);
    }





    @Then("the API should return response which matches {string}")
    public void the_API_should_return_part_matches(String expectedRegexp) {
        restAssuredThat(lastResponse -> lastResponse.statusCode(200));
        assertTrue("Response not matches to regexp pattern: " + expectedRegexp,
                SerenityRest.lastResponse().body().asString().matches(expectedRegexp));
    }

    @Then("the API should return valid logged in response")
    public void the_API_should_return_valid_logged_in_response() {
        the_API_should_return_part_matches("logged in user session:\\d{13}");
        the_API_should_return_header("X-Rate-Limit",  "5000");
        the_API_should_return_header_matching("X-Expires-After", "\\w{3}\\s\\w{3}([\\s|:]\\d{2}){4}\\sUTC\\s20[\\d]{2}");
    }

    /*
    * sometimes we can receive in response huge JSON without any described model in swagger
    * for such cases we just check it for valid JSON syntax
    * */
    @Then("the API should return valid JSON")
    public void the_API_should_return_valid_Json_response() {
        boolean stepResult = true;
        try {
            JsonParser parser = new JsonParser();
            parser.parse(SerenityRest.lastResponse().body().asString());
        }catch (JsonSyntaxException ex){
            stepResult = false;
        }finally {
            assertEquals("Inventory returns invalid JSON response", true, stepResult);
        }
    }

}
