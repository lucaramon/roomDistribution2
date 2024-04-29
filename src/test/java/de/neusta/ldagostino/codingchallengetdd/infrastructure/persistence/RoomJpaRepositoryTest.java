package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomJpaRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("sitzplan")
            .withUsername("sa")
            .withPassword("password");

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Test
    void containerConnectionSuccessfullTest() {
        Assertions.assertThat(postgresContainer.isCreated()).isTrue();
        Assertions.assertThat(postgresContainer.isRunning()).isTrue();
    }

    @BeforeEach
    void setup() {
        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setRoomNumber("1234");

        PersonEntity personEntity1 = new PersonEntity();
        personEntity1.setFirstname("Susanne");
        personEntity1.setLastname("Moog");
        personEntity1.setLdapuser("smoog");

        roomEntity1.setPersons(Collections.singletonList(personEntity1));

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setRoomNumber("5678");

        Collection<RoomEntity> roomEntities = new ArrayList<>();
        roomEntities.add(roomEntity1);
        roomEntities.add(roomEntity2);

        roomJpaRepository.saveAll(roomEntities);
    }

    @Test
    void saveRoomEntityJpaRepositoryTest() {

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber("1234");

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Susanne");
        personEntity.setLastname("Moog");
        personEntity.setLdapuser("smoog");

        roomEntity.setPersons(Collections.singletonList(personEntity));

        RoomEntity savedRoomEntity = roomJpaRepository.save(roomEntity);

        Assertions.assertThat(savedRoomEntity.getId()).isNotNull();
        Assertions.assertThat(savedRoomEntity.getRoomNumber()).isEqualTo(roomEntity.getRoomNumber());
        Assertions.assertThat(savedRoomEntity.getPersons().get(0).getFirstname()).isEqualTo(personEntity.getFirstname());
        Assertions.assertThat(savedRoomEntity.getPersons().get(0).getLastname()).isEqualTo(personEntity.getLastname());
        Assertions.assertThat(savedRoomEntity.getPersons().get(0).getLdapuser()).isEqualTo(personEntity.getLdapuser());
    }

    @Test
    void findRoomEntityByRoomNumberInJpaRepositoryTest() {

        RoomEntity getRoomEntity = roomJpaRepository.findRoomByRoomNumber("1234");

        Assertions.assertThat(getRoomEntity).isNotNull();
        Assertions.assertThat(getRoomEntity.getRoomNumber()).isEqualTo("1234");
        Assertions.assertThat(getRoomEntity.getPersons().get(0).getLdapuser()).isEqualTo("smoog");
    }

    @Test
    void findAllRoomEntitiesInJpaRoomRepository() {

        List<RoomEntity> getRoomEntities = roomJpaRepository.findAll();

        Assertions.assertThat(getRoomEntities).isNotNull();
        Assertions.assertThat(getRoomEntities.size()).isEqualTo(2);
        Assertions.assertThat(getRoomEntities.get(0).getPersons().size()).isEqualTo(1);
        Assertions.assertThat(getRoomEntities.get(1).getPersons().size()).isEqualTo(0);
    }
}

