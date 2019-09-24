package com.randomfood.food.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find any entity with given id.")
public class EntityNotFoundException extends Exception {

    public static String errorMessage = "Could not find any entity with given id.";

    public EntityNotFoundException(String message) {
        super(message);
    }
}
