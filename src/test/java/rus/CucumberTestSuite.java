package rus;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = "~@issue",
        plugin = {"pretty"},
        features = "classpath:features"
)

public class CucumberTestSuite {


}


