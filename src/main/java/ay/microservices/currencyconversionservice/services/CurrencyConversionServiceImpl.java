package ay.microservices.currencyconversionservice.services;

import ay.microservices.currencyconversionservice.exceptions.NotFoundException;
import ay.microservices.currencyconversionservice.models.CurrencyConversion;
import ay.microservices.currencyconversionservice.proxies.CurrencyExchangeProxy;
import ay.microservices.currencyconversionservice.repositories.CurrencyConversionRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by aliyussef on 09/04/2021
 */
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    private final Environment environment;
    private final CurrencyConversionRepository currencyConversionRepository;
    private final CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversionServiceImpl(Environment environment, CurrencyConversionRepository currencyConversionRepository, CurrencyExchangeProxy currencyExchangeProxy) {
        this.environment = environment;
        this.currencyConversionRepository = currencyConversionRepository;
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @Override
    public CurrencyConversion getExchangeValue(String from, String to, BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.getExchangeValue(from, to);

        if (currencyConversion == null) {
            throw new NotFoundException("Currency exchange not found!");
        }

        return CurrencyConversion.builder()
                .id(currencyConversion.getId())
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculationAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment())
                .build();
    }
}
