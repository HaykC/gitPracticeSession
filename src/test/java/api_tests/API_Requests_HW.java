package api_tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestApiUtils;



public class API_Requests_HW {
	Response response;
	String baseurl = "https://petstore.swagger.io/v2";
	

	
	@Test
	public void TestCase1() {
		String endpoint = "/pet/";
		int petid = 22112;

		response = RestAssured.given().accept(ContentType.JSON).when().get(baseurl + endpoint + petid);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		Assert.assertEquals(response.path("name").toString(), "BooBoo");
		System.out.println(response.path("name").toString());

}
	
	
	@Test
	public void TestCase2() {
		String endpoint = "/pet/";
		int petid = 201029;

		response = RestAssured.given().accept(ContentType.JSON).when().get(baseurl + endpoint + petid);

		response.then().statusCode(404).contentType("application/json");
		response.prettyPrint();
		
		System.out.println(response.jsonPath().get("message").toString());
		Assert.assertEquals(response.path("message").toString(), "Pet not found");
		
	}
	
	
	@Test
	public void TestCase3() {
		String endpoint = "/pet";
		
		String payload = "{\r\n"
				+ "  \"id\": \"hhhhhh\",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"6526\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		
		response = RestAssured
				.given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(baseurl + endpoint);
		
		response.then().statusCode(405).contentType("application/json");
		response.prettyPrint();
		
	}
	
	
	@Test
	public void TestCase4() {
		String endpoint = "/pet";
		String petid = "kuygul"; // Invalid pet ID
		

		response = RestAssured.given().contentType("application/json").accept(ContentType.JSON)
				.when().get(baseurl + endpoint + petid);
		response.then().statusCode(404).contentType("application/json");
		response.prettyPrint();
		
	}
	
	@Test
	public void TestCase5() {
		String endpoint = "/pet";
		String petid = "kuygul"; // Invalid pet ID
		
		response = RestAssured.given().contentType("application/json").accept(ContentType.JSON)
				.when().delete(baseurl + endpoint + petid);
		response.then().statusCode(404).contentType("application/json");
		response.prettyPrint();
		
	}
	
}









