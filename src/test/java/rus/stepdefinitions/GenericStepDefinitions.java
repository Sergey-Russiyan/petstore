package rus.stepdefinitions;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GenericStepDefinitions {

    /*
    * generic steps which
    * */
    @Then("the API should return {string}")
    public void the_API_should_return(String expectedMessage) {
        restAssuredThat(lastResponse -> lastResponse.body(equalTo(expectedMessage)));
    }
    @Then("the API should return response which contains {string}")
    public void the_API_should_return_part_contains(String expectedMessage) {
        SerenityRest.given().log().all();
        assertTrue("Response not contains: " + expectedMessage,
                SerenityRest.lastResponse().body().asString().contains(expectedMessage));
    }
    @Then("the API should return response with status code {int}")
    public GenericStepDefinitions the_API_should_return_response_with_status_code(int statusCode) {
        assertEquals(statusCode, SerenityRest.lastResponse().statusCode());
        return this;
    }
    @Then("the API should return response which matches {string}")
    public void the_API_should_return_part_matches(String expectedRegexp) {
        restAssuredThat(lastResponse -> lastResponse.statusCode(200));
        assertTrue("Response not matches to regexp pattern: " + expectedRegexp,
                SerenityRest.lastResponse().body().asString().matches(expectedRegexp));
    }
    @Then("the API should return header {string} which matches {string}")
    public void the_API_should_return_header_matching(String expectedHeader, String expectedRegexpPattern) {
        assertTrue("Response header " + expectedHeader + " not matching pattern: "+ expectedRegexpPattern,
                SerenityRest.lastResponse().header(expectedHeader).matches(expectedRegexpPattern));
    }
    @Then("the API should return header {string} with value {string}")
    public void the_API_should_return_header(String expectedHeader, String expectedValue) {
        restAssuredThat(lastResponse -> lastResponse.header(expectedHeader, expectedValue));
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


    private void verifyGetBadResponseMandatoryFields(HashMap<String, ?> map){
        String msg = "response NOT contains expected field: ";
        assertTrue(msg + "'code'", map.containsKey("code"));
        assertTrue(msg + "'type'", map.containsKey("type"));
        assertTrue(msg + "'message'", map.containsKey("message"));
        assertEquals(map.size(), 3);
    }


}
