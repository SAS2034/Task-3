package org.example;
//package restservice;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import org.testng.annotations.Test;

public class RestTest {

   private static final String URL = "https://jira.moscow.alfaintra.net/rest/api/2/issue/SFAIMP-1219/comment";
  // private static final String URL = "https://jira.moscow.alfaintra.net/rest/api/2/issue/DROCGOALS-1601/comment";
  // private static final String LOGIN = "U_BB034";
  // private static final String PASSWORD = "2034_Svet04ka";
  private final String LOGIN = System.getProperty("login");
   private final String PASSWORD = System.getProperty("password");

    @Test
    public void testAddComment() {
// Отключение проверки сертификата.

        RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        String commentBody = "Привет!\\n Светлана была здесь.";

        RestAssured.given()
                .auth().preemptive().basic(LOGIN, PASSWORD)

                .header("Content-Type", "application/json")
                .body("{\"body\": \"" + commentBody + "\"}")
                .when()
                .post(URL)
                .then()
                .statusCode(201);
    }
}