package carlos.unda.n26challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Invalid input data")
public class InvalidInputException extends RuntimeException{

    public InvalidInputException(String errorMsg) {
        super(errorMsg);
    }
}
