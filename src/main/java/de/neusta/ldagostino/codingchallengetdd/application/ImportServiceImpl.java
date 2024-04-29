package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.validation.DataLineValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImportServiceImpl implements ImportService {

    private final RoomRepository roomRepository;
    private final CreateRoomService createRoomService;
    private final List<SitzplanImportValidator> sitzplanImportValidators;
    private final DataLineValidator dataLineValidator;

    @Override
    public List<Room> importData(byte[] content) {

        List<Room> allRooms = createRooms(content);

        validate(allRooms);

        roomRepository.deleteRooms();

        return roomRepository.addRooms(allRooms).stream().toList();
    }

    private void validate(List<Room> allRooms) {
        sitzplanImportValidators.forEach(sitzplanImportValidator -> sitzplanImportValidator.validate(allRooms));
    }

    private List<Room> createRooms(byte[] content) {
        String contentString = new String(content);
        List<String> contentList = contentString.lines().toList();

        return contentList.stream()
                .peek(dataLineValidator::validateRoomAsString)
                .map(createRoomService::createRoom).toList();
    }
}
