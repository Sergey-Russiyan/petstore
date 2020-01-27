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

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static starter.status.AppStatus.RUNNING;

public class ApplicationStatusStepDefinitions {

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
    @When("I check the store inventory")
    public void i_check_store_inventory() {
        theApplication.getStoreInventory();
    }
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
        restAssuredThat(lastResponse -> lastResponse.body(contains(expectedMessage)));
    }
    @Then("the API should return response which matches {string}")
    public void the_API_should_return_part_matches(String expectedRegexp) {
        restAssuredThat(lastResponse -> lastResponse.statusCode(200));
        assertTrue("Response not matches to regexp pattern: " + expectedRegexp,
                SerenityRest.lastResponse().body().asString().matches(expectedRegexp));
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
