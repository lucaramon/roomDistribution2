package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import de.neusta.ldagostino.codingchallengetdd.application.RoomRepository;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomJpaRepository roomJpaRepository;
    private final RoomMapper roomMapper;

    @Override
    public Room getRoom(String roomNumber) {

        RoomEntity roomEntity = roomJpaRepository.findRoomByRoomNumber(roomNumber);

        return roomMapper.mapRoomEntity(roomEntity);
    }

    @Override
    public List<Room> getRooms() {

        Collection<RoomEntity> roomEntities = roomJpaRepository.findAll();

        return roomMapper.mapRoomEntities(roomEntities).stream().toList();
    }

    @Override
    public Collection<Room> addRooms(List<Room> allRooms) {

        Collection<RoomEntity> roomEntities = roomMapper.mapRooms(allRooms);
        deleteRooms();
        Collection<RoomEntity> savedRoomEntities = roomJpaRepository.saveAll(roomEntities);

        return roomMapper.mapRoomEntities(savedRoomEntities);
    }

    @Override
    public void deleteRooms() {

        roomJpaRepository.deleteAll();
    }
}
