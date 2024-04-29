package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePersonServiceImplTest {

    @InjectMocks
    CreatePersonServiceImpl createPersonService;

    @Test
    void createPersonWithMinimumValuesTest() {

        String personAsString = "Susanne Moog (smoog)";

        Person person = createPersonService.createPerson(personAsString);

        Assertions.assertThat(person.getFirstname()).isEqualTo("Susanne");
        Assertions.assertThat(person.getLastname()).isEqualTo("Moog");
        Assertions.assertThat(person.getLdapuser()).isEqualTo("smoog");
    }

    @Test
    void createPersonWithSecondFirstnameTest(){

        String personAsString = "Susanne Marie Moog (smoog)";

        Person person = createPersonService.createPerson(personAsString);

        Assertions.assertThat(person.getFirstname()).isEqualTo("Susanne Marie");
        Assertions.assertThat(person.getLastname()).isEqualTo("Moog");
        Assertions.assertThat(person.getLdapuser()).isEqualTo("smoog");
    }

    @Test
    void createPersonWithTitleTest() {

        String personAsString = "Dr. Susanne Moog (smoog)";

        Person person = createPersonService.createPerson(personAsString);

        Assertions.assertThat(person.getTitle()).isEqualTo("Dr.");
        Assertions.assertThat(person.getFirstname()).isEqualTo("Susanne");
        Assertions.assertThat(person.getLastname()).isEqualTo("Moog");
        Assertions.assertThat(person.getLdapuser()).isEqualTo("smoog");
    }

    @ParameterizedTest
    @ValueSource(strings = {"van", "von", "de"})
    @NullSource
    void createPersonWithNameAdditionTest(String nameAddition) {

        String personAsString = "Susanne " + nameAddition + " Moog (smoog)";

        Person person = createPersonService.createPerson(personAsString);

        Assertions.assertThat(person.getNameAddition()).isEqualTo(nameAddition);
    }

    @Test
    void createPersonWithAllValuesTest() {

        String personAsString = "Dr. Susanne van Moog (smoog)";

        Person person = createPersonService.createPerson(personAsString);

        Assertions.assertThat(person.getTitle()).isEqualTo("Dr.");
        Assertions.assertThat(person.getFirstname()).isEqualTo("Susanne");
        Assertions.assertThat(person.getNameAddition()).isEqualTo("van");
        Assertions.assertThat(person.getLastname()).isEqualTo("Moog");
        Assertions.assertThat(person.getLdapuser()).isEqualTo("smoog");
    }
}