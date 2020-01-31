package rus.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import rus.entity.UserTestObject;
import rus.status.Application;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserStepDefinitions {

    @Steps
    Application theApplication;

    @When("I check user login with valid credentials:")
    public void i_check_user_login_with_valid_credentials(List<Map<String, String>> credentials) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        for(Map<String, String> eachCred : credentials){
            theApplication.getUserLogin(eachCred.get("login"), eachCred.get("password"));
            assertEquals("Unexpected response code when user login with valid credentials",
                    SerenityRest.lastResponse().statusCode(), 200);
        }
    }
    @When("I check user login with invalid credentials:")
    public void i_check_user_login_with_invalid_credentials(List<Map<String, String>> credentials) {
        for(Map<String, String> eachCred : credentials){
            theApplication.getUserLogin(eachCred.get("login"), eachCred.get("password"));
            assertEquals("Unexpected response code when user login with valid credentials",
                    SerenityRest.lastResponse().statusCode(), 400);
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

    @When("I update existing user {string}")
    public void i_update_user_with_name(String userName) {
        UserTestObject user = new UserTestObject();
        theApplication.updateUserWithName(userName, user);
    }
    @When("I post new valid user")
    public void i_post_new_valid_user() {
        theApplication.postUser(new UserTestObject());
    }
    @When("I post array of valid users {int}")
    public void i_post_array_of_valid_users(int usersQty) {
        theApplication.postUsersArray(usersQty);
    }
    @When("I post list of valid users {int}")
    public void i_post_list_of_valid_users(int usersQty) {
        theApplication.postUsersList(usersQty);//todo ask dev - actually there are no difference in requests bracketing
    }
    @Then("the user should be log out from application")
    public void the_user_should_be_log_out_from_app() {
        GenericStepDefinitions gen = new GenericStepDefinitions();
        gen.the_API_should_return_response_with_status_code(200);
        assertEquals("Unexpected header value for logged in user",
                SerenityRest.lastResponse().header("connection"), "close");
    }
    @Then("the API should return valid logged in response")
    public void the_API_should_return_valid_logged_in_response() {
        GenericStepDefinitions gen = new GenericStepDefinitions();
        gen.the_API_should_return_part_matches("logged in user session:\\d{13}");
        gen.the_API_should_return_header("X-Rate-Limit",  "5000");
        gen.the_API_should_return_header_matching("X-Expires-After", "\\w{3}\\s\\w{3}([\\s|:]\\d{2}){4}\\sUTC\\s20[\\d]{2}");
    }

}
