package rus.stepdefinitions;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import rus.entity.OrderTestObject;
import rus.petstore.GenericPetStoreResponse;
import rus.status.Application;

public class StoreStepDefinitions {

    @Steps
    Application theApplication;

    @Steps
    GenericPetStoreResponse genericPetStoreResponse;

    @When("I check the store inventory")
    public void i_check_store_inventory() {
        theApplication.getStoreInventory();
    }
    @When("I post new valid store order")
    public void i_post_valid_store_order() {

        theApplication.postStoreOrder(new OrderTestObject());
    }


}
