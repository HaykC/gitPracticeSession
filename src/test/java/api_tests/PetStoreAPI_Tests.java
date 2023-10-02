package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestApiUtils;

public class PetStoreAPI_Tests {
	String baseurl = "https://petstore.swagger.io/v2";
	Response response;
	RestApiUtils apiutils;
	int petid;
	
	// create a pet with dynamic ID, and store the id as a global variable@
	
	@Test
	public void create_A_Pet() {
		apiutils = new RestApiUtils();
		// we need url
		String endpoint = "/pet";
		// request header
		// request method
		// request body
		
		petid = apiutils.randomNumber();
		
		String payload = "{\r\n"
				+ "  \"id\": "+ petid + ",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"dog\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"Jeko\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 2259,\r\n"
				+ "      \"name\": \"Shun\"\r\n"
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
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertEquals(Integer.parseInt(response.path("id").toString()), petid);
		Assert.assertEquals(response.path("name").toString(), "Jeko");
		Assert.assertEquals(response.path("status").toString(), "available");
	}
	
	
	@Test(dependsOnMethods = "update_a_pet")
	public void delete_a_pet() {
		String endpoint = "/pet/";
		response = RestAssured
				.given().accept("application/json")
				.when()
				.delete(baseurl + endpoint + petid);
		response.then().statusCode(200).contentType("application/json");
		
	}
	
	
	@Test
	public void getPetsByStatus() {

		String endpoint = "/pet/findByStatus";

		response = RestAssured.given().accept(ContentType.JSON).param("status", "sold").when().get(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");

		response.prettyPrint();
	}
	
	
	@Test(dependsOnMethods = "getApetByID")
	public void update_a_pet() {
		String endpoint = "/pet";
		String payload = "{\r\n"
				+ "  \"id\": "+ petid +",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"Layka\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"Layka\",\r\n"
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
				.given().contentType("application/json")
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertEquals(Integer.parseInt(response.path("id").toString()), petid);
		Assert.assertEquals(response.path("name").toString(), "Layka");
		Assert.assertEquals(response.path("status").toString(), "available");
		
	}
	

	@Test(dependsOnMethods = "create_A_Pet")
	public void getApetByID() {
		String endpoint = "/pet/";

		response = RestAssured.given().accept(ContentType.JSON).when().get(baseurl + endpoint + petid);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();

		System.out.println(response.path("name").toString());
		System.out.println(response.jsonPath().get("id").toString());

		Assert.assertEquals(Integer.parseInt(response.path("id").toString()), petid);
		Assert.assertEquals(response.path("name").toString(), "Jeko");
		
		System.out.println(response.path("category.name").toString());
		System.out.println(response.path("category.id").toString());

		System.out.println(response.path("tags[0].id").toString());

		
		Assert.assertEquals(Integer.parseInt(response.path("id").toString()), petid);
		Assert.assertEquals(response.path("name").toString(), "Jeko");

		
	}


	
	@Test
	public void invalidGetPetByID() {
		String endpoint = "/pet/";

		response = RestAssured.given().accept(ContentType.JSON).when().get(baseurl + endpoint + 9298);

		response.then().statusCode(404).contentType("application/json");
		response.prettyPrint();
		Assert.assertEquals(response.path("message".toString()), "Pet not found");
		
		
		
	}

}
