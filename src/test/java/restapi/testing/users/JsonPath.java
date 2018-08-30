package restapi.testing.users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
//import io.restassured.path.json.JsonPath;


import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import org.junit.BeforeClass;
//import org.junit.Test;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JsonPath {
	Faker faker = new Faker();
	int newUserID;

	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "https://www.batch8api.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2"; 
		

	}

	@Test
	public void testJSONPath() {

		Response response =

				given().relaxedHTTPSValidation().
				// .auth().preemptive().basic("admin", "admin").
						when().log().all().get("/users/{id}", 1);

		System.out.println(response.asString());
		response.prettyPrint();
    	//    JsonPath jP = response.jsonPath() ; 

    	    
//	    System.out.println( jsonPath.getInt("id") );
		// title , slug , self

	}

}
