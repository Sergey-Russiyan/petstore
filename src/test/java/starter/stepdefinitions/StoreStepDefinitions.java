package starter.stepdefinitions;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.petstore.GenericPetStoreResponse;
import starter.status.Application;

public class StoreStepDefinitions {

    @Steps
    Application theApplication;

    @Steps
    GenericPetStoreResponse genericPetStoreResponse;

    @When("I check the store inventory")
    public void i_check_store_inventory() {
        theApplication.getStoreInventory();
    }



}
