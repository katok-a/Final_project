package katok.Tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/katok/Features/",
        glue = "katok.Steps",
        tags = "@Inbox_Tests"
)

public class LogOutTest {
}

