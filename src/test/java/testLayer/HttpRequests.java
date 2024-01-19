package testLayer;


import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;

//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HttpRequests {
	
	@Test
	public void GETusers()
	{
		
		
		given()
		      .pathParam("path", "users")
		      .queryParam("page", 2)
		.when()
		      .get("https://reqres.in/api/{path}")
		.then()
		      .log().cookies();
		      //.statusCode(200)
		     // .body("data[2].first_name",equalTo("Tobias"));
		      
		
	}
	
	//@Test  //How to check if the URL contains any cookies or not from the response variable 
	public void GETheaders()
	{
		
		
		Response res=given()
		      
		.when()
		      .get("https://reqres.in/api/users/2");
				     
		Headers myheader=res.getHeaders();
		
		for(Header h:myheader)
		System.out.println(h.getName()+" value is "+h.getValue());
		
		
	}
	
	@Test
	public void GETusers1()
	{
		
		Response res=given()
		      .pathParam("path", "users")
	          .queryParam("page", 2)
		.when()
		      .get("https://reqres.in/api/{path}");
		
		
		System.out.println(res.getBody().asPrettyString());
		
	}
	
	@Test
	public void GETusers2()
	{
		
		Response res=given()
				  .contentType(ContentType.JSON)
			      .pathParam("path", "users")
		          .queryParam("page", 2)
			.when()
			      .get("https://reqres.in/api/{path}");
		
		//List names=res.jsonPath().getList("data.first_name");
		//System.out.println(names);
		
		String name=res.jsonPath().get("data[2].first_name");
		System.out.println(name);
		Assert.assertEquals("Tobias", name);
		
		
		JSONObject jo=new JSONObject(res.asPrettyString());
		
		//System.out.println(jo.getJSONArray("data").getJSONObject(0).get("first_name"));
		//System.out.println(jo.getJSONArray("data"));
		
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
			   
			  //jo.getJSONArray("data").getJSONObject(i).get("first_name").toString()
			if(jo.getJSONArray("data").getJSONObject(i).get("first_name").toString().equals("Tobias1")) {
				System.out.println("His email is  "+jo.getJSONArray("data").getJSONObject(i).get("email").toString());
				break;
			}
			
			
		}
		
	}

}
