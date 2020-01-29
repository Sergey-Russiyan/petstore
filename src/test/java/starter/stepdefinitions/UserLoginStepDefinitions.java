package starter.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.thucydides.core.annotations.Steps;
import starter.petstore.PetStoreResponse;
import starter.petstore.GetPetWithId;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UserLoginStepDefinitions {

    @Steps
    GetPetWithId getPetWithId;

    @Steps
    PetStoreResponse petStoreResponse;

    String trade;


    @Then("the response should include the following details:")
    public void response_should_contain_the_following_details(List<Map<String, String>> tradeDetails) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        restAssuredThat(response -> response.statusCode(200));

        Map<String, String> expectedResponse = tradeDetails.get(0);
        Map<String, String> actualResponse = petStoreResponse.returned();

        assertThat(actualResponse).containsAllEntriesOf(expectedResponse);
    }

}
