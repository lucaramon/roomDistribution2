package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotUniqueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UniqueRoomNumberValidatorTest {

    @InjectMocks
    UniqueRoomNumberValidator uniqueRoomNumberValidator;

    @Test
    void roomNumberAlreadyExcistsTest() {

        List<Room> allRooms = new ArrayList<>();

        Room room1 = new Room("1234");
        allRooms.add(room1);

        Room room2 = new Room("1234");
        allRooms.add(room2);

        RoomNumberNotUniqueException roomNumberNotUniqueException = assertThrows(RoomNumberNotUniqueException.class, () -> uniqueRoomNumberValidator.validate(allRooms));
        Assertions.assertThat(roomNumberNotUniqueException.getMessage()).isEqualTo("Eine Raumnummer darf nur einmal vorkommen!");
    }

    @Test
    void roomNumberIsUniqueTest() {

        List<Room> allRooms = new ArrayList<>();

        Room room1 = new Room("1234");
        allRooms.add(room1);

        Room room2 = new Room("5678");
        allRooms.add(room2);

        uniqueRoomNumberValidator.validate(allRooms);
    }
}