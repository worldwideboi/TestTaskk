package dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseEntity{

    private String statusCode;

    public enum statusOfCodes{

    }
    private Integer statusCodeValue;
}
