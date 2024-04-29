package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomNumberNotUniqueExceptionTest {

    @Test
    public void testMessage() {

        RoomNumberNotUniqueException roomNumberNotUniqueException = new RoomNumberNotUniqueException("Eine Raumnummer darf nur einmal vorkommen!");

        Assertions.assertThat(roomNumberNotUniqueException.getMessage()).isEqualTo("Eine Raumnummer darf nur einmal vorkommen!");
    }
}