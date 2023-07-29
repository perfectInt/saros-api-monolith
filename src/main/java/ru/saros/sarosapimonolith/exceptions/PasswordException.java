package ru.saros.sarosapimonolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordException extends RuntimeException {
    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }
}
