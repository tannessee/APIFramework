package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Place_Google_map;

public class TestDataBuild {
	
	public Place_Google_map addPlacePayload(String name, String language, String address) {
		Place_Google_map pl = new Place_Google_map();
		pl.setAccuracy(50);
		pl.setAddress(address);
		pl.setLanguage(language);
		pl.setName(name);
		pl.setPhone_number("(+91) 983 893 3937");
		pl.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		pl.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		pl.setLocation(l);
		
		return pl;
			
	}
	
	public String deletePlacePayload(String placeId)	{
		return "{\r\n    \"place_id\":\""+ placeId +"\"\r\n}";
		
	} 
}
