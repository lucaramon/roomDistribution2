package de.neusta.ldagostino.codingchallengetdd.infrastructure;

import de.neusta.ldagostino.codingchallengetdd.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RoomNumberNotUniqueException.class)
    public ResponseEntity<?> handleRoomNumberNotUniqueException(RoomNumberNotUniqueException exception) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setCode(2);
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonNotUniqueException.class)
    public ResponseEntity<?> handlePersonNotUniqueException(PersonNotUniqueException exception) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setCode(3);
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataLineNotValidException.class)
    public ResponseEntity<?> handleDataLineNotValidException(DataLineNotValidException exception) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setCode(4);
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<?> handleRoomNotFoundException(RoomNotFoundException exception) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setCode(5);
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomNumberNotValidException.class)
    public ResponseEntity<?> handleRoomNumberNotValidException(RoomNumberNotValidException exception) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setCode(6);
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
