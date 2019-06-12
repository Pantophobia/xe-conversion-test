package io.github.pantophobia.xe.pages;

import io.github.pantophobia.xe.enums.Currency;
import lombok.Getter;
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

    @FindBy(css = ".converterresult-unitConversion")
    private List<WebElement> conversionRates;

    @FindBy(css = ".converterresult-fromAmount")
    private WebElement conversionAmount;

    public void selectFromCurrency(Currency currency) {
        fromCurrency.sendKeys(currency.name());
    }

    public void selectToCurrency(Currency currency) {
        toCurrency.sendKeys(currency.name());
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

    private String extractConversionValue(String conversionText) {
        // E.g. 1 GBP = 1.12422 EUR
        String result = conversionText.split("=")[1].replace("=", "").trim();
        // 1.12422 EUR
        result = result.split(" ")[0];
        return result;
    }
}
