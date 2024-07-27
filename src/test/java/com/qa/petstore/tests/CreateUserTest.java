package com.qa.petstore.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CreateUserTest {
    String userName = "Vijay";
    String id;



    @BeforeTest
    public void setup(){
        RestAssured.baseURI ="https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void createTestUser(){
        Response res =  given().log().all().contentType("application/json").body("{\n" +
                "\"id\": 786,\n" +
                "\"username\": \"" + userName+ "\",\n" +
                "\"firstName\": \"Mukesh\",\n" +
                "\"lastName\": \"Otwani\",\n" +
                "\"email\": \"mukesh@gmail.com\",\n" +
                "\"password\": \"mukesh@123\",\n" +
                "\"phone\": \"9090909090\",\n" +
                "\"userStatus\": 1\n" +
                "}").when().post("/user");

        res.getBody().prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);
        id = res.jsonPath().getString("message");
        Assert.assertNotNull(id);

    }


    @Test(priority = 2)
    public void getUser(){
        Map<String,String> hvalue = new HashMap<>();
        hvalue.put("contentType","application/json");
        Response res = given().log().all().headers(hvalue).pathParam("username",userName).when().get("/user/{username}");
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertTrue(res.getStatusLine().contains("OK"));
        String username = res.jsonPath().getString("username");
        Assert.assertEquals(username,userName);
        Assert.assertTrue(res.getContentType().contains("json"));

    }


    @Test(priority = 3)
    public void updateUser(){
       Response res= given().contentType("application/json").pathParam("username",userName).when().body("{\n" +
                "\"id\": 786,\n" +
                "\"username\": \""+userName+ "\",\n" +
                "\"firstName\": \"Mukesh\",\n" +
                "\"lastName\": \"OtwaniTest\",\n" +
                "\"email\": \"mukesh123456@gmail.com\",\n" +
                "\"password\": \"mukesh@123\",\n" +
                "\"phone\": \"9090909090\",\n" +
                "\"userStatus\": 1\n" +
                "}").put("/user/{username}");

       res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);

    }


    @Test(priority = 4)
 public void delete(){

        Response res = given().contentType("application/json").pathParam("username",userName).when().delete("/user/{username}");
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);


 }

}
