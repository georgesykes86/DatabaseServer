package com.georgesykes.databaseserver.feature;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.georgesykes.databaseserver.Server;
import io.restassured.RestAssured;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ControllerFeatureTest {

  private Server server;

  @BeforeAll
  public void setUp() throws IOException {
    server = new Server();
    server.start();
    RestAssured.port = 4000;
  }

  @AfterAll
  public void tearDownn() {
    server.stop();
  }

  @Test
  public void setsAndGetsAKey(){
    given()
        .param("setter", "value")
        .when()
        .get("set")
        .then()
        .statusCode(200);
    given()
        .param("key", "setter")
        .when()
        .get("get")
        .then()
        .statusCode(200)
        .body(equalTo("value"));
  }

  @Test
  public void setsAndGetsMultipleKeys(){
    given()
        .param("key1", "value1")
        .param("key2", "value2")
        .when()
        .get("set")
        .then()
        .statusCode(200);
    given()
        .param("key", "key1")
        .when()
        .get("get")
        .then()
        .statusCode(200)
        .body(equalTo("value1"));
    given()
        .param("key", "key2")
        .when()
        .get("get")
        .then()
        .statusCode(200)
        .body(equalTo("value2"));
  }


}
