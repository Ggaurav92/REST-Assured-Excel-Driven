package testFrameworks;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.dataDriven;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ExcelDriven {

	@Test
	public void PostExcel() throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		dataDriven data = new dataDriven();
		ArrayList<String> d = data.getData("RestAddBook","RestAssured");
		
		
		
		HashMap<String, Object>  map = new HashMap<String, Object>();
		map.put("accuracy", d.get(1));
		map.put("name", d.get(2));
		map.put("phone_number", d.get(3));
		map.put("address", d.get(4));
		map.put("website", d.get(5));
		map.put("language", d.get(6));
		
		HashMap<String, Object>  map2 = new HashMap<String, Object>();
		map2.put("lat",d.get(7));
		map2.put("lng", d.get(8));
		map.put("location", map2);
		
		ArrayList<String> typesList = new ArrayList<String>(Arrays.asList(d.get(9),d.get(10)));
		map.put("types", typesList);
		
		
		Response resp = given().log().all().header("Content-Type","application/json").header("User-Agent","PostmanRuntime/7.26.3")
				.header("Accept","*/*").header("Accept-Encoding","gzip, deflate, br")
				.header("Connection","keep-alive")
		.queryParam("key", "qaclick123")
				.body(map)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response();
	
		
		JsonPath js = new JsonPath(resp.asString());
		String id = js.get("id");
		System.out.println(id);
		
		
	}
	
	
}//End of Class
