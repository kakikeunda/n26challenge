package carlos.unda.n26challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NO_CONTENT, reason="Transaction is older than 60 seconds")
public class TransactionOvertimeException extends RuntimeException{

    public TransactionOvertimeException() {
    }
}
