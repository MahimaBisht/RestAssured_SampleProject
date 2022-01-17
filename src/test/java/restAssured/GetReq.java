package restAssured;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetReq {

	String resString;
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI="https://reqres.in/api/";
	}
	
	@Test(priority=0)
	 public void readReswith_reqSpecification() {
		
		RequestSpecification reqSpeci = RestAssured.given();
		
		Response response = reqSpeci.request(Method.GET,"users?page=2&id=7");
		
		resString = response.getBody().asString();
	}
	
	@Test(priority=100)
	public void readReswithGET_asString() {
		
		resString= RestAssured.get("users?page=2&id=7").asString();
	}
	
	@Test(priority=200)
	public void readReswithGET_asPrettyString() {
		
		resString= RestAssured.get("users?page=2&id=7").asPrettyString();
	}
	
	@Test(priority=300)
	public void assertvaluefromRes() {
		Assert.assertEquals(RestAssured.get("users?page=2&id=7").jsonPath().getString("data.first_name"),"Michael");
	}
	
	@AfterMethod
	public void print(){
		System.out.println(resString);
	}
}
