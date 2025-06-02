package org.example.dataframe.exceptions;

public class TypeValidationException extends RuntimeException{
    public TypeValidationException() {
        super("Type validation failed");
    }

    public TypeValidationException(String message) {
        super(message);
    }
}
