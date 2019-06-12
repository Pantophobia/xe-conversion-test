package io.github.pantophobia.xe.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:XeConversion.feature",
        glue = {"io.github.pantophobia.xe.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-html-report/XeConversion",
        }
)
public class XeConversionTest {
}
