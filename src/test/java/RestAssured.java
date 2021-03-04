import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssured {
    private Object Response;

    // Basic YW5kQGdtYWlsLmNvbTpBc2RmZ2g1NDY=
    @Test
    public void postRequestTest() {
        RequestSpecification httpRequest = io.restassured.RestAssured.given();

        Response response = httpRequest
                .given().header("Content-Type", "application/json")
                .given().header("Authorization", "YW5kQGdtYWlsLmNvbTpBc2RmZ2g1NDY=")
                .request()
                .body("{\n" +
                        "  \"first_name\": \"Pigidij\",\n" +
                        "  \"second_name\": \"Arnold\"\n" +
                        "}")
                .when().post("https://java-3-ilcarro-team-b.herokuapp.com/registration");

        String responseBody = response.getBody().asString();
        System.out.println("ResponseBody is=>" + responseBody);
        int StatusCode = response.getStatusCode();
        System.out.println("statuscode" + StatusCode);
        Assert.assertEquals(StatusCode, 200);
    }

    @Test

    public void getLoginTest() {

        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().contentType(ContentType.JSON)
                .given().header("Authorization", "YW5kQGdtYWlsLmNvbTpBc2RmZ2g1NDY=")
                .request().get("https://java-3-ilcarro-team-b.herokuapp.com/user/login");

        String responseBody = response.getBody().asString();
        System.out.println("ResponseBody is=>" + responseBody);
        int StatusCode = response.getStatusCode();
        System.out.println("statuscode" + StatusCode);
        Assert.assertEquals(StatusCode, 200, "BUG:Status code coming is different!");

        JsonElement parsed = new JsonParser().parse(responseBody);
        String first_name = parsed.getAsJsonObject().get("first_name").toString();
        String own_car = parsed.getAsJsonObject().get("own_cars").toString();
        System.out.println(first_name + " " + own_car);
        Assert.assertEquals(first_name,"\"Pigidij\"", "first_name is not the same as expected");
    }
}
