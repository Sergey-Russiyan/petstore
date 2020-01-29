package starter;

import cucumber.api.CucumberOptions;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.Assume;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = "~@issue",
        plugin = {"pretty"},
        features = "classpath:features"
)

public class CucumberTestSuite {


}


