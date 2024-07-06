package stepdefinitions.restapi;

import com.example.generated.reqres.CreateUser.RequestUser;
import com.example.generated.reqres.CreateUser.RequestUserResponse;
import com.example.generated.reqres.GetUser.ResponseUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.serenitybdd.core.Serenity;
import stepdefinitions.Maker;

import static io.restassured.RestAssured.given;

public class UserRestApi extends Maker {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Given("user hit the api for creating user as {string} and {string}")
    public void userHitTheApiForCreatingUserAsAnd(String name, String job) throws JsonProcessingException {
        RequestUser requestBody = new RequestUser();
        requestBody.setName(name);
        requestBody.setJob(job);

        Response response = given().header("Content-Type", "application/json").body(requestBody).when().post("/api/users").then().log().all().extract().response();

        ObjectMapper objectMapper = new ObjectMapper();
        RequestUser reqres = objectMapper.readValue(response.asString(), RequestUser.class);

        // Log request and response to Serenity report
        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqres);

        Serenity.recordReportData().withTitle("API Request").andContents(jsonRequest);

        Serenity.recordReportData().withTitle("API Response").andContents(jsonResponse);
        setRequestUserResponse(new ObjectMapper().readValue(response.asString(), RequestUserResponse.class));
    }

    @When("user get the user_id than frame the get Request")
    public void userGetTheUser_idThanFrameTheGetRequest() throws JsonProcessingException {
        RequestUserResponse requestUserResponse = getRequestUserResponse();
        String id = requestUserResponse.getId();
        System.out.println("User id:" + id);
        ResponseBody body = given().log().all().get("/api/users?page=" + id).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        RequestUser reqres = objectMapper.readValue(body.asString(), RequestUser.class);

        // Log the request and response separately to Serenity report
        String jsonRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqres);
        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqres);

        Serenity.recordReportData().withTitle("API Request").andContents(jsonRequest);
        Serenity.recordReportData().withTitle("API Response").andContents(jsonResponse);

    }
}
