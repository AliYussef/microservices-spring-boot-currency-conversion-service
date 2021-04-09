package ay.microservices.currencyconversionservice.services;

import ay.microservices.currencyconversionservice.models.CurrencyConversion;

import java.math.BigDecimal;

/**
 * Created by aliyussef on 09/04/2021
 */
public interface CurrencyConversionService {
    CurrencyConversion getExchangeValue(String from, String to, BigDecimal quantity);
}
