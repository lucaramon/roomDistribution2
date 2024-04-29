package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.generated.model.PersonDto;
import de.neusta.ldagostino.codingchallengetdd.generated.model.RoomDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RoomDtoMapperImplTest {

    @InjectMocks
    RoomDtoMapperImpl roomDtoMapper;

    @Test
    void mapEmptyRoomToRoomDtoTest() {

        RoomDto roomDto = roomDtoMapper.mapRoomToRoomDto(null);

        Assertions.assertThat(roomDto).isEqualTo(null);
    }

    @Test
    void mapRoomToRoomDtoTest() {

        Room room = new Room("1234");

        RoomDto roomDto = roomDtoMapper.mapRoomToRoomDto(room);

        Assertions.assertThat(roomDto.getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void mapEmptyRoomCollectionToRoomDtoCollectionTest() {

        Collection<RoomDto> rooms = roomDtoMapper.mapRoomsToRoomDto(null);

        Assertions.assertThat(rooms).isEqualTo(null);
    }

    @Test
    void mapRoomCollectionToRoomDtoCollectionTest() {

        Collection<Room> rooms = new ArrayList<>();
        Room room = new Room("1234");
        rooms.add(room);

        Collection<RoomDto> roomDtos = roomDtoMapper.mapRoomsToRoomDto(rooms);

        Assertions.assertThat(roomDtos).isNotEmpty();
        Assertions.assertThat(roomDtos.size()).isEqualTo(rooms.size());
        Assertions.assertThat(roomDtos.stream().toList().get(0).getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void mapEmptyPersonToPersonDtoTest() {

        PersonDto personDto = roomDtoMapper.personToPersonDto(null);

        Assertions.assertThat(personDto).isEqualTo(null);
    }

    @Test
    void mapPersonToPersonDtoTest() {

        Person person = new Person("Susanne", "Moog", "smoog");

        PersonDto personDto = roomDtoMapper.personToPersonDto(person);

        Assertions.assertThat(personDto.getLdapuser()).isEqualTo(person.getLdapuser());
    }

    @Test
    void mapEmptyPersonCollectionToPersonDtoCollectionTest() {

        Collection<PersonDto> personDtos = roomDtoMapper.personListToPersonDtoList(null);

        Assertions.assertThat(personDtos).isEqualTo(null);
    }

    @Test
    void mapPersonCollectionToPersonDtoCollectionTest() {

        List<Person> persons = new ArrayList<>();
        Person person = new Person("Susanne", "Moog", "smoog");
        persons.add(person);

        Collection<PersonDto> personDtos = roomDtoMapper.personListToPersonDtoList(persons);

        Assertions.assertThat(personDtos).isNotEmpty();
        Assertions.assertThat(personDtos.size()).isEqualTo(persons.size());
        Assertions.assertThat(personDtos.stream().toList().get(0).getLdapuser()).isEqualTo(person.getLdapuser());
    }
}