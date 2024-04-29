package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @InjectMocks
    RoomServiceImpl roomService;

    @Mock
    RoomRepositoryImpl roomRepository;

    @Test
    public void getExistingRoom(){

        Room expectedRoom = new Room("1234");

        Mockito.when(roomRepository.getRoom("1234")).thenReturn(expectedRoom);

        Room room = roomService.getRoom("1234");

        Assertions.assertThat(room).isNotNull();
        Assertions.assertThat(room).isEqualTo(expectedRoom);
    }

    @Test
    public void getNotExistingRoom(){

        Mockito.when(roomRepository.getRoom("1234")).thenReturn(null);

        Room room = roomService.getRoom("1234");

        Assertions.assertThat(room).isNull();
    }

    @Test
    public void getExistingRoomCollection(){

        List<Room> excpectedRoomCollection = new ArrayList<>();

        Mockito.when(roomRepository.getRooms()).thenReturn(excpectedRoomCollection);

        List<Room> roomCollection = roomService.getRooms();

        Assertions.assertThat(roomCollection).isNotNull();
        Assertions.assertThat(roomCollection).isEqualTo(excpectedRoomCollection);
    }

    @Test
    public void getNotExistingRoomCollection(){

        Mockito.when(roomRepository.getRooms()).thenReturn(Collections.emptyList());

        List<Room> roomCollection = roomService.getRooms();

        Assertions.assertThat(roomCollection).isEmpty();
    }
}