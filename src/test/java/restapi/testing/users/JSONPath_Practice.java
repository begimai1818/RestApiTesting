package restapi.testing.users;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONPath_Practice {

	
	Faker faker = new Faker();
	
	@BeforeClass
	public static void init() {

		RestAssured.baseURI = "https://www.batch8api.dev.cc";
		RestAssured.basePath = "/wp-json/wp/v2"; 
		RestAssured.useRelaxedHTTPSValidation();

	}
	
	 @Test
	  public void simpleGetRequest() {
	    
	    Response response = 
	        
	    given()
	      //.relaxedHTTPSValidation()
	    .when()
	      .log().all()
	      //.queryParam("per_page",2)
	      .get("/posts")

	      ;
	    
	    JsonPath jp = response.jsonPath();
	    // USE JSON PATH TO FIND OUT FIRST AUTHOR 
	    //System.out.println(  jp.getInt("[0].author")  );
	    System.out.println(  jp.getInt("author[0]")  );
	    
	    List<Object> lst = jp.getList("author") ; 
	    
	    List<Integer> lstNum = jp.getList("author",Integer.class) ;
	    System.out.println(lstNum);
	    
	    
	    List<String> titles = jp.getList("title.rendered",String.class) ; 

	    
	    //
	    //List<Integer> lstCount = jp.getList("_links.version-history[0].count",Integer.class) ;
	    List<Object> lstCount = jp.getList("_links.version-history.count") ;
	    
	    System.out.println(lstCount);
	    
	    // USE JSON PATH TO FIND OUT ALL OF THE AUTHOR 
	    // USE JSON PATH TO FIND OUT version-history count 
	    
	  }
	  
	  @Test
	  public void getAllDriverFirstName() {
	    
	    Response response = given()
	    		.when()
	    		.get("http://ergast.com/api/f1/drivers.json");
	    
	    
	    
	    
	    JsonPath jPath = response.jsonPath();
	    
	    List<String> givenName = jPath.getList("MRData.DriverTable.Drivers.givenName", String.class);
	    		
	    System.out.println(givenName);
	    List<Object> lst4 = jPath.get("MRData.DriverTable.Drivers.findAll{whatever-> whatever.givenName=='George'}");
//	    System.out.println(jPath.get("MRData.DriverTable.Drivers[0]"));
	    
	    
	    
	    Map map1 = jPath.getMap("MRData.DriverTable.Drivers[0]") ; 
	    System.out.println(map1);
	    System.out.println(map1.keySet());
	    
	    Map<String, String> map2 = jPath.getMap("MRData.DriverTable.Drivers[0]",String.class, String.class) ;  
	    System.out.println( map2.values() );
	    
	    System.out.println( jPath.getString("MRData.DriverTable.Drivers[1].givenName" )) ; 
	    
	    // JSONPATH That rest assured use is the GPath from groovy 
	    // Predicate 
	    List<Object> lst5 = jPath.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George'}") ; 
	    System.out.println(lst5);
	    
	    List<Object> lst6 =
	    		jPath.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George' && it.nationality=='American' }") ; 
	    
	    System.out.println(lst6);
	    // find out all the driver that has 3 letters given name   it.length()
	    
	    
	    List<Object> lst7 = jPath.get("MRData.DriverTable.Drivers.findAll{it.givenName.length()==3}");
	    
	    System.out.println(lst7);
	    
	    List<Object> lst8 = jPath.getList("MRData.DriverTable.Drivers.findAll{  driver-> driver.givenName.length()==3 }.familyName ") ;
	    
	    System.out.println(lst8);
	    
	    // find out all the driver that has 3 letters given name   it.givenName.length()
	    

	    // single json object ---> Driver object in java 
	    
	    Driver driverObj = jPath.getObject("MRData.DriverTable.Drivers[1]", Driver.class) ; 
	    System.out.println( driverObj );
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  
	    
	  
	    
	    
	  }
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
