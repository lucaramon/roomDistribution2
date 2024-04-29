package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.application.SitzplanImportValidator;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.RoomNumberNotUniqueException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniqueRoomNumberValidator implements SitzplanImportValidator {
    
    public void validate(List<Room> allRooms) {

        List<String> allRoomNumbers = allRooms.stream()
                .map(Room::getRoomNumber)
                .toList();

        if (allRoomNumbers.size() != allRoomNumbers.stream().distinct().count()) {
            throw new RoomNumberNotUniqueException("Eine Raumnummer darf nur einmal vorkommen!");
        }
    }
}
