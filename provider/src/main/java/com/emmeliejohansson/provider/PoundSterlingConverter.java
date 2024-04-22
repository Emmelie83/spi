package com.emmeliejohansson.provider;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

@CurrencyConverterImpl(name = "Pound Sterling (GBP)")
public class PoundSterlingConverter implements CurrencyConverter {
    @Override
    public double convert(double amount, String toCurrency) {
        if (toCurrency.equals("GBP")) {
            return amount;
        } else if (toCurrency.equals("USD")) {
            return amount * 1.24;
        } else if (toCurrency.equals("EUR")) {
            return amount * 1.16;
        } else if (toCurrency.equals("SEK")) {
            return amount * 13.45;
        }
        return -1;
    }
}