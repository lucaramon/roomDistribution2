package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

public class RoomNumberNotUniqueException extends RuntimeException{

    public RoomNumberNotUniqueException(String message) {
        super(message);
    }
}
