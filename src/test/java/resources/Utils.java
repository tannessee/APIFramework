package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
	
		if (req==null) { // чтобы не перезаписывался файл logging.txt			
		PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")) // повторяющуюся часть запроса сохраним
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log)) // для сохранения логов request в файле
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) // для сохранения логов response в файле
				.build(); 		
		return req;
		} 
			return req;
	
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\andre\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties"); //data comming from inside the file
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath (Response response, String key) {
		
		String resp = response.asString(); 
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
		
	}

}
