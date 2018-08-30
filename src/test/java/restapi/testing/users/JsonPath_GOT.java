package restapi.testing.users;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class JsonPath_GOT {
	
	/*https://api.got.show/api/characters/*/
	
	@Test
	public void testJsonPath() {
		
		String url = "https://api.got.show/api/characters/";
		
		Response response = given().relaxedHTTPSValidation()
		.accept(ContentType.JSON)
		.and()
		.get(url);
		
		String responseString = response.asString();
		
		
		
		
		
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
