package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.application.RoomService;
import de.neusta.ldagostino.codingchallengetdd.generated.api.RoomApiDelegate;
import de.neusta.ldagostino.codingchallengetdd.generated.model.RoomDto;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNotFoundException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotValidException;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.RoomDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomApiDelegatorImpl implements RoomApiDelegate {

    private final RoomService roomService;
    private final RoomDtoMapper roomDtoMapper;

    @Override
    public ResponseEntity<RoomDto> roomRoomNumberGet(String roomNumber) {

        RoomDto room = roomDtoMapper.mapRoomToRoomDto(roomService.getRoom(roomNumber));

        if (roomNumber.length() != 4) {
            throw new RoomNumberNotValidException("Die Raumnummer muss 4 stellig sein!");
        } else if (room == null) {
            throw new RoomNotFoundException("Raum mit Nummer " + roomNumber + " nicht gefunden!");
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RoomDto>> roomGet() {
        List<RoomDto> rooms = (List<RoomDto>) roomDtoMapper.mapRoomsToRoomDto(roomService.getRooms());

        if (rooms.isEmpty()) {
            throw new RoomNotFoundException("Es wurde kein Raum gefunden!");
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
