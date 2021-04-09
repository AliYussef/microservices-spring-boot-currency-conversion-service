package ay.microservices.currencyconversionservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by aliyussef on 09/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private int statusCode;
}
