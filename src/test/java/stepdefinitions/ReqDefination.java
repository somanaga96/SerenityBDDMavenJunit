package stepdefinitions;

import com.example.generated.reqres.Data;
import com.example.generated.reqres.Reqres;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.core.Serenity;

public class ReqDefination {

    @Given("user hit the api")
    public void user_hit_the_api() throws JsonProcessingException {
        Response response = RestAssured.given().baseUri("https://reqres.in")
                .when().get("/api/users/5")
                .then().log().all()
                .extract().response();

        // Convert JSON response to Reqres object
        ObjectMapper objectMapper = new ObjectMapper();
        Reqres reqres = objectMapper.readValue(response.asString(), Reqres.class);

        // Log the response to Serenity report
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqres);
        Serenity.recordReportData().withTitle("API Response").andContents(jsonResponse);

        // Print the Reqres object as a JSON string (optional)
        System.out.println(jsonResponse);
    }
}
