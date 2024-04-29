package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.DataLineNotValidException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.DataLineValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DataLineValidatorTest {

    @InjectMocks
    DataLineValidator dataLineValidator;

    @Test
    void roomDataLineWithoutCommaTest() {

        String roomAsString = "1234 Susanne Moog (smoog) Kai Wesling (kwesling)";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> dataLineValidator.validateRoomAsString(roomAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

    @Test
    void validRoomDataLineTest() {

        String roomAsString = "1234, Susanne Moog (smoog), Kai Wesling (kwesling)";

        assertDoesNotThrow(() -> dataLineValidator.validateRoomAsString(roomAsString));
    }

    @Test
    void personDataLineWithoutSpacesTest() {

        String personAsString = "SusanneMoog(smoog)";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> dataLineValidator.validatePersonAsString(personAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

    @Test
    void personDataLineWithoutBracketsTest() {

        String personAsString = "Susanne Moog smoog";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> dataLineValidator.validatePersonAsString(personAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

    @Test
    void personDataLineWithOneBracketTest() {

        String personAsString = "Susanne Moog (smoog";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> dataLineValidator.validatePersonAsString(personAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

    @Test
    void personDataLineWithAnotherOneBracketTest() {

        String personAsString = "Susanne Moog smoog)";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> dataLineValidator.validatePersonAsString(personAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

    @Test
    void validPersonDataTest() {

        String personAsString = "Susanne Moog (smoog)";

        assertDoesNotThrow(() -> dataLineValidator.validatePersonAsString(personAsString));
    }
}