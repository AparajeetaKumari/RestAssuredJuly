package com.qa.petstore.tests;

import Pojos.Petstore;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PetStorePojoTest {


    Petstore petstore = new Petstore(111,"Vijay","vijay","Kumar","vijay@gmail.com","abcdefeh","3456789011",1);
    Petstore updatedpetstore = new Petstore(111,"Ranjan","Ranjan","Kumar","Ranjan@gmail.com","abcdefeh","3456789011",1);



    @BeforeTest
    public void setup(){
        RestAssured.baseURI ="https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void createTestUser(){
        Response res =  given().log().all().contentType("application/json").body(petstore).when().post("/user");

        res.getBody().prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);
        String id = res.jsonPath().getString("message");
        Assert.assertNotNull(id);

    }


    @Test(priority = 2)
    public void getUser(){
        Map<String,String> hvalue = new HashMap<>();
        hvalue.put("contentType","application/json");
        Response res = given().log().all().headers(hvalue).pathParam("username",petstore.getUsername()).when().get("/user/{username}");
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertTrue(res.getStatusLine().contains("OK"));
        String username = res.jsonPath().getString("username");
        Assert.assertEquals(username,petstore.getUsername());
        Assert.assertTrue(res.getContentType().contains("json"));

    }


    @Test(priority = 3)
    public void updateUser(){
        Response res= given().contentType("application/json").pathParam("username",petstore.getUsername()).when().body(updatedpetstore).put("/user/{username}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);

    }


    @Test(priority = 4)
    public void delete(){

        Response res = given().contentType("application/json").pathParam("username",updatedpetstore.getUsername()).when().delete("/user/{username}");
        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(),200);


    }

}
