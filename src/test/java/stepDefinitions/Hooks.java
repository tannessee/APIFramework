package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace") // пререквизиты перед выполнением отдельного DeletePlace
	public void beforeScenario() throws Throwable {
		// execute this code when place id is null
		// write code that will create a place id
		placeValidationSteps m = new placeValidationSteps();

		if (placeValidationSteps.place_id == null) {
			m.add_place_payload_with("Izbushka2", "Germany-GR", "Kaluga");
			m.user_calls_something_with_some_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Izbushka2", "getPlaceAPI");
		}

	}

}
