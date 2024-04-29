package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomEntity;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomJpaRepository;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomMapperImpl;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomRepositoryImplTest {

    @InjectMocks
    RoomRepositoryImpl roomRepository;

    @Mock
    RoomJpaRepository roomJpaRepository;

    @Mock
    RoomMapperImpl roomMapper;

    @Test
    public void getExistingRoomTest() {

        Room expectedRoom = new Room("1234");

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber("1234");

        when(roomJpaRepository.findRoomByRoomNumber("1234")).thenReturn(roomEntity);
        when(roomMapper.mapRoomEntity(roomEntity)).thenReturn(expectedRoom);
        Room room = roomRepository.getRoom("1234");

        assertThat(room).isNotNull();
        assertThat(room).isEqualTo(expectedRoom);
    }

    @Test
    public void getNotExistingRoomTest() {

        when(roomJpaRepository.findRoomByRoomNumber("1234")).thenReturn(null);

        Room room = roomRepository.getRoom("1234");

        assertThat(room).isNull();
    }

    @Test
    public void getExistingRoomCollectionTest() {

        List<Room> expectedAllRooms = new ArrayList<>();
        Room excpectedRoom = new Room("1234");
        expectedAllRooms.add(excpectedRoom);

        List<RoomEntity> roomEntities = new ArrayList<>();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber("1234");
        roomEntities.add(roomEntity);

        when(roomJpaRepository.findAll()).thenReturn(roomEntities);
        when(roomMapper.mapRoomEntities(roomEntities)).thenReturn(expectedAllRooms);

        List<Room> rooms = roomRepository.getRooms();

        assertThat(rooms).isNotNull();
        assertThat(rooms).isEqualTo(expectedAllRooms);
    }

    @Test
    public void getNotExistingRoomCollectionTest() {

        when(roomJpaRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(roomRepository.getRooms()).isEmpty();
    }

    @Test
    public void addAllRoomsTest() {

        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room("1234");
        rooms.add(room1);

        Room room2 = new Room("5678");
        rooms.add(room2);

        List<RoomEntity> roomEntities = new ArrayList<>();

        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setRoomNumber("1234");
        roomEntities.add(roomEntity1);

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setRoomNumber("5678");
        roomEntities.add(roomEntity2);

        when(roomMapper.mapRooms(rooms)).thenReturn(roomEntities);
        when(roomJpaRepository.saveAll(roomEntities)).thenReturn(roomEntities);
        when(roomMapper.mapRoomEntities(roomEntities)).thenReturn(rooms);

        Collection<Room> roomCollection = roomRepository.addRooms(rooms);

        assertThat(roomCollection).isEqualTo(rooms);
    }

    @Test
    public void roomIsNullAndNotAddedTest() {

        Collection<Room> roomCollection = roomRepository.addRooms(new ArrayList<>());

        assertThat(roomCollection).isEmpty();
    }

    @Test
    public void deleteAllRoomsTest() {

        roomRepository.deleteRooms();
        verify(roomJpaRepository).deleteAll();
    }
}