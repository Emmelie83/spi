package com.emmeliejohansson.provider;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

@CurrencyConverterImpl(name = "Euro (EUR)")
public class EuroConverter implements CurrencyConverter {
    @Override
    public double convert(double amount, String toCurrency) {
        if (toCurrency.equals("EUR")) {
            return amount;
        } else if (toCurrency.equals("USD")) {
            return amount * 1.07;
        } else if (toCurrency.equals("GBP")) {
            return amount * 0.86;
        } else if (toCurrency.equals("SEK")) {
            return amount * 11.60;
        }
        return -1;
    }
}
