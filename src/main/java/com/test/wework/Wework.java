package com.test.wework;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

public class Wework {

    private static String token;

    public static String getWeworkToken(String secret){
         return RestAssured.given()
                .log().all()
                .params("corpid", WeworkConfig.getInstance().corpid)
                .param("corpsecret", secret)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .body("errcode", equalTo(0))
                 .extract().path("access_token");

    }

    public static String getToken(String secret){
        if(token == null){
            token = getWeworkToken(secret);
        }
        return token;
    }
}
