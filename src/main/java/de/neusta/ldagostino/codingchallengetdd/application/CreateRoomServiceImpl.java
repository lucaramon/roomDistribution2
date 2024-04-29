package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.DataLineValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateRoomServiceImpl implements CreateRoomService {

    private final CreatePersonService createPersonService;

    private final DataLineValidator dataLineValidator;

    @Override
    public Room createRoom(String roomAsString) {

        List<String> valuesOfRoom = Arrays.asList(roomAsString.split(","));

        Room room = new Room(valuesOfRoom.get(0));

        List<Person> allPersons = valuesOfRoom.stream()
                .skip(1)
                .map(String::trim)
                .peek(dataLineValidator::validatePersonAsString)
                .map(createPersonService::createPerson)
                .collect(Collectors.toList());

        room.setPersons(allPersons);

        return room;
    }
}
