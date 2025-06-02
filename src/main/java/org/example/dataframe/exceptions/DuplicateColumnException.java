package org.example.dataframe.exceptions;

public class DuplicateColumnException extends IllegalArgumentException{
    public DuplicateColumnException() {
        super("Type validation failed");
    }

    public DuplicateColumnException(String message) {
        super(message);
    }
    public DuplicateColumnException(String message,String column) {
        super(String.format("%s ,column: %s",message ,column));
    }
}
