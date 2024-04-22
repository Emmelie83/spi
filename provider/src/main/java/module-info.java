
import com.emmeliejohansson.provider.EuroConverter;
import com.emmeliejohansson.provider.PoundSterlingConverter;
import com.emmeliejohansson.provider.SwedishCrownsConverter;
import com.emmeliejohansson.provider.USDollarConverter;
import com.emmeliejohansson.service.CurrencyConverter;

module com.emmeliejohansson.provider {

    requires com.emmeliejohansson.service;
    provides CurrencyConverter with PoundSterlingConverter, USDollarConverter, EuroConverter, SwedishCrownsConverter;
}