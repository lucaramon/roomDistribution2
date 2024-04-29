package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotValidException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomNumberNotValidExceptionTest {

    @Test
    public void testMessage() {

        RoomNumberNotValidException roomNumberNotValidException = new RoomNumberNotValidException("Die Raumnummer muss 4 stellig sein!");

        Assertions.assertThat(roomNumberNotValidException.getMessage()).isEqualTo("Die Raumnummer muss 4 stellig sein!");
    }
}