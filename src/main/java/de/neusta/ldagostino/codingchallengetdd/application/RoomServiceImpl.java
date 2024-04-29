package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room getRoom(String roomNumber) {
        return roomRepository.getRoom(roomNumber);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.getRooms();
    }
}
