package de.neusta.ldagostino.codingchallengetdd.infrastructure.validation;

import de.neusta.ldagostino.codingchallengetdd.application.SitzplanImportValidator;
import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.PersonNotUniqueException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniquePersonValidator implements SitzplanImportValidator {

    public void validate(List<Room> allRooms) {
        List<String> allLdapuser = allRooms.stream()
                .flatMap(room -> room.getPersons().stream())
                .map(Person::getLdapuser)
                .toList();

        if (allLdapuser.size() != allLdapuser.stream().distinct().count()) {
            throw new PersonNotUniqueException("Eine Person darf nur einmal vorkommen!");
        }
    }
}
