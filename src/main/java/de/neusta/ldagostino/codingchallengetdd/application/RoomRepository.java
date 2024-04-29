package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;

import java.util.Collection;
import java.util.List;

public interface RoomRepository {
    Room getRoom(String roomNumber);

    List<Room> getRooms();

    Collection<Room> addRooms(List<Room> allRooms);

    void deleteRooms();
}
