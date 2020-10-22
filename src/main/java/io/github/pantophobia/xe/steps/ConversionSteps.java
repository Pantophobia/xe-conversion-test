package io.github.pantophobia.xe.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.pantophobia.xe.actions.ConversionActions;
import io.github.pantophobia.xe.enums.Currency;
import io.github.pantophobia.xe.helpers.ScenarioData;
import io.github.pantophobia.xe.pages.ConversionPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class ConversionSteps {

    private WebDriver webDriver;

    private ConversionActions conversionActions;

    private ScenarioData scenarioData;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().browserVersion("latest").setup();
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        ConversionPage page = PageFactory.initElements(webDriver, ConversionPage.class);
        this.conversionActions = new ConversionActions(page);
        this.scenarioData = new ScenarioData();
    }

    @Given("I am on the XE homepage")
    public void userIsOnTheXeHomePage() {
        this.webDriver.get("https://www.xe.com");
        this.conversionActions.handleCookiePopup();
        this.conversionActions.handlePopUp();
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
