package info.runnerSuite;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources",
        tags = {"@runAllServices"},
        plugin = {"pretty", "html:target/cucmber_reports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"info"})
public class RunnerSuite {

}
