package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String message) {
        super(message);
    }
}
