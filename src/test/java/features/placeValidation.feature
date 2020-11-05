Feature: Validating Place Api's
@AddPlace @Regression
Scenario Outline: Verify if place has been Successfully added using AddPlaceApi
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "post" http request 
	Then Api call got success with status code 200	
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_id created maps to  "<name>" using "getPlaceAPI"

Examples:

		|  name      |   language     |    address           |
		|  Resto     |   Russian-RU   |    Main str, 9008    |
#		|  Bungalow  |   Italian-IT   |    Milano str, 8990  |
#		|  Izbushka  |   Gagauz-GE    |    Copceac str, 8990  |
	

@DeletePlace 
Scenario: Verify if DeletePlace functionality is working
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "post" http request 
	Then Api call got success with status code 200	
	And "status" in response body is "OK"
	
	
	