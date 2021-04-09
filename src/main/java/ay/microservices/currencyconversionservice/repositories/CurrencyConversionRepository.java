package ay.microservices.currencyconversionservice.repositories;

import ay.microservices.currencyconversionservice.models.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aliyussef on 09/04/2021
 */
@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {
}
