package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.DataLineNotValidException;
import org.springframework.stereotype.Component;

@Component
public class DataLineValidator {

    public void validateRoomAsString(String roomAsString) {

        if (!roomAsString.contains(",")) {
            throw new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!");
        }
    }

    public void validatePersonAsString(String personAsString) {

        if (!personAsString.contains(" ") || !personAsString.contains("(") || !personAsString.contains(")")) {
            throw new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!");
        }
    }
}
