package starter.stepdefinitions;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.petstore.GenericPetStoreResponse;
import starter.status.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static starter.status.AppStatus.RUNNING;

public class PetStepDefinitions {

    private String msg = "Response not contains field";

    @Steps
    Application theApplication;

    @Steps
    GenericPetStoreResponse genericPetStoreResponse;

    @Given("the application is running")
    public void the_application_is_running() {
        assertThat(theApplication.currentStatus()).isEqualTo(RUNNING);
    }

    @When("I check the application status")
    public void i_check_the_application_status() {
        theApplication.readStatusMessage();
    }

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


    @Then("the response should include the following details:")
    public void response_should_contain_the_following_details(List<Map<String, String>> tradeDetails) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        restAssuredThat(response -> response.statusCode(200));

        Map<String, String> expectedResponse = tradeDetails.get(0);
        Map<String, String> actualResponse = genericPetStoreResponse.returned();

        assertThat(actualResponse).containsAllEntriesOf(expectedResponse);
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


}
