package com.emmeliejohansson.provider;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

@CurrencyConverterImpl(name = "Swedish Crowns (SEK)")
public class SwedishCrownsConverter implements CurrencyConverter {
    @Override
    public double convert(double amount, String toCurrency) {
        if (toCurrency.equals("SEK")) {
            return amount;
        } else if (toCurrency.equals("USD")) {
            return amount * 0.092;
        } else if (toCurrency.equals("EUR")) {
            return amount * 0.086;
        } else if (toCurrency.equals("GBP")) {
            return amount * 0.074;
        }
        return -1;
    }
}
