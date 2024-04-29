package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomNotFoundExceptionTest {

    @Test
    public void testMessage() {

        RoomNotFoundException roomNotFoundException = new RoomNotFoundException("Raum nicht gefunden!");

        Assertions.assertThat(roomNotFoundException.getMessage()).isEqualTo("Raum nicht gefunden!");
    }
}