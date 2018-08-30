package restapi.testing.users;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PracticeJacksonLibary {

	@Test
	public void databindTest() throws Exception {

		String jsonString = "{\"name\" : \"Adam\",\"age\" : 21}";
		ObjectMapper om = new ObjectMapper();
		Person obj = om.readValue(jsonString, Person.class);

		System.out.println(jsonString);
		System.out.println(obj);

		String convertedStr = om.writeValueAsString(obj);
		System.out.println(convertedStr);

	}

	@Test
	public void convertJsonToJavaObject() throws Exception {

		String data = "{\r\n" + "\r\n" + "                   \"driverId\": \"abate\",\r\n" + "\r\n"
				+ "                   \"url\": \"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\",\r\n" + "\r\n"
				+ "                   \"givenName\": \"Carlo\",\r\n" + "\r\n"
				+ "                   \"familyName\": \"Abate\",\r\n" + "\r\n"
				+ "                   \"dateOfBirth\": \"1932-07-10\",\r\n" + "\r\n"
				+ "                   \"nationality\": \"Italian\"\r\n" + "\r\n" + "               }";

		ObjectMapper om = new ObjectMapper();
		Driver obj = om.readValue(data, Driver.class);

		System.out.println(obj);

		String convertStr = om.writeValueAsString(obj);
		System.out.println(convertStr);

	}

	@Test
	public void databindTestWithCollection() throws Exception {

		String jsonStringArr = "[ {\"name\":\"Adam\", \"age\":10} , {\"name\":\"john\", \"age\":12} , {\"name\":\"yuAN\", \"age\":25} ] ";

		ObjectMapper om = new ObjectMapper();
		Person[] arr = om.readValue(jsonStringArr, Person[].class);

		System.out.println("Array ---> " + Arrays.toString(arr));

		String jsonArray = om.writeValueAsString(arr);
		System.out.println("JSON Array ---> " + jsonArray);

		List<Person> ppl = Arrays.asList(new Person("aaa", 11), new Person("bbb", 12), new Person("ccc", 13));
		String jsonPPL = om.writeValueAsString(ppl);
		System.out.println("JSON List ---> " + jsonPPL);

		// converting to an Arraylist instead of Array
		// we need to use a typeReference object --> Type reference is a abstact class
		// with no abstract method thats why you see body{}
		List<Person> lst = om.readValue(jsonStringArr, new TypeReference<List<Person>>() {
		});

		System.out.println("List ---> " + lst);
	}

	@Test
	public void TestWithOnlyJsonPath() {

		Response res = given().when().get("http://ergast.com/api/f1/drivers.json");

		List<Object> pplList = res.jsonPath().getList("MRData.DriverTable.Drivers");
		System.out.println("JSONPATH LIST " + pplList);

	}

}

class Person {

	String name;
	int age;

	public Person() {
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
