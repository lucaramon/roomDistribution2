package de.neusta.ldagostino.codingchallengetdd.infrastructure.exception;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.PersonNotUniqueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonNotUniqueExceptionTest {

    @Test
    public void testMessage() {

        PersonNotUniqueException personNotUniqueException = new PersonNotUniqueException("Eine Person darf nur einmal vorkommen!");

        Assertions.assertThat(personNotUniqueException.getMessage()).isEqualTo("Eine Person darf nur einmal vorkommen!");
    }

}