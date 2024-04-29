package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.DataLineNotValidException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.DataLineValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CreateRoomServiceImplTest {

    @InjectMocks
    CreateRoomServiceImpl createRoomService;

    @Mock
    CreatePersonServiceImpl createPersonService;

    @Mock
    DataLineValidator dataLineValidator;

    @Test
    void createRoomWithRoomNumberTest() {

        String roomAsString = "1234";

        Room room = createRoomService.createRoom(roomAsString);

        Assertions.assertThat(room.getRoomNumber()).isEqualTo("1234");
    }

    @Test
    void createRoomWithRoomNumberAndPersonTest() {

        String roomAsString = "1234, Susanne Moog (smoog)";

        Mockito.when(createPersonService.createPerson("Susanne Moog (smoog)")).thenReturn(new Person("Susanne", "Moog", "smoog"));

        Room room = createRoomService.createRoom(roomAsString);

        Assertions.assertThat(room.getRoomNumber()).isEqualTo("1234");
        Assertions.assertThat(room.getPersons().size()).isEqualTo(1);
    }

    @Test
    void createRoomWithRoomNumberAndTwoPersonsTest() {

        String roomAsString = "1234, Susanne Moog (smoog), Kai Wesling (kwesling)";

        Mockito.when(createPersonService.createPerson("Susanne Moog (smoog)")).thenReturn(new Person("Susanne", "Moog", "smoog"));
        Mockito.when(createPersonService.createPerson("Kai Wesling (kwesling)")).thenReturn(new Person("Kai", "Wesling", "kwesling"));

        Room room = createRoomService.createRoom(roomAsString);

        Assertions.assertThat(room.getRoomNumber()).isEqualTo("1234");
        Assertions.assertThat(room.getPersons().size()).isEqualTo(2);
    }

    @Test
    void createRoomWithoutSpacesInPersonTest() {

        Mockito.doThrow(new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!")).when(dataLineValidator).validatePersonAsString("SusanneMoog(smoog)");

        String roomAsString = "1234, SusanneMoog(smoog)";

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> createRoomService.createRoom(roomAsString));
        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }
}