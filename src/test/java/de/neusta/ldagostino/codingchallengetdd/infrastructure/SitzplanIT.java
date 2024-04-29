package de.neusta.ldagostino.codingchallengetdd.infrastructure;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SitzplanIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("sitzplan")
            .withUsername("sa")
            .withPassword("password");


    @Test
    void containerConnectionSuccessfullTest() {
        Assertions.assertThat(postgresContainer.isCreated()).isTrue();
        Assertions.assertThat(postgresContainer.isRunning()).isTrue();
    }

    @Test
    void sitzplanApplicationTest() {

        //Auf alle Räume zugreifen -> leeres Ergebnis
        getAllRoomsWithEmptyResult();

        //Datei importieren
        importData();

        //Auf alle Räume zugreifen -> Räume vorhanden
        getAllRoomsWithResult();

        //Ein Raum laden -> Values prüfen
        getOneExistingRoomAndCheckRoomValues();

        //Datei neu importieren
        importData();

        //Auf alle Räume zugreifen -> gleiche Datensatzzahl
        getAllRoomsWithResult();
    }

    private void getAllRoomsWithEmptyResult() {
        ResponseEntity<?> responseEntity = testRestTemplate.exchange("/api/room", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void importData() {
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", new ClassPathResource("sitzplan.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                "/api/import",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                Void.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void getAllRoomsWithResult() {
        ResponseEntity<List<Room>> responseEntity = testRestTemplate.exchange("/api/room", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
        });

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotEmpty();
        Assertions.assertThat(responseEntity.getBody()).hasSize(15);
    }

    private void getOneExistingRoomAndCheckRoomValues() {
        ResponseEntity<Room> responseEntity = testRestTemplate.exchange("/api/room/1111", HttpMethod.GET, null, new ParameterizedTypeReference<Room>() {
        });

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getRoomNumber()).isEqualTo("1111");
        Assertions.assertThat(responseEntity.getBody().getPersons().get(0).getLdapuser()).isEqualTo("dfischer");
    }
}
