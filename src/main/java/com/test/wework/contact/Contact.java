package com.test.wework.contact;

import com.test.wework.Restful;
import com.test.wework.Wework;
import com.test.wework.WeworkConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Contact extends Restful {
    public Contact(){
       reset();
    }

    public void reset(){
        rs = given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("access_token", Wework.getToken(WeworkConfig.getInstance().contactSecret));
    }
}
