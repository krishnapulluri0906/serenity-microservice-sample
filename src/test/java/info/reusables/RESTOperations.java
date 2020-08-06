package info.reusables;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class RESTOperations {

    Response response;

    public Response getService(String uri) {
        try {

            response = SerenityRest.
                    given().
                    contentType(ContentType.JSON).
                    when().
                    get(uri);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public Response getService(String uri, Map<String, String> header) {

        try {
            response = SerenityRest.
                    given().
                    headers(header).
                    contentType(ContentType.JSON).
                    when().
                    get(uri);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;

    }

    public Response postService(String uri, String body) {

        try {
            response = SerenityRest.
                    given().
                    contentType(ContentType.JSON)
                    .body(body).
                            when().
                            post(uri);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return response;
    }

    public Response postService(String uri, String body,
                                Map<String, String> header) {

        try {
            response = SerenityRest.
                    given().
                    headers(header).
                    contentType(ContentType.JSON).
                    body(body).
                    when().
                    post(uri);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public void validateResponse(Response response) {

    }

}
