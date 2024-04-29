package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

public class PersonNotUniqueException extends RuntimeException {

    public PersonNotUniqueException(String message) {
        super(message);
    }
}
