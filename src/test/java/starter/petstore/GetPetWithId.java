package starter.petstore;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.WebServiceEndPoints;

public class GetPetWithId {

    @Step("Get Pet with ID")
    public void get_pet_with_Id(String petId) {
        SerenityRest.given()
                .log().all()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(petId)
                .when()
                .get(WebServiceEndPoints.PET_ID.getUrl());
    }
}
