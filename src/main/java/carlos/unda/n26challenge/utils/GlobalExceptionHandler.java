package carlos.unda.n26challenge.utils;

import carlos.unda.n26challenge.exception.InvalidInputException;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){

        JsonObject body = new JsonObject();
        body.addProperty(Constants.JSON_FIELD_ERROR, ex.getMessage());
        return new ResponseEntity<JsonObject>(body,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
