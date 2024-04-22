package com.emmeliejohansson.provider;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

@CurrencyConverterImpl(name = "US Dollar (USD)")
public class USDollarConverter implements CurrencyConverter {
    @Override
    public double convert(double amount, String toCurrency) {
        if (toCurrency.equals("USD")) {
            return amount;
        } else if (toCurrency.equals("EUR")) {
            return amount * 0.94;
        } else if (toCurrency.equals("GBP")) {
            return amount * 0.81;
        } else if (toCurrency.equals("SEK")) {
            return amount * 10.88;
        }
        return -1;
    }
}
