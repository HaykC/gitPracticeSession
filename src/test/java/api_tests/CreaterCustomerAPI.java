package api_tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestDataReader;

public class CreaterCustomerAPI {
	String baseurl = "http://crater.primetech-apps.com/";
	
	Response response;
	Faker faker = new Faker();
	String firstName = faker.name().firstName().toString();
	String email = firstName + "@gmail.com";
	String token;
	String id;

	
	@Test
	public void crater_login() {
		String endpoint = "api/v1/auth/login";
		String userEmail = TestDataReader.getProperty("email");
		String userPassword = TestDataReader.getProperty("password");
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("username", userEmail);
		requestBody.put("password", userPassword);
		requestBody.put("device_name", "mobile_app");
		
		response = RestAssured.given()
				.headers(requestHeaders)
				.body(requestBody)
				.when().post(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		token = response.path("token");
		Assert.assertEquals(response.path("type"), "Bearer");
		
	}
	
	
	@Test(dependsOnMethods = "crater_login")
	public void list_of_customers() {

		String endpoint = "api/v1/customers";
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");
		requestHeaders.put("Authorization", "Bearer " + token);

		Map<String, Object> params = new HashMap<>();
		params.put("limit", 15);
	
	response = RestAssured
			.given()
			.headers(requestHeaders)
			.params(params)
			.when().get(baseurl + endpoint);
	
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		Assert.assertNotNull(response.path("data").toString());
	
	}	
	
	@Test(dependsOnMethods = "crater_login")
	public void create_a_customer() {
		String endpoint = "api/v1/customers";

		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");
		requestHeaders.put("Authorization", "Bearer " + token);
		
		Map<String, Object> user = new HashMap<>();
		user.put("name", firstName);
		user.put("email", email);
		user.put("enable_portal", true);
		
		response = RestAssured
				.given()
				.headers(requestHeaders)
				.body(user)
				.when().post(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		id = response.path("data.id").toString();
//	
//		Assert.assertTrue(response.path("data.enable_portal"));
//		Assert.assertEquals(response.path("data.name").toString(), firstName);
//		System.out.println(response.path("data.id").toString());
//		System.out.println(firstName);
		
	}
	
	
	@Test(dependsOnMethods = "create_a_customer")
	public void get_a_customer_by_id() {
		String endpoint = "api/v1/customers/" + id;
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");
		requestHeaders.put("Authorization", "Bearer " + token);
		
		response = RestAssured
				.given().headers(requestHeaders)
				.when().get(baseurl + endpoint);
		
		response.prettyPrint();
		response.then().statusCode(200).contentType("application/json");
		
		Assert.assertTrue(response.path("data.enable_portal"));
		
	}
	
	
	@Test(dependsOnMethods = "create_a_customer")
	public void update_a_customer_by_id() {
		String endpoint = "api/v1/customers/" + id;
		String updatedName = faker.name().firstName();
		
		Map<String, Object> user = new HashMap<>();
		user.put("name", updatedName);
		user.put("email", email);
		user.put("enable_portal", false);
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");
		requestHeaders.put("Authorization", "Bearer " + token);
		
		response = RestAssured
				.given().headers(requestHeaders)
				.body(user).when()
				.put(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertEquals(response.path("data.id").toString(), id);
		Assert.assertEquals(response.path("data.name").toString(), updatedName);
		Assert.assertFalse(response.path("data.enable_portal"));
		
		System.out.println(response.path("data.name").toString());
		System.out.println(response.path("data.id").toString());
		
		System.out.println(response.path("data").toString());
		
	}
	
	
}
