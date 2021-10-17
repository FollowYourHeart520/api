import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

/**
 * 静静态导入方法，以提高使用rest-assured的效率
 * when()：触发条件
 * then()：断言
 */


public class Xueqiu {

    public static RequestSpecification req;
    public static ResponseSpecification res;

    @Before
    public void ge(){
//        System.out.println("==================111==============");

        RestAssured
                .filters((req, res, ctx) -> {
                    if (req.getURI().contains("baidu")) {
//                        req.header("access_token", new Wework().getToken());
//                        req.header("id", "test");
                        Response re = ctx.next(req, res);
                        return re;
                    }
                    return ctx.next(req,res);
                });

//        req = new RequestSpecBuilder().build();
//        req.port(80);
////        req.header("","");
////        req.cookie("","");
//
        res = new ResponseSpecBuilder().build();
        res.statusCode(200);
////        res.body("","");
    }

//    @Test
    public void testXueqiuJson(){
        useRelaxedHTTPSValidation(); //信任https请求

        //given开头表示输入数据
        given()
                .proxy("127.0.0.1")
                .log().all()
                //query请求参数
                .queryParam("q","sogoR额")
                //头信息
                .header("Cookie","acw_tc=2760827d16340949010515684ed4bb4ee4072789739390caa1c11ac93bd2e3; xq_a_token=cddd72fc3c69466c73a5a4d4ed47425fd46595e7; xqat=cddd72fc3c69466c73a5a4d4ed47425fd46595e7; xq_r_token=09a12558d29517e1a5c3914a7784318067bcd87c; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTYzNTg3NTQwNSwiY3RtIjoxNjM0MDk0ODgwMTA3LCJjaWQiOiJkOWQwbjRBWnVwIn0.SGrN2EJU3CSkp3NXhjtooRI_F_9QKMeUJyEskApvwUcmwxmM9p2HKvwhrTqf2v_nQPoWCyIeiTiuM9L91_BYSqamkI6QXwFm16d2ClsL7JW8BNKVnidAskPauNj0qN4W0yzOzA3bh924ZSrDXkPUGLwmw4x3zkELc5bikH3bjZvUBZP3Ng6tJudnS-ubGeAHbXpWmg5TYxcMhjSsff3yQ6LJuHVNvbjc2-vXycSh0zg4QVXF2St9W9ntYlkosx7Xh5G0Qe3q-JRirTEOixSmQjeChmP4PQDibdlHrTFeiggMCS6CIG2h9JvjVdsSc8Wg0EKUljb0w3NXNtqw6dgniA; u=391634094901059; Hm_lvt_1db88642e346389874251b5a1eded6e3=1634094902; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1634094902; device_id=50485e7e131714a19505907e2eeacdf9")
                //when表示触发条件
         .when()
                .get("https://xueqiu.com/cube/search.json")
                //then对结果断言
         .then()
                .log().all()
//                .spec(res)
//                .statusCode(200)
                .body("q",equalTo("sogo"))
                .body("list[1].style.name",equalTo("稳如泰山"))
                .body("list.findAll{it.name == 'sogo'}.owner.verified_description",hasItem("雪球实盘大赛参赛选手"));
//                .body("list.style.findAll{style->style.degree>20 && style.degree<40}.degree",hasItem(30));
//                list.name：根节点.子节点
//                body() 可以无限的写下去
//                hasItems("reference")，前面的key对应的value是在这个括号里
        /**
         * 对json的断言
         *
         * book节点下，是四个对立的数组元素，每个元素中都有category、author、title、price,我要断言book中的第一个author的值是Nigel Rees。
         * .body("store.book[0].author",equalTo("Nigel Rees"))
         * "store.book[0].author" :book数组的第1个元素下的author，第2个元素可以用book[1]……，最后一个元素可以使用book[-1]
         *
         */

    }

//    @Test
    public void testBaiduXml() {
        given()
                .log().all()
                .proxy("127.0.0.1")
                .spec(req)
                .header("Cookie","BIDUPSID=8568EEEBCEF6664EDB0970FE94DADF00; PSTM=1629707936; BAIDUID=8568EEEBCEF6664EA5B0DB8C508E8391:FG=1; BD_UPN=123253; __yjs_duid=1_93550dd9e9215fa5aa2cd5e694a869751629779838923; delPer=0; BD_CK_SAM=1; BAIDUID_BFESS=82C4161087C8836EE1EDA3A519C432E9:FG=1; BD_HOME=1; Hm_lvt_aec699bb6442ba076c8981c6dc490771=1632988961,1633867141; Hm_lpvt_aec699bb6442ba076c8981c6dc490771=1633867141; shifen[71327539383_87851]=1633867144; BCLID=10912376100843961767; BDSFRCVID=kJtOJeC62iRhuobHDr1LKwNVOmKKQOoTH6aolCcFqi-T381YdyPoEG0PDf8g0KubhaS4ogKK0mOTHv_F_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tR3aQ5rtKRTffjrnhPF3ynIFXP6-hnjy3bRkX4Q4WInpqtK4255kWq4Wbttf5q3RymJ42-39LPO2hpRjyxv4y4Ldj4oxJpOJ-bCL0p5aHl51fbbvbURvD-ug3-AqBM5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoK8yJCt2MDPrMJbE5-QH-xQ0KnLXKKOLVbc4bp7keq8CDRKKDhLQM4AOBlIOb652VP3d2nQUjxo2y5jHhIuejno9XTQj0C72KqbufP5psIJMqq_WbT8ULtce0l5XaKviaKJEBMb1SJvDBT5h2M4qMxtOLR3pWDTm_q5TtUJMeCnTD-Dhe6JLDNueJ6LqfK5eB4bHHJoHjJbGq4bohjPh5-O9BtQmJJrHLKnkWKQ1bhTLyJoB-TkWbR_DXqcyQg-q3R75bbQmqI5yWKclDbDyLqrf0x-jLNcuVn0MWKbDfq0424nJyUnQhtnnBpQW3H8HL4nv2JcJbM5m3x6qLTKkQN3T-PKO5bRh_CFbJI8MbKDlePQfqR0eKMoKb-LXaDTJVbvJtp7keq8CDl6bQqIdh47e-ROib652VP3OBRTUDRr2y5jH3xJbQ4rHBqvi5m6n0h_2bP5psIJMXtDWbT8Uj2chBfFLaKviaKJEBMb1SJvDBT5h2M4qMxtOLR3pWDTm_q5TtUJMeCnTD-Dhe6j3DHAetTLDfKbtBbKyfTrjDnCrMU6MXUI8LNDHalj3bCOaapb-tlRvfprKXPcvyp_ghnO7ttoyag72KtjVHx-5bKjXbbrmhUL1Db3RKjvMtg3t3JnqLKboepvoD-cc3MkBBPjdJJQOBKQB0KnGbUQkeq8CQft20b0EeMtjW6LEK5r2SC_KtK-W3f; BCLID_BFESS=10912376100843961767; BDSFRCVID_BFESS=kJtOJeC62iRhuobHDr1LKwNVOmKKQOoTH6aolCcFqi-T381YdyPoEG0PDf8g0KubhaS4ogKK0mOTHv_F_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=tR3aQ5rtKRTffjrnhPF3ynIFXP6-hnjy3bRkX4Q4WInpqtK4255kWq4Wbttf5q3RymJ42-39LPO2hpRjyxv4y4Ldj4oxJpOJ-bCL0p5aHl51fbbvbURvD-ug3-AqBM5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoK8yJCt2MDPrMJbE5-QH-xQ0KnLXKKOLVbc4bp7keq8CDRKKDhLQM4AOBlIOb652VP3d2nQUjxo2y5jHhIuejno9XTQj0C72KqbufP5psIJMqq_WbT8ULtce0l5XaKviaKJEBMb1SJvDBT5h2M4qMxtOLR3pWDTm_q5TtUJMeCnTD-Dhe6JLDNueJ6LqfK5eB4bHHJoHjJbGq4bohjPh5-O9BtQmJJrHLKnkWKQ1bhTLyJoB-TkWbR_DXqcyQg-q3R75bbQmqI5yWKclDbDyLqrf0x-jLNcuVn0MWKbDfq0424nJyUnQhtnnBpQW3H8HL4nv2JcJbM5m3x6qLTKkQN3T-PKO5bRh_CFbJI8MbKDlePQfqR0eKMoKb-LXaDTJVbvJtp7keq8CDl6bQqIdh47e-ROib652VP3OBRTUDRr2y5jH3xJbQ4rHBqvi5m6n0h_2bP5psIJMXtDWbT8Uj2chBfFLaKviaKJEBMb1SJvDBT5h2M4qMxtOLR3pWDTm_q5TtUJMeCnTD-Dhe6j3DHAetTLDfKbtBbKyfTrjDnCrMU6MXUI8LNDHalj3bCOaapb-tlRvfprKXPcvyp_ghnO7ttoyag72KtjVHx-5bKjXbbrmhUL1Db3RKjvMtg3t3JnqLKboepvoD-cc3MkBBPjdJJQOBKQB0KnGbUQkeq8CQft20b0EeMtjW6LEK5r2SC_KtK-W3f; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BAIDU_WISE_UID=wapp_1634022756198_928; MCITY=-131%3A; PSINO=2; COOKIE_SESSION=56176_0_8_9_11_39_1_2_8_7_0_2_60747_0_3_0_1634090052_0_1634090049%7C9%231576570_9_1633867052%7C3; H_PS_PSSID=34653_34448_34067_31254_34741_34597_34584_34517_34832_34813_26350_34760_34825_34419_22158_34691_34678; BA_HECTOR=0l2la42184212k85021gmd3kt0q");
        get("http://www.baidu.com/")
                .then()
                .spec(res)
                .time(lessThan(2000L));//响应时间少于2000毫秒
    }

    /**
     * filter((req,res,ctx)->{//重新生成response})
     * filter相当于过滤器、拦截器，在请求之前可以改掉请求信息，如URL，然后传给ctx调用链，调用链发送篡改后的请求，调用链获得response，可以进行篡改，篡改之后才会返回response
     */
    @Test
    public void testFilter(){
        given()
                .log().all()
//                filter((req,res,ctx)->{//重新生成response})
//                .filter((req,res,ctx)->{
//                    req.header("id","test");
//                    Response re = ctx.next(req,res);
//                    return re;
//                })
                .when()
                .get("http://www.baidu.com/")
                .then()
                .spec(res);

    }



}
