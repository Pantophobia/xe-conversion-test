package io.github.pantophobia.xe.helpers;

import io.github.pantophobia.xe.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ScenarioData {

    private BigDecimal amountToConvert;
    private Currency fromCurrency;
    private Currency toCurrency;
}
