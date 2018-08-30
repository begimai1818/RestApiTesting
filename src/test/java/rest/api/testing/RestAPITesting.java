package rest.api.testing;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITesting {

	@BeforeClass
	public static void setUp() {

		RestAssured.baseURI = "https://www.batch8api.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2/";
	}

	@Test
	public void simpleGetRequest() {

		given().relaxedHTTPSValidation()
		.when().log().all()
				// .queryParam("per_page",2)
				.get("/posts")
				.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.and().
				body("id", hasItem(8))
		// .body("title.rendered", is("Vadym title") )
		// .body("sticky", is(false))

		;

		// TASK . CHECK YOUR RESPONSE ID AND TITLE IS AS EXPECTED IN YOUR APP

	}

	@Test
	public void printBody() {

		given()
		.relaxedHTTPSValidation()
		.when()
		.log().all()
				// .queryParam("per_page",2)
				.get("/posts")
				.body()
				.prettyPrint();

	}

	@Test
	public void simpleGetRequestForSingleItem() {

		given()
		.relaxedHTTPSValidation()
		.when()
		.log()
		.all()
				// .queryParam("per_page",2)
		.pathParam("value", 8)
		.get("/posts/{value}")
				// .get("/posts/{whatever}" , 24)
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(8))
		// .body("title.rendered", is("Vadym title") )
		// .body("sticky", is(false))

		;

		
	}
	
	
	@Test
	public  void simplePostTest() {
		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("username", "username")
		.contentType(ContentType.JSON)
		.when()
		.body("{\n" + 
				"    \"title\":\"API day3 post\" ,\n" + 
				"    \"content\": \"awesome content\",\n" + 
				"    \"status\" : \"publish\"\n" + 
				"\n" + 
				"}")
		.post("/posts").then().statusCode(201);
		
		
	}
	
	@Test
	public  void myOwnPostTest() {
		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("username", "username")
		.contentType(ContentType.JSON)
		.when()
		.body("{\n" + 
				"    \"title\":\"MY BEST DAY IS COMING\" ,\n" + 
				"    \"content\": \"I KNOW THAT\",\n" + 
				"    \"status\" : \"publish\"\n" + 
				"\n" + 
				"}")
		.log().all()
		.post("/posts").then().statusCode(201);
		
		
	}
	
	
	@Test
	public void simplePutTest() {
		
		
		
		
	}
	
	@Test
	public void simpleDeleteTest() {
		
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("username", "username")
		.when()
		.pathParam("deleteID", 8)
		.queryParam("force", true)
		.delete("/posts/{deleteID}")
		.then().statusCode(200);
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
