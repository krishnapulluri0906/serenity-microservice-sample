package info.reusables;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.core.steps.StepFailure;

import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Fail;

public class customEnsure {

	/**
	 * A helper method to make a RestAssured assertion appear as a separate step.
	 * e.g Ensure.that("Commany name should be returned", response ->
	 * response.body("companyName", equalTo("Apple Inc.s")));
	 *
	 * @param description
	 * @param check
	 */
	public static customEnsure that(String description, Consumer<ValidatableResponse> check) {
		customEnsure ensure = new customEnsure();
		ensure.performCheck(description, check, "Ensure that ");
		return ensure;
	}

	public customEnsure andThat(String description, Consumer<ValidatableResponse> check) {
		performCheck(description, check, "And that ");
		return this;
	}

	private void performCheck(String description, Consumer<ValidatableResponse> check, String prefix) {
		StepEventBus.getEventBus().stepStarted(ExecutedStepDescription.withTitle(prefix + description));
		try {
			check.accept(SerenityRest.then());
			String escapedMessage = StringUtils.replace(description, "", "&nbsp;");
			StepEventBus.getEventBus().stepStarted(ExecutedStepDescription.withTitle(escapedMessage + "Success"));
		} catch (Throwable e) {
			StepEventBus.getEventBus().stepFailed(new StepFailure(ExecutedStepDescription.withTitle(description), e));
			Fail.fail("Validation failed at:" + description);
			return;
		}
		StepEventBus.getEventBus().stepFinished();
	}
	
	public static void customLogWithoutScreenShot(String description, String status) {
		String escapedMessage = "";
		if (status.equalsIgnoreCase("pass")) {
			escapedMessage = StringUtils.replace(description, "", "&nbsp;");
			StepEventBus.getEventBus().stepStarted(ExecutedStepDescription.withTitle(escapedMessage + "Pass"));
			StepEventBus.getEventBus().stepFinished();
		} else if (status.equalsIgnoreCase("fail")) {
			Fail.fail("Validation failed at: " + description);
		}

	}


	public static class Report {
		public static void customLogWithoutScreenShot(String description, String status) {
			String escapedMessage = "";
			if (status.equalsIgnoreCase("pass")) {
				escapedMessage = StringUtils.replace(description, "", "&nbsp;");
				StepEventBus.getEventBus().stepStarted(ExecutedStepDescription.withTitle(escapedMessage + "Pass"));
				StepEventBus.getEventBus().stepFinished();
			} else if (status.equalsIgnoreCase("fail")) {
				Fail.fail("Validation failed at: " + description);
			}

		}

	}

}