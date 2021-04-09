package ay.microservices.currencyconversionservice.controllers;

import ay.microservices.currencyconversionservice.models.CurrencyConversion;
import ay.microservices.currencyconversionservice.services.CurrencyConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by aliyussef on 09/04/2021
 */
@RestController
@RequestMapping("api/v1/currencyConversion")
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/api/v1/currencyExchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables);
        CurrencyConversion currencyConversion = currencyConversionResponseEntity.getBody();

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

    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyConversion calculateCurrencyConversionUsingFeign(@PathVariable String from,
                                                                    @PathVariable String to,
                                                                    @PathVariable BigDecimal quantity) {

        return currencyConversionService.getExchangeValue(from, to, quantity);
    }
}
