import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;


public class SmokeApiTests {
    @Test
    void simpleTest() {
        String body = """
                  {
                    "id": 22424,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                  }""";

        Response response = given()
                                .header("accept", "application/json")
                            .header("Content-Type", "application/json")
                            .baseUri("https://petstore.swagger.io/v2/")
                            .when()
                                .body(body)
                                .post("user")
                            .andReturn();

        Assertions.assertEquals(200,response.getStatusCode());
        Assertions.assertEquals("unknown",response.jsonPath().getString("type"));
        Assertions.assertEquals(200,response.jsonPath().getInt("code"));
        Assertions.assertNotNull(response.jsonPath().getString("message"));

    }
}
