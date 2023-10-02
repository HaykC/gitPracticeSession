package api_tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.RestApiUtils;
import utils.TestDataReader;

public class CraterApiTests {

	String baseurl = "http://crater.primetech-apps.com/";

	RestApiUtils apiutils;
	Response response;
	String token;
	int id;

	@Test
	public void login() {
		String endpoint = "api/v1/auth/login";

		String userEmail = TestDataReader.getProperty("email");
		String userPassword = TestDataReader.getProperty("password");
//		String body = "{\r\n"
//				+ "    \"username\": \"" + userEmail + "\",\r\n"
//				+ "    \"password\": \"" + userPassword + "\",\r\n"
//				+ "    \"device_name\": \"mobile_app\"\r\n"
//				+ "}";
//		

		// serialize and de-serialize
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("username", userEmail);
		requestBody.put("password", userPassword);
		requestBody.put("device_name", "mobile_app");

		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("Company", "1");

		response = RestAssured.given().headers(requestHeaders).body(requestBody).when().post(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();

		token = response.path("token").toString();

		Assert.assertEquals(response.path("type").toString(), "Bearer");

	}

	@Test(dependsOnMethods = "login")
	public void create_an_item() {
		String endpoint = "api/v1/items";
		String requestBody = "{\r\n" + "    \"name\": \"Laptop\",\r\n" + "    \"price\": 1445" + "}";
		Map<String, Object> requestHeader = new HashMap<>();

		response = RestAssured.given().header("Content-type", "application/json").header("Accept", "application/json")
				.header("company", "1").header("Authorization", "Bearer " + token).body(requestBody).when()
				.post(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		id = response.path("data.id");

	}

	@Test(dependsOnMethods = "create_an_item")
	public void delete_the_item() {
		String endpoint = "api/v1/items/delete";

		String body = "{\r\n" + "    \"ids\": [\r\n" + "        " + id + "\r\n" + "    ]\r\n" + "}";

		response = RestAssured.given().header("Content-type", "application/json").header("Accept", "application/json")
				.header("company", "1").header("Authorization", "Bearer " + token).body(body).when()
				.post(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		Assert.assertTrue(response.path("success"));

	}

	@Test(dependsOnMethods = "delete_the_item")
	public void logout() {
		String endpoint = "api/v1/auth/logout";
		RestAssured.given().header("Content-type", "application/json").header("Accept", "application/json")
				.header("company", "1").header("Authorization", "Bearer " + token).when().post(baseurl + endpoint);

		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();

	}

	@Test
	public void fakerTest() {
		Faker faker = new Faker();

		System.out.println(faker.name().fullName());
		System.out.println(faker.name().lastName());
		System.out.println(faker.name().nameWithMiddle());

		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().fullAddress());

		System.out.println(faker.commerce().productName());
		System.out.println(faker.commerce().price());
		System.out.println(faker.commerce().color());

	}

}
