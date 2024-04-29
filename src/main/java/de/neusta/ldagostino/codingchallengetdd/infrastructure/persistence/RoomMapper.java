package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface RoomMapper {

    Collection<Room> mapRoomEntities(Collection<RoomEntity> roomEntities);

    Collection<RoomEntity> mapRooms(Collection<Room> rooms);

    Room mapRoomEntity(RoomEntity roomEntity);

    RoomEntity mapRoom(Room room);
}
