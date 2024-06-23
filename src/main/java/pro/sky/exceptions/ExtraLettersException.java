package pro.sky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExtraLettersException extends RuntimeException {
    public ExtraLettersException(String message) {
        super(message);
    }
}
