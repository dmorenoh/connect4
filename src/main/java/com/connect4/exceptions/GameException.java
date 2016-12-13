package com.connect4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dmorenoh on 14/11/16.
 */
public class GameException extends RuntimeException {
    public GameException(String message) {
        super(message);
    }
}
