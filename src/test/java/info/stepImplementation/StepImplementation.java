package info.stepImplementation;

import org.json.JSONObject;


import info.reusables.RESTOperations;
import info.stepDefinition.StepDefinition;
import io.restassured.response.Response;

public class StepImplementation extends RESTOperations {
    Response response;

    public Response getRequest() {
        response = getService(StepDefinition.uri);
        return response;
    }

    public Response postRequest(String emp_name, String emp_sal, String emp_age) {

        String body = "{\"name\":\"%%name%%\",\"salary\":\"%%salary%%\",\"age\":\"%%age%%\"}";

        body = body.replaceAll("%%name%%", emp_name)
                .replaceAll("%%salary%%", emp_sal)
                .replaceAll("%%age%%", emp_age);

        StepDefinition.scenario.write("Request body.................." + body);
        response = postService(StepDefinition.uri, body);


        return response;
    }

    public void validateResp(Response response) {
        JSONObject jObj = new JSONObject();
        jObj.getJSONArray("data");
        System.out.println("Data array......................" + jObj.getJSONArray("data"));

    }

}
