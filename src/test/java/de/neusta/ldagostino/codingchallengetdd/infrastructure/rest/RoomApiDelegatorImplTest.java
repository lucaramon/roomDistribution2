package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.application.RoomService;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.generated.model.RoomDto;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNotFoundException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotValidException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.RoomApiDelegatorImpl;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.RoomDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RoomApiDelegatorImplTest {

    @InjectMocks
    RoomApiDelegatorImpl roomApiDelegator;

    @Mock
    RoomService roomService;

    @Mock
    RoomDtoMapper roomDtoMapper;

    @Test
    public void getExistingRoomTest() {

        Room expectedRoom = new Room("1234");

        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNumber("1234");

        Mockito.when(roomService.getRoom("1234")).thenReturn(expectedRoom);
        Mockito.when(roomDtoMapper.mapRoomToRoomDto(expectedRoom)).thenReturn(roomDto);

        ResponseEntity<RoomDto> excpetedReturn = new ResponseEntity<>(roomDto, HttpStatus.OK);

        ResponseEntity<RoomDto> room = roomApiDelegator.roomRoomNumberGet("1234");

        Assertions.assertThat(room).isNotNull();
        Assertions.assertThat(room).isEqualTo(excpetedReturn);
    }

    @Test
    public void getNotExistingRoomTest() {

        RoomNotFoundException roomNotFoundException = assertThrows(RoomNotFoundException.class, () -> roomApiDelegator.roomRoomNumberGet("1234"));

        Assertions.assertThat(roomNotFoundException.getMessage()).isEqualTo("Raum mit Nummer 1234 nicht gefunden!");
    }

    @Test
    public void getRoomWithInvalidNumberTest() {

        RoomNumberNotValidException roomNumberNotValidException = assertThrows(RoomNumberNotValidException.class, () -> roomApiDelegator.roomRoomNumberGet("12345"));

        Assertions.assertThat(roomNumberNotValidException.getMessage()).isEqualTo("Die Raumnummer muss 4 stellig sein!");
    }

    @Test
    public void getExistingRoomCollectionTest() {

        List<Room> roomCollection = new ArrayList<>();
        roomCollection.add(new Room("1234"));

        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNumber("1234");
        List<RoomDto> expectedRoomDtoCollection = new ArrayList<>();
        expectedRoomDtoCollection.add(roomDto);

        Mockito.when(roomService.getRooms()).thenReturn(roomCollection);
        Mockito.when(roomDtoMapper.mapRoomsToRoomDto(roomCollection)).thenReturn(expectedRoomDtoCollection);

        ResponseEntity<List<RoomDto>> excpetedReturn = new ResponseEntity<>(expectedRoomDtoCollection, HttpStatus.OK);

        ResponseEntity<List<RoomDto>> roomDtoCollection = roomApiDelegator.roomGet();

        Assertions.assertThat(roomDtoCollection).isNotNull();
        Assertions.assertThat(roomDtoCollection).isEqualTo(excpetedReturn);
    }

    @Test
    public void getNonExistingRoomCollectionTest() {

        RoomNotFoundException roomNotFoundException = assertThrows(RoomNotFoundException.class, () -> roomApiDelegator.roomGet());

        Assertions.assertThat(roomNotFoundException.getMessage()).isEqualTo("Es wurde kein Raum gefunden!");
    }
}