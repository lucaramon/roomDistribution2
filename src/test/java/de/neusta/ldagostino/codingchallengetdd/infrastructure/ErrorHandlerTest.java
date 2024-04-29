package de.neusta.ldagostino.codingchallengetdd.infrastructure;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    @InjectMocks
    ErrorHandler errorHandler;

    @Test
    public void handleRoomNotFoundExceptionTest() {

        ResponseEntity<?> responseEntity = errorHandler.handleRoomNotFoundException(new RoomNotFoundException("Raum nicht gefunden!"));

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void handleRoomNumberNotValidExceptionTest() {

        ResponseEntity<?> responseEntity = errorHandler.handleRoomNumberNotValidException(new RoomNumberNotValidException("Die Raumnummer muss 4 stellig sein!"));

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void handleRoomNumberNotUniqueExceptionTest() {

        ResponseEntity<?> responseEntity = errorHandler.handleRoomNumberNotUniqueException(new RoomNumberNotUniqueException("Eine Raumnummer darf nur einmal vorkommen!"));

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void handlePersonNotUniqueExceptionTest() {

        ResponseEntity<?> responseEntity = errorHandler.handlePersonNotUniqueException(new PersonNotUniqueException("Eine Person darf nur einmal vorkommen!"));

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void handleDataLineNotValidExceptionTest() {

        ResponseEntity<?> responseEntity = errorHandler.handleDataLineNotValidException(new DataLineNotValidException("Die Datenreihe entspricht nicht den Konventionen!"));

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}