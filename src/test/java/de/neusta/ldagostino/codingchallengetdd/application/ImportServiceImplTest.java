package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.DataLineNotValidException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.PersonNotUniqueException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotUniqueException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomRepositoryImpl;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.DataLineValidator;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.UniquePersonValidator;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.UniqueRoomNumberValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportServiceImplTest {

    @InjectMocks
    ImportServiceImpl importService;

    @Mock
    RoomRepositoryImpl roomRepository;

    @Mock
    CreateRoomServiceImpl createRoomService;

    @Mock
    UniqueRoomNumberValidator uniqueRoomNumberValidator;

    @Mock
    UniquePersonValidator uniquePersonValidator;

    @Mock
    DataLineValidator dataLineValidator;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(importService, "sitzplanImportValidators", Arrays.asList(uniquePersonValidator, uniqueRoomNumberValidator));
    }

    @Test
    void emptyContentTest() {
        List<Room> roomList = importService.importData("".getBytes());
        Assertions.assertThat(roomList).hasSize(0);
    }

    @Test
    void importSingleRoomTest() {

        List<Room> allRooms = new ArrayList<>();

        Room expectedRoom = new Room("1234");
        allRooms.add(expectedRoom);

        when(createRoomService.createRoom("1234")).thenReturn(expectedRoom);
        when(roomRepository.addRooms(allRooms)).thenReturn(allRooms);

        List<Room> roomList = importService.importData("1234".getBytes());

        Assertions.assertThat(roomList).hasSize(1);
        Assertions.assertThat(roomList.get(0).getRoomNumber()).isEqualTo("1234");
    }

    @Test
    void importMultipleRoomsAndPersonsTest() {

        List<Room> allRooms = new ArrayList<>();

        Room expectedRoom1 = new Room("1234");
        allRooms.add(expectedRoom1);

        Room expectedRoom2 = new Room("5678");
        allRooms.add(expectedRoom2);

        when(createRoomService.createRoom("1234, Susanne Moog (smoog)")).thenReturn(expectedRoom1);
        when(createRoomService.createRoom("5678, Kai Wesling (kwesling), Thomas Kruse (tkruse)")).thenReturn(expectedRoom2);
        when(roomRepository.addRooms(allRooms)).thenReturn(allRooms);

        String content = """
                1234, Susanne Moog (smoog)
                5678, Kai Wesling (kwesling), Thomas Kruse (tkruse)
                """;

        List<Room> roomList = importService.importData(content.getBytes());

        Assertions.assertThat(roomList).hasSize(2);
        Assertions.assertThat(roomList.get(0).getRoomNumber()).isEqualTo("1234");
        Assertions.assertThat(roomList.get(1).getRoomNumber()).isEqualTo("5678");
        verify(roomRepository).deleteRooms();
    }

    @Test
    void importRoomsWithSameRoomNumberTest() {

        List<Room> allRooms = new ArrayList<>();

        Room expectedRoom1 = new Room("1234");
        allRooms.add(expectedRoom1);

        Room expectedRoom2 = new Room("1234");
        allRooms.add(expectedRoom2);

        when(createRoomService.createRoom("1234, Susanne Moog (smoog)")).thenReturn(expectedRoom1);
        when(createRoomService.createRoom("1234, Kai Wesling (kwesling), Thomas Kruse (tkruse)")).thenReturn(expectedRoom2);
        doThrow(new RoomNumberNotUniqueException("Eine Raumnummer darf nur einmal vorkommen!")).when(uniqueRoomNumberValidator).validate(allRooms);

        String content = """
                1234, Susanne Moog (smoog)
                1234, Kai Wesling (kwesling), Thomas Kruse (tkruse)
                """;

        RoomNumberNotUniqueException roomNumberNotUniqueException = assertThrows(RoomNumberNotUniqueException.class, () -> importService.importData(content.getBytes()));

        Assertions.assertThat(roomNumberNotUniqueException.getMessage()).isEqualTo("Eine Raumnummer darf nur einmal vorkommen!");
    }

    @Test
    void importRoomsWithSamePersonTest() {

        List<Room> allRooms = new ArrayList<>();

        Person person1 = new Person("Susanne", "Moog", "smoog");
        Person person2 = new Person("Sebastian", "Moog", "smoog");

        List<Person> allPersonsRoom1 = new ArrayList<>();
        allPersonsRoom1.add(person1);

        Room expectedRoom1 = new Room("1234");
        expectedRoom1.setPersons(allPersonsRoom1);
        allRooms.add(expectedRoom1);

        List<Person> allPersonsRoom2 = new ArrayList<>();
        allPersonsRoom2.add(person2);

        Room expectedRoom2 = new Room("5678");
        expectedRoom2.setPersons(allPersonsRoom2);
        allRooms.add(expectedRoom2);

        when(createRoomService.createRoom("1234, Susanne Moog (smoog)")).thenReturn(expectedRoom1);
        when(createRoomService.createRoom("5678, Sebastian Moog (smoog)")).thenReturn(expectedRoom2);
        doThrow(new PersonNotUniqueException("Eine Person darf nur einmal vorkommen!")).when(uniquePersonValidator).validate(allRooms);

        String content = """
                1234, Susanne Moog (smoog)
                5678, Sebastian Moog (smoog)
                """;

        PersonNotUniqueException personNotUniqueException = assertThrows(PersonNotUniqueException.class, () -> importService.importData(content.getBytes()));

        Assertions.assertThat(personNotUniqueException.getMessage()).isEqualTo("Eine Person darf nur einmal vorkommen!");

        verifyNoInteractions(roomRepository);
    }

    @Test
    void importRoomsWithoutCommaSeparationTest() {

        doThrow(new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!")).when(dataLineValidator).validateRoomAsString("1234 Susanne Moog (smoog)");

        String content = """
                1234 Susanne Moog (smoog)
                5678 Kai Wesling (kwesling) Thomas Kruse (tkruse)
                """;

        DataLineNotValidException dataLineNotValidException = assertThrows(DataLineNotValidException.class, () -> importService.importData(content.getBytes()));

        Assertions.assertThat(dataLineNotValidException.getMessage()).isEqualTo("Die Datenreihe entspricht nicht den Konventionen!");
    }
}