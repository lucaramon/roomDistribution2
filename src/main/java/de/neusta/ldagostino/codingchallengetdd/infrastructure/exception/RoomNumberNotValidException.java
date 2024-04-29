package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

public class RoomNumberNotValidException extends RuntimeException {

    public RoomNumberNotValidException(String message) {
        super(message);
    }
}
