package com.rishi.sit.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.rishi.sit.utils.DbUtil;
import io.restassured.RestAssured;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserApiTests {

  @BeforeClass
  public void setup() throws Exception {
    DbUtil.seed();
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
  }

  @DataProvider(name = "ids")
  public Object[][] ids() {
    return new Object[][]{{1}, {2}, {3}};
  }

  @Test(dataProvider = "ids", description = "TC-API-01 API user record matches DB expected record")
  public void tcApi01_userMatchesDb(int id) throws Exception {
    Map<String, String> db = DbUtil.getUser(id);
    given().when().get("/users/" + id)
        .then().statusCode(200)
        .body("name", equalTo(db.get("name")))
        .body("email", equalTo(db.get("email")));
  }
}