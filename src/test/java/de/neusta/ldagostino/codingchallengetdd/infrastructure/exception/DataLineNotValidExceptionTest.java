package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.DataLineNotValidException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DataLineNotValidExceptionTest {

    @Test
    public void testMessage() {

        DataLineNotValidException dataLineNotValidException = new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!");

        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }

}