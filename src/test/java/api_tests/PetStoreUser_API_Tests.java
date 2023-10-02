package api_tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.RestApiUtils;

public class PetStoreUser_API_Tests {
	String baseurl = "https://petstore.swagger.io/v2";
	Response response;
	Faker faker = new Faker();
	RestApiUtils utils = new RestApiUtils();
	int id;
	String username;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;

	@Test
	public void create_a_user() {
		int randomNumber = utils.randomNumber();
		String endpoint = "/user";
		id = 65165;
		username = faker.name().username();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		email = firstName + lastName + "@gmai.com";
		password = "kuygfuitfiykugy";
		phone = faker.phoneNumber().cellPhone();

		String body = "{\r\n" + "  \"id\": " + id + ",\r\n" + "  \"username\": \"" + username + "\",\r\n"
				+ "  \"firstName\": \"" + firstName + "\",\r\n" + "  \"lastName\": \"" + lastName + "\",\r\n"
				+ "  \"email\": \"" + email + "\",\r\n" + "  \"password\": \"" + password + "\",\r\n"
				+ "  \"phone\": \"" + phone + "\",\r\n" + "  \"userStatus\": 0\r\n" + "}";

		response = RestAssured.given().accept("application/json").contentType("application/json").body(body).when()
				.post(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();

	}

	@Test(dependsOnMethods = "create_a_user")
	public void get_a_user_with_username() {

		String endpoint = "/user/";
		response = RestAssured.given().accept("application/json").contentType("application/json").when()
				.get(baseurl + endpoint + username);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		Assert.assertEquals(Integer.parseInt(response.jsonPath().get("id").toString()), id);

	}
	
	
	@Test (dependsOnMethods = "get_a_user_with_username")
	public void get_a_invalid_user() {
		String endpoint = "/user/";
		String invalidUsername = username + "xxx";
		
		response = RestAssured.given()
				.accept("application/json")
				.contentType("application/json")
				.when()
				.get(baseurl + endpoint + username + invalidUsername);
		
		response.then().statusCode(404).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertEquals(Integer.parseInt(response.path("code").toString()), 1);
		Assert.assertEquals(response.path("type"), "error");
		Assert.assertEquals(response.path("message"), "User not found");
		
		System.out.println(response.path("code").toString());
		
		
	}
	
	
	@Test (dependsOnMethods = "create_a_user")
	public void user_login() {
		String endpoint = "/user/login";
		
		response = RestAssured.given()
				.accept("application/json")
				.contentType("application/json")
				.param(username)
				.param(password)
				.when()
				.get(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertNotNull(response.path("message").toString());
		
	}	
	
	
	@Test (dependsOnMethods = "user_login")
	public void user_logout() {
		String endpoint = "/user/logout";
		
		response = RestAssured.given()
				.accept("application/json")
				.contentType("application/json")
				.when()
				.get(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		Assert.assertTrue(response.path("message").toString().equals("ok"));
	}
	
	
	@Test (dependsOnMethods = "create_a_user")
	public void update_a_user() {
		String endpoint = "/user/" + username;
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		
		String body = "{\r\n" + "  \"id\": " + id + 111 + ",\r\n" + "  \"username\": \"" + username + "updated" + "\",\r\n"
				+ "  \"firstName\": \"" + firstName + "updated" + "\",\r\n" + "  \"lastName\": \"" + lastName + "updated" +  "\",\r\n"
				+ "  \"email\": \"" + email + "updated" + "\",\r\n" + "  \"password\": \"" + password + "updated" + "\",\r\n"
				+ "  \"phone\": \"" + phone + "\",\r\n" + "  \"userStatus\": 0\r\n" + "}";
		
		response = RestAssured.given()
				.headers(requestHeaders)
			//	.accept("application/json")
			//	.contentType("application/json")
				.body(body)
				.when()
				.put(baseurl + endpoint);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		
	}
	
}
