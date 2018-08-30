package rest.api.testing;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
//import org.junit.BeforeClass;
//import org.junit.Test;
import io.restassured.RestAssured;

public class RestTest {
	
	@BeforeClass
	public static void SetUp() {
	RestAssured.baseURI = "https://www.batch8api.dev.cc";
	RestAssured.basePath = "/wp-json/wp/v2/";
		
		
		
	}
	
	//given  rest end point http://73.166.37.2:1000/ords/hr/employees/
	//When I send a HTTP Get request to the server
	//Then I should get 200 OK result
	
	@Test
	public void firstTest() {
		
		when().get("http://73.166.37.2:1000/ords/hr/employees/100")
		.then()
		.statusCode(200);
		
		
	}
	
	@Test
	public void secondTest() {
		
		when().get("http://73.166.37.2:1000/ords/hr/departments/110")
		.then()
		.statusCode(200);
		
	}
	
	
	/*
	 * Given  https://www.batch8api.dev.cc/wp-json/wp/v2/posts
	 * When I send 
	 * */
	
	@Test
	public void givenTest() {
		given()
		.relaxedHTTPSValidation()//-->Secure connection
		.when()
		.get("https://www.batch8api.dev.cc/wp-json/wp/v2/posts")
		.then()
		.statusCode(200);
		
		
	}
	
	/*
	 *   Given rest end point 
	 *   https://www.batch8api.dev.cc/wp-json/wp/v2/posts/8
	 *   When I send a HTTP Get request to the server
	 *   Then I should get 200 OK result status code
	 *   
	 * */
	
	@Test
	public void idTest() {
		
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.batch8api.dev.cc/wp-json/wp/v2/posts/8")
		.then()
		.statusCode(200)
		.and()
		.body("id", equalTo(8))
		.body("title.rendered", equalTo("posting using rest service"))
		;
		
		
	}
	
	/*Given rest end point
	 * http://73.166.37.2:1000/ords/hr/jobs/ACXZ_DEV
	 * 
	 * */
	
	@Test
	public void idTestwithLogDetail() {
		
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.batch8api.dev.cc/wp-json/wp/v2/posts/8")
		.then()
		.log()
		.all()
		.statusCode(200)
		.and()
		.body("id", equalTo(8))
		.body("title.rendered", equalTo("posting using rest service"))
		;
		
		
		
		
	}
	
	@Test
	public void idTestwithLogDetailWithPath() {
		
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.batch8api.dev.cc/wp-json/wp/v2/posts/{id}", 8)
		.then()
		.log()
		.all()
		.statusCode(200)
		.and()
		.body("id", equalTo(8));
		
		
		
		
	}
	
	
	@Test
	public void RestTest() {
		
		
		
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("posts/{id}", 8)
		.then()
		.statusCode(200);
		
		
		//201-->creating cf                                                          
		
		
	}
	
	
	@Test
	public void testWithHamcrest() {
		int a = 5, b = 5, c = 6;
		
		assertTrue(a==b);
		assertEquals(a, c);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
