package io.github.pantophobia.xe.actions;

import io.github.pantophobia.xe.enums.Currency;
import io.github.pantophobia.xe.helpers.PresenceUtils;
import io.github.pantophobia.xe.pages.ConversionPage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.math.BigDecimal;

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
        log.info(String.format("RESULT: %s", conversionPage.getConversionResult()));

//        conversionPage.getFromCurrencyConversionRate();

    }

    public void verifyCorrectConversion(Currency toCurrency, Currency fromCurrency) {
        log.info(String.format("RESULT: %s", conversionPage.getConversionResult()));
//        conversionPage.getConversionResult();
//        conversionPage.getFromCurrencyConversionRate();

    }

    public void handleCookiePopup() {
        if (PresenceUtils.isPresent(conversionPage.getAcceptCookiesButton()))
            conversionPage.acceptCookies();
    }

}
