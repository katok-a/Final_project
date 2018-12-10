package katok.Tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@Suite.SuiteClasses(value = {ComposeTests.class})
@CucumberOptions(
        features = "src/test/java/katok/Features/",
        glue = "katok.Steps"
)

public class AllTests {
}