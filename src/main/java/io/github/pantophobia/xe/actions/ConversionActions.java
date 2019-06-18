package io.github.pantophobia.xe.actions;

import io.github.pantophobia.xe.enums.Currency;
import io.github.pantophobia.xe.helpers.PresenceUtils;
import io.github.pantophobia.xe.pages.ConversionPage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Log4j
@AllArgsConstructor
public class ConversionActions {

    private final ConversionPage conversionPage;

    public void convertCurrency(BigDecimal value, Currency fromCurrency, Currency toCurrency) {
        log.info(String.format("CONVERTING %s of %s to %s", value, fromCurrency.getCountry(), toCurrency.getCountry()));
        conversionPage.enterAmount(value);
        conversionPage.selectFromCurrency(fromCurrency);
        conversionPage.selectToCurrency(toCurrency);
        conversionPage.submit();
    }

    public void verifyResult(BigDecimal valueOfConversion) {
        BigDecimal conversionRate = conversionPage.getToCurrencyConversionRate();

        MathContext mc = new MathContext(6, RoundingMode.DOWN);
        BigDecimal expectedResult = conversionRate.multiply(valueOfConversion, mc);
        BigDecimal actualResult = conversionPage.getConversionResult();

        log.info(String.format("EXPECTED RESULT: %s", expectedResult));
        log.info(String.format("ACTUAL RESULT: %s", actualResult));

        Assert.assertEquals(expectedResult, actualResult);
    }

    public void verifyCorrectConversion(Currency fromCurrency, Currency toCurrency) {
        String currencyError = "CURRENCY NOT CORRECT, EXPECTED %s, BUT FOUND %s";
        Assert.assertEquals("TO " + String.format(currencyError, toCurrency, conversionPage.getToCurrencyAfterConversion()),
                toCurrency, conversionPage.getToCurrencyAfterConversion());
        Assert.assertEquals("FROM " + String.format(currencyError, fromCurrency, conversionPage.getFromCurrencyAfterConversion()),
                fromCurrency, conversionPage.getFromCurrencyAfterConversion());
    }

    public void handleCookiePopup() {
        if (PresenceUtils.isPresent(conversionPage.getAcceptCookiesButton()))
            conversionPage.acceptCookies();
    }

}
