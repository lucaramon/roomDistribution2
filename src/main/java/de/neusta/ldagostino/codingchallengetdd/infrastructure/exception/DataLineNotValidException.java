package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

public class DataLineNotValidException extends RuntimeException{

    public DataLineNotValidException(String message) {
        super(message);
    }
}
