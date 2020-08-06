package info.stepDefinition;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import info.reusables.GetServiceURI;
import info.reusables.customEnsure;
import info.stepImplementation.StepImplementation;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefinition {

	public static String serviceName, uri;

	public Response response;

	public static Scenario scenario;
	StepImplementation objStepImplement = new StepImplementation();

	customEnsure custom = new customEnsure();

	@Before
	public void setUp(Scenario scenario) {
		StepDefinition.scenario = scenario;
	}

	@Given("^I want to test \"([^\"]*)\" service$")
	public void init(String serviceName) {
		StepDefinition.serviceName = serviceName;

		if (serviceName.contains("employees")) {
			StepDefinition.uri = GetServiceURI.EMPLOYEES.uri;
		}

		if (serviceName.contains("create")) {
			StepDefinition.uri = GetServiceURI.CREATE.uri;
		}

		if (serviceName.contains("employee/")) {
			StepDefinition.uri = GetServiceURI.SPECIFIC_EMPLOYEES.uri;
			String emp_id = serviceName.split("/")[1];
			StepDefinition.uri = StepDefinition.uri.replaceAll("EMP_ID", emp_id);
		}

		scenario.write("Service name to be tested: " + StepDefinition.serviceName);
		scenario.write("Endpoint to be tested : " + StepDefinition.uri);
	}

	@When("I do a GET request")
	public void getRequest() {
		response = objStepImplement.getRequest();

	}

	@When("^I do a POST request with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void postRequest(String emp_name, String emp_sal, String emp_age) {
		response = objStepImplement.postRequest(emp_name, emp_sal, emp_age);

	}

	@Then("^I validate the response with statuscode \"([^\"]*)\"$")
	public void validateStatuscode(String statusCode) {

		// scenario.write("Response.............................." +
		// response.prettyPrint());
		customEnsure.customLogWithoutScreenShot("response.prettyPrint()", "pass");

		// objStepImplement.validateResp(response);
		int statcd = Integer.parseInt(statusCode);
		Assert.assertEquals(statcd, response.getStatusCode());

	}

	@And("I check number of employees returned")
	public void checkNumberofEmployees() {
		List<Map<String, String>> listObj = response.jsonPath().getList("data");

		int size = listObj.size();
		String tempObj = null;
		String names = response.jsonPath().getString("employee_name");
		System.out.println("names...................." + names);
		Map<String, String> mapObj = listObj.get(4);

		for (int i = 0; i < size; i++) {

			Map<String, String> temp = listObj.get(i);
			tempObj = temp.get("employee_name") + "\n" + tempObj;

		}

		// scenario.write("Number of employees in reponse is: " + size);
		customEnsure.customLogWithoutScreenShot("Number of employees in reponse is: " + size, "pass");

	}
}
