package io.github.pantophobia.xe.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.pantophobia.xe.actions.ConversionActions;
import io.github.pantophobia.xe.enums.Currency;
import io.github.pantophobia.xe.helpers.ScenarioData;
import io.github.pantophobia.xe.pages.ConversionPage;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

public class ConversionSteps {

    private WebDriver webDriver;

    private ConversionActions conversionActions;

    private ScenarioData scenarioData;

    @Before
    public void setup() {
        BasicConfigurator.configure(); // Setting up Log4j
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32_235\\chromedriver.exe");
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        ConversionPage page = PageFactory.initElements(webDriver, ConversionPage.class);
        this.conversionActions = new ConversionActions(page);
        this.scenarioData = new ScenarioData();
    }


    @Given("I am on the XE homepage")
    public void userIsOnTheXeHomePage() {
        this.webDriver.get("https://www.xe.com");
        this.conversionActions.handleCookiePopup();
    }

    @When("^I convert \"(.*)\" from \"(.*)\" to \"(.*)\"$")
    public void userConvertsAValue(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        this.scenarioData.setAmountToConvert(amount);
        this.scenarioData.setToCurrency(toCurrency);
        this.scenarioData.setFromCurrency(fromCurrency);
        this.conversionActions.convertCurrency(amount, fromCurrency, toCurrency);
    }

    @Then("the result is correct")
    public void verifyValue() {
        conversionActions.verifyCorrectConversion(scenarioData.getFromCurrency(), scenarioData.getToCurrency());
        conversionActions.verifyResult(scenarioData.getAmountToConvert());
    }


    @After
    public void tearDown() {
        this.webDriver.quit();
    }
}
