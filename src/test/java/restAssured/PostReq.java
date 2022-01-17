package restAssured;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import junit.framework.Assert;

public class PostReq {

	@Test
	public void verifyStatusNContent() {
		
		//setup auth details
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("sample_usename");
		basicAuth.setPassword("sample_pass");
		
		RestAssured.authentication=basicAuth;
		
		//set baseuri
		RestAssured.baseURI="https://reqres.in/";
		
		//create json object used as request body
		JSONObject jsonobj = new JSONObject();
		
		//create a map to create request body
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		//map request body to jsonobj and send request using POST method
		Response res = RestAssured.given().
				header("Content-Type","application/json").
				body(JSONObject.toJSONString(map)).
				request(Method.POST,"api/users");
		
		//print status code and status line
		System.out.println(res.getStatusCode()+""+ res.getStatusLine());
		
		Assert.assertEquals(res.getStatusCode(),201);
		Assert.assertEquals(res.jsonPath().get("name"),"morpheus");
		Assert.assertEquals(res.jsonPath().get("job"),"leader");
		
	}
}
