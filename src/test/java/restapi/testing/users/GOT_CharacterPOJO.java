package restapi.testing.users;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GOT_CharacterPOJO {

	@Test
	public void databindTestWithCollection() throws Exception {

		String str = " [\n" + "    {\n" + "        \"_id\": \"56ffc5be043244081938576d\",\n"
				+ "        \"male\": true,\n" + "        \"house\": \"House Hightower\",\n"
				+ "        \"slug\": \"Abelar_Hightower\",\n" + "        \"name\": \"Abelar Hightower\",\n"
				+ "        \"__v\": 0,\n" + "        \"pageRank\": 2.5,\n" + "        \"books\": [\n"
				+ "            \"The Hedge Knight\"\n" + "        ],\n"
				+ "        \"updatedAt\": \"2016-04-02T13:14:38.834Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.834Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    },\n" + "    {\n"
				+ "        \"_id\": \"56ffc5be043244081938576e\",\n" + "        \"male\": true,\n"
				+ "        \"house\": \"House Frey\",\n" + "        \"slug\": \"Addam_Frey\",\n"
				+ "        \"name\": \"Addam Frey\",\n" + "        \"__v\": 0,\n" + "        \"pageRank\": 4.5,\n"
				+ "        \"books\": [\n" + "            \"The Mystery Knight\"\n" + "        ],\n"
				+ "        \"updatedAt\": \"2016-04-02T13:14:38.875Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.875Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    },\n" + "    {\n"
				+ "        \"_id\": \"56ffc5be043244081938576f\",\n" + "        \"male\": true,\n"
				+ "        \"slug\": \"Addam\",\n" + "        \"name\": \"Addam\",\n" + "        \"__v\": 0,\n"
				+ "        \"pageRank\": 1.5,\n" + "        \"books\": [\n" + "            \"The Mystery Knight\"\n"
				+ "        ],\n" + "        \"updatedAt\": \"2016-04-02T13:14:38.877Z\",\n"
				+ "        \"createdAt\": \"2016-04-02T13:14:38.877Z\",\n" + "        \"titles\": [\n"
				+ "            \"Ser\"\n" + "        ]\n" + "    } ] ";

		ObjectMapper om = new ObjectMapper();
		Person[] arr = om.readValue(str, Person[].class);

		System.out.println("Array ---> " + Arrays.toString(arr));

		String jsonArray = om.writeValueAsString(arr);
		System.out.println("JSON Array ---> " + jsonArray);

		// converting to an Arraylist instead of Array
		// we need to use a typeReference object --> Type reference is a abstact class
		// with no abstract method thats why you see body{}
		List<Person> lst = om.readValue(str, new TypeReference<List<Person>>() {
		});

		System.out.println("List ---> " + lst);
	}

	/// Careat GOT_Character ---> id , male , house , name

}

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties("extraMessage")
class Converting {

	String name;
	String books;
	String male;
	String house;
	String slug;

	public Converting() {
		// TODO Auto-generated constructor stub
	}

	public Converting(String name, String books, String male, String house, String slug) {
		super();
		this.name = name;
		this.books = books;
		this.male = male;
		this.house = house;
		this.slug = slug;
	}

	@Override
	public String toString() {
		return "Converting [name=" + name + ", books=" + books + ", male=" + male + ", house=" + house + ", slug="
				+ slug + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}
