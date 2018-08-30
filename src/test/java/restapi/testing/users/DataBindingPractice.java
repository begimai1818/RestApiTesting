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

public class DataBindingPractice {

	Faker faker = new Faker();
	int newUserID;

	@BeforeClass
	public void init() {

		RestAssured.baseURI = "https://www.batch8api.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2";

	}

	@Test
	public void test() {

		given().relaxedHTTPSValidation().auth().preemptive().basic("admin", "admin").contentType(ContentType.JSON)
				.body("{\n" + "  \n" + "  \"title \":\"abc\",\n" + "  \" content\":\"nmy super\",\n"
						+ "  \"status\":\"publish\"\n" + "\n" + "}")
				.when().log().all().post("/posts").then()
				// .statusCode(HttpStatus.SC_CREATED)
				.statusCode(201).header("Content-Type", containsString("application/json"))
				.body("title.raw", is("abc"));

	}

}
