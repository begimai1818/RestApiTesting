package restapi.testing.users;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
//import org.junit.BeforeClass;
//import org.junit.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

public class UserActionTest {
	
	@BeforeClass
	public static void setUp() {

		RestAssured.baseURI = "https://www.batch8api.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2/";
	}
	
	
	 @Test
	  public void testPublicUserGetOnlyAdminProfileInfo() {
	    
	    given()
	      .relaxedHTTPSValidation().
	      //.auth().preemptive().basic("username", "username").
	    when()
	      .log().all()
	      .get("/users").
	    then()
	      //.statusCode(HttpStatus.SC_OK)
	      .statusCode(200) 
	      //.contentType(ContentType.JSON)
	      .header("Content-Type", "application/json; charset=UTF-8")
	      .body("id", hasSize(1) )
	      .body("name", hasItem("admin") )
	      
	    ;

	  }
	  
	  @Test
	  public void testPublicUserShouldNotBeAble_CreateNewUser() {
	    
	    given()
	      .relaxedHTTPSValidation().
	      //.auth().preemptive().basic("username", "username").
	    when()
	      .log().all()
//	      .body("{" +
//	          "  \"username\" : \"user_b\" ,\n" + 
//	          "  \"name\" : \"user b\" ,\n" + 
//	          "  \"first_name\" : \"super b\", \n" + 
//	          "  \"last_name\" : \"user last\" ,\n" + 
//	          "  \"email\" : \"s@aaa.com\" ,\n" + 
//	          "  \"roles\" : \"author\" ,\n" + 
//	          "  \"password\" : \"user\" \n" + 
//	          "}")
	      
	      .body("{" + 
	      		"	\"username\" : \"user_b\" ,\n" + 
	      		"	\"name\" : \"user b\" ,\n" + 
	      		"	\"first_name\" : \"super\", \n" + 
	      		"	\"last_name\" : \"user \" ,\n" + 
	      		"	\"email\" : \"s@jj.com\" ,\n" + 
	      		"	\"roles\" : \"author\" ,\n" + 
	      		"	\"password\" : \"user\" \n" + 
	      		"}")
	      .contentType(ContentType.JSON)
	      .post("/users").
	    then()
	      //.statusCode(HttpStatus.SC_UNAUTHORIZED)
	      .statusCode(401) 
	      .contentType(ContentType.JSON)
//	      .header("Content-Type", "application/json; charset=UTF-8")
	      .body("code", is("rest_cannot_create_user") )
//	      .body("name", hasItem("username") )
//	      
	    ;

	  }
	  
	  
	  @Test
	  public void testAdminUserShouldBeAble_CreateNewUser() {
	    
	    given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("username", "username").
	    when()
	      .log().all()
	      .body("{" +
		      		"	\"username\" : \"user_c\" ,\n" + 
		      		"	\"name\" : \"user c\" ,\n" + 
		      		"	\"first_name\" : \"super\", \n" + 
		      		"	\"last_name\" : \"user \" ,\n" + 
		      		"	\"email\" : \"s@jjjjj.com\" ,\n" + 
		      		"	\"roles\" : \"author\" ,\n" + 
		      		"	\"password\" : \"username\" \n" + 
		      		"}")
	      .contentType(ContentType.JSON)
	      .post("/users").
	    then()
	      //.statusCode(HttpStatus.SC_CREATED)
	        .statusCode(201) 
	      .contentType(ContentType.JSON)
//	      .header("Content-Type", "application/json; charset=UTF-8")
	      .body("username", is("user_c") )
//	      .body("name", hasItem("admin") )
//	      
	    ;

	  }
	  
		@Test
		public void adminUser_ShouldBeAbleto_UpdateExistingUser() {
			 given()
		      .relaxedHTTPSValidation()
		      .auth().preemptive().basic("username", "username").
		    when()
		      .log().all()
		     
//		      .body("{" +
//		      		"	\"username\" : \"user_c\" ,\n" + 
//		      		"	\"name\" : \"user c\" ,\n" + 
//		      		"	\"first_name\" : \"super\", \n" + 
//		      		"	\"last_name\" : \"user \" ,\n" + 
//		      		"	\"email\" : \"s@jjjjj.com\" ,\n" + 
//		      		"	\"roles\" : \"author\" ,\n" + 
//		      		"	\"password\" : \"username\" \n" + 
//		      		"}")
		      
		      
		      .body("{" + 
		      		
		      		"	\"first_name\" : \"super updated\", \n" + 
		      		"	\"last_name\" : \"user last\" ,\n" + 
		      		"	\"email\" : \"s@g.com\" ,\n" + 
		      		"	\"roles\" : \"author\" ,\n" + 
		      		"	\"password\" : \"user\" \n" + 
		      		"}")
		      .pathParam("newID", 2)
		      .contentType(ContentType.JSON)
		      .put("/users/newID")
		      .then()
		      .statusCode(200)
		      .contentType(ContentType.JSON);
			
			
		}
		
		@Test
		public void adminUser_ShouldBeAbleto_DeleteExistingUser() {
			
		}
		
		@Test
		public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {
			
		}	
		
	

}
