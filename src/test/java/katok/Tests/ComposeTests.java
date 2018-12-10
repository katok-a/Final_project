package katok.Tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/katok/Features/",
        glue = "katok.Steps",
        tags = "@ComposePage_Tests"
)

public class ComposeTests {
}