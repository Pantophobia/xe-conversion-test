package io.github.pantophobia.xe.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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
