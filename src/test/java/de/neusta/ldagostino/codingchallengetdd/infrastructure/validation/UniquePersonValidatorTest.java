package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.PersonNotUniqueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UniquePersonValidatorTest {

    @InjectMocks
    UniquePersonValidator uniquePersonValidator;

    @Test
    void personAlreadyExcistsTest() {

        List<Room> allRooms = new ArrayList<>();
        List<Person> allPersons = new ArrayList<>();

        Person person1 = new Person("Susanne", "Moog", "smoog");
        allPersons.add(person1);

        Person person2 = new Person("Sebastian", "Moog", "smoog");
        allPersons.add(person2);

        Room room = new Room("1234");
        room.setPersons(allPersons);

        allRooms.add(room);

        PersonNotUniqueException personNotUniqueException = assertThrows(PersonNotUniqueException.class, () -> uniquePersonValidator.validate(allRooms));
        Assertions.assertThat(personNotUniqueException.getMessage()).isEqualTo("Eine Person darf nur einmal vorkommen!");
    }

    @Test
    void personIsUniqueTest() {

        List<Room> allRooms = new ArrayList<>();
        List<Person> allPersons = new ArrayList<>();

        Person person1 = new Person("Susanne", "Moog", "smoog");
        allPersons.add(person1);

        Person person2 = new Person("Sebastian", "Meier", "smeier");
        allPersons.add(person2);

        Room room = new Room("1234");
        room.setPersons(allPersons);

        allRooms.add(room);

        uniquePersonValidator.validate(allRooms);
    }
}