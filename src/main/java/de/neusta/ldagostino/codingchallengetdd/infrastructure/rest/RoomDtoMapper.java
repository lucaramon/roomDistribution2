package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.generated.model.RoomDto;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.PersonMapper;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence.RoomEntity;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface RoomDtoMapper {

    Collection<RoomDto> mapRoomsToRoomDto(Collection<Room> rooms);

    RoomDto mapRoomToRoomDto(Room room);

}
