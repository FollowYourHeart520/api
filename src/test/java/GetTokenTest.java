import com.test.wework.Wework;
import com.test.wework.WeworkConfig;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetTokenTest {
    @Before
    public void config(){
        RestAssured
                .given()
                .filter((req,res,ctx)->{
                    if(req.getURI().contains("weixin")){
                        req.header("access_token", new Wework().getToken(WeworkConfig.getInstance().Secret));
                        Response re = ctx.next(req,res);
                        return re;
                    }
                    return ctx.next(req,res);
                })
                .filter((req,res,ctx)-> {
                            Response re = ctx.next(req, res);
                            ResponseBuilder clone = new ResponseBuilder().clone(re);
                            String s = new String(Base64.getDecoder().decode(re.body().asString().trim()));
                            clone.setBody(s);
                            Response ret = clone.build();
                            return ret;
                        });

//
//                });

    }

    @Test
    public void testGetToken(){
        Wework ww = new Wework();
        String token = ww.getToken(WeworkConfig.getInstance().Secret);
        System.out.println(token);
        assertThat(token,not(CoreMatchers.equalTo(null)));
    }
}
