package pyrkap.logsandmetrics.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pyrkap.logsandmetrics.domain.exceptions.CyclistAlreadyExistsException;
import pyrkap.logsandmetrics.domain.exceptions.CyclistNotFoundException;
import pyrkap.logsandmetrics.domain.exceptions.InvalidIdException;
import pyrkap.logsandmetrics.infrastructure.api.dtos.MessageDto;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {CyclistAlreadyExistsException.class})
    public ResponseEntity<MessageDto> handle(CyclistAlreadyExistsException e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {CyclistNotFoundException.class})
    public ResponseEntity<MessageDto> handle(CyclistNotFoundException e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidIdException.class})
    public ResponseEntity<MessageDto> handle(InvalidIdException e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
