import com.test.wework.contact.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


/**
 * Department Tester.
 *
 * @author sijiacan
 * @version 1.0
 * @since <pre>十月 16, 2021</pre>
 */
public class DepartmentListTest {

    Department dt;
    long random = System.currentTimeMillis();

    @Before
    public void before() throws Exception {
        if(dt == null){
            dt = new Department();
        }

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getList(String id)
     */
    @Test
    public void testGetList() throws Exception {
//TODO: Test goes here...
        dt.getList("").then()
                .log().all()
                .statusCode(200);
//                .body("department[1].name", equalTo("财务部"));

//        dt.getList("7").then()
//                .body("department.name[0]", equalTo("汽车运营"));
    }

    @Test
    public void testCreate() {
        dt.create("test1","1")
                .then().log().all()
                .body("errcode",equalTo(60008));
    }

    //@Test
    //汉字，需要utf8编码
    public void testCreateChinese() {
        dt.create("测试1","1")
                .then().log().all()
                .body("errmsg",hasItem("department existed"));
    }

    @Test
    public void testCreateByMap() {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("name","map"+random);
        hm.put("parentid","1");
        dt.createByMap(hm)
                .then().log().all()
                .body("errcode",equalTo(60008));
    }

    //@Test
    public void testUpdate(){
        String departmentname = "si";
        String name="si-test";
        dt.create(departmentname,"1");
        String id = dt.getList("").path("department.find{ it.name=='" + departmentname + "'}.id").toString();
        dt.update(id,name)
                .then().log().all()
                .body("errmsg",equalTo("updated"));
    }

    //@Test
    public void testDelete(){
        String departmentname = "si";
        dt.create(departmentname,"1");
        String id = dt.getList("").path("department.find{ it.name=='" + departmentname + "'}.id").toString();
        dt.delete(id)
                .then().log().all()
                .body("errmsg",equalTo("deleted"));

    }

} 
