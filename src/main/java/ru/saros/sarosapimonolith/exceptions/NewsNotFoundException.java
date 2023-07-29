package ru.saros.sarosapimonolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException() {
        super();
    }

    public NewsNotFoundException(String message) {
        super(message);
    }
}
