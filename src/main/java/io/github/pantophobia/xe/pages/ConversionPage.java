package io.github.pantophobia.xe.pages;

import io.github.pantophobia.xe.enums.Currency;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;

public class ConversionPage {

    @FindBy(id = "amount")
    private WebElement amountValue;

    @FindBy(id = "from")
    private WebElement fromCurrency;

    @FindBy(id = "to")
    private WebElement toCurrency;

    @FindBy(css = ".submitButton")
    private WebElement submitButton;

    @FindBy(css = ".converterresult-toAmount")
    private WebElement resultAmount;

    @Getter
    @FindBy(css = ".privacy-basic-button-submit")
    private WebElement acceptCookiesButton;

    @FindBy(css = ".second div")
    private List<WebElement> conversionRates;

    @FindBy(css = ".converterresult-fromAmount")
    private WebElement conversionAmount;

    @FindBy(css = ".converterresult-conversionFrom")
    private WebElement conversionResultFromCurrency;

    @FindBy(css = ".converterresult-toCurrency")
    private WebElement conversionResultToCurrency;

    @Getter
    @FindBy(xpath = ".//button[contains(@id, 'yie-close-button')]")
    private WebElement popupCloseButton;

    public void selectFromCurrency(Currency currency) {
        fromCurrency.sendKeys(currency.name(), Keys.ENTER);
    }

    public void selectToCurrency(Currency currency) {
        toCurrency.sendKeys(currency.name(), Keys.ENTER);
    }

    public void enterAmount(BigDecimal amount) {
        amountValue.sendKeys(amount.toPlainString());
    }

    public void submit() {
        submitButton.click();
    }

    public BigDecimal getConversionResult() {
        return new BigDecimal(resultAmount.getText());
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public BigDecimal getFromCurrencyConversionRate() {
        return new BigDecimal(extractConversionValue(conversionRates.get(0).getText()));
    }

    public BigDecimal getToCurrencyConversionRate() {
        return new BigDecimal(extractConversionValue(conversionRates.get(1).getText()));
    }

    public Currency getFromCurrencyAfterConversion() {
        String fromCurrency = conversionResultFromCurrency.getText().split(" ")[0];
        fromCurrency = fromCurrency.substring(fromCurrency.length()-3);
        return Currency.valueOf(fromCurrency);
    }

    public Currency getToCurrencyAfterConversion() {
        return Currency.valueOf(conversionResultToCurrency.getText());
    }

    private String extractConversionValue(String conversionText) {
        // E.g. 1 GBP = 1.12422 EUR
        String result = conversionText.split("=")[1].replace("=", "").trim();
        // 1.12422 EUR
        result = result.split(" ")[0];
        return result;
    }
}
