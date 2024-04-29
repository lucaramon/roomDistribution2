package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RoomMapperImplTest {

    @InjectMocks
    RoomMapperImpl roomMapper;

    @Test
    void mapEmptyRoomCollectionEntityToRoomCollectionTest() {

        Collection<Room> rooms = roomMapper.mapRoomEntities(null);

        Assertions.assertThat(rooms).isEqualTo(null);
    }

    @Test
    void mapRoomCollectionEntityToRoomCollectionTest() {

        Collection<RoomEntity> roomEntities = new ArrayList<>();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber("1234");
        roomEntities.add(roomEntity);

        Collection<Room> rooms = roomMapper.mapRoomEntities(roomEntities);

        Assertions.assertThat(rooms).isNotEmpty();
        Assertions.assertThat(rooms.size()).isEqualTo(roomEntities.size());
        Assertions.assertThat(rooms.stream().toList().get(0).getRoomNumber()).isEqualTo(roomEntity.getRoomNumber());
    }

    @Test
    void mapEmptyRoomCollectionToRoomEntityCollectionTest() {

        Collection<RoomEntity> roomEntities = roomMapper.mapRooms(null);

        Assertions.assertThat(roomEntities).isEqualTo(null);
    }

    @Test
    void mapRoomCollectionToRoomEntityCollectionTest() {

        Collection<Room> rooms = new ArrayList<>();
        Room room = new Room("1234");
        rooms.add(room);

        Collection<RoomEntity> roomEntities = roomMapper.mapRooms(rooms);

        Assertions.assertThat(roomEntities).isNotEmpty();
        Assertions.assertThat(roomEntities.size()).isEqualTo(rooms.size());
        Assertions.assertThat(roomEntities.stream().toList().get(0).getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void mapEmptyRoomEntityToRoomTest() {

        Room room = roomMapper.mapRoomEntity(null);

        Assertions.assertThat(room).isEqualTo(null);
    }

    @Test
    void mapRoomEntityToRoomTest() {

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber("1234");

        Room room = roomMapper.mapRoomEntity(roomEntity);

        Assertions.assertThat(room.getRoomNumber()).isEqualTo(roomEntity.getRoomNumber());
    }

    @Test
    void mapEmptyRoomToRoomEntityTest() {

        RoomEntity roomEntity = roomMapper.mapRoom(null);

        Assertions.assertThat(roomEntity).isEqualTo(null);
    }

    @Test
    void mapRoomToRoomEntityTest() {

        Room room = new Room("1234");

        RoomEntity roomEntity = roomMapper.mapRoom(room);

        Assertions.assertThat(roomEntity.getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void mapEmptyPersonEntityToPersonTest() {

        Person person = roomMapper.personEntityToPerson(null);

        Assertions.assertThat(person).isEqualTo(null);
    }

    @Test
    void mapPersonEntityToPersonTest() {

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Susanne");
        personEntity.setLastname("Moog");
        personEntity.setLdapuser("smoog");

        Person person = roomMapper.personEntityToPerson(personEntity);

        Assertions.assertThat(person.getLdapuser()).isEqualTo(personEntity.getLdapuser());
    }

    @Test
    void mapEmptyPersonToPersonEntityTest() {

        PersonEntity personEntity = roomMapper.personToPersonEntity(null);

        Assertions.assertThat(personEntity).isEqualTo(null);
    }

    @Test
    void mapPersonToPersonEntityTest() {

        Person person = new Person("Susanne", "Moog", "smoog");

        PersonEntity personEntity = roomMapper.personToPersonEntity(person);

        Assertions.assertThat(personEntity.getLdapuser()).isEqualTo(person.getLdapuser());
    }

    @Test
    void mapEmptyPersonCollectionEntityToPersonCollectionTest() {

        Collection<Person> persons = roomMapper.personEntityListToPersonList(null);

        Assertions.assertThat(persons).isEqualTo(null);
    }

    @Test
    void mapPersonCollectionEntityToPersonCollectionTest() {

        List<PersonEntity> personEntities = new ArrayList<>();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Susanne");
        personEntity.setLastname("Moog");
        personEntity.setLdapuser("smoog");
        personEntities.add(personEntity);

        Collection<Person> persons = roomMapper.personEntityListToPersonList(personEntities);

        Assertions.assertThat(persons).isNotEmpty();
        Assertions.assertThat(persons.size()).isEqualTo(personEntities.size());
        Assertions.assertThat(persons.stream().toList().get(0).getLdapuser()).isEqualTo(personEntity.getLdapuser());
    }

    @Test
    void mapEmptyPersonCollectionToPersonEntityCollectionTest() {

        Collection<PersonEntity> personEntities = roomMapper.personListToPersonEntityList(null);

        Assertions.assertThat(personEntities).isEqualTo(null);
    }

    @Test
    void mapPersonCollectionToPersonEntityCollectionTest() {

        List<Person> persons = new ArrayList<>();
        Person person = new Person("Susanne", "Moog", "smoog");
        persons.add(person);

        Collection<PersonEntity> personEntities = roomMapper.personListToPersonEntityList(persons);

        Assertions.assertThat(personEntities).isNotEmpty();
        Assertions.assertThat(personEntities.size()).isEqualTo(persons.size());
        Assertions.assertThat(personEntities.stream().toList().get(0).getLdapuser()).isEqualTo(person.getLdapuser());
    }
}