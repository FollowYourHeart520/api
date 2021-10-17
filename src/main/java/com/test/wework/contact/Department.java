package com.test.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import static io.restassured.RestAssured.requestSpecification;

public class Department extends Contact{

    public Response getList(String id){
        return rs
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().statusCode(200).extract().response();
    }

    public Response create(String name,String id){

//        useRelaxedHTTPSValidation(); //信任https请求

        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/department.json"))
                .set("$.name",name)
                .set("$.parentid",id)
                .jsonString();

//        return given()
////                .log().all()
////                .contentType(ContentType.JSON)
////                .queryParam("access_token",Wework.getToken(WeworkConfig.getInstance().contactSecret))
        return rs
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();
    }

    public Response createByMap(HashMap<String,Object> map){
        DocumentContext parse = JsonPath.parse(this.getClass().getResourceAsStream("/data/department.json"));
        map.entrySet().forEach(entry->{
                parse.set(entry.getKey(),entry.getValue());
        });

        return rs.body(parse.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();
    }

        public Response update(String id,String name){
        String updatebody = JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.id", id)
                .set("$.name",name)
                .jsonString();

        return rs
                .body(updatebody)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().statusCode(200)
                .extract().response();

    }

    public Response delete(String id){
        return requestSpecification
                .param("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().statusCode(200).extract().response();

    }

}
