package resources;
//enum is a special class in java which has collection of constants and methods

public enum APIresources {
	AddPlaceAPI ("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;
	
	APIresources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
