package ay.microservices.currencyconversionservice.proxies;

import ay.microservices.currencyconversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aliyussef on 09/04/2021
 */
//@FeignClient(name = "currency-exchange", url = "localhost:8000/api/v1/currencyExchange/")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("api/v1/currencyExchange/from/{from}/to/{to}")
    @ResponseStatus(HttpStatus.OK)
    CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);
}
